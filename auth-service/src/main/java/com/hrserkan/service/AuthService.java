package com.hrserkan.service;

import com.hrserkan.dto.request.ActivateRequestDto;
import com.hrserkan.dto.request.AuthUpdateRequestDto;
import com.hrserkan.dto.request.LoginRequestDto;
import com.hrserkan.dto.request.RegisterRequestDto;
import com.hrserkan.dto.response.RegisterResponseDto;
import com.hrserkan.exception.AuthManagerException;
import com.hrserkan.exception.ErrorType;
import com.hrserkan.manager.IUserManager;
import com.hrserkan.mapper.IAuthMapper;
import com.hrserkan.rabbitmq.producer.RegisterProducer;
import com.hrserkan.repository.IAuthRepository;
import com.hrserkan.repository.entity.Auth;
import com.hrserkan.repository.enums.EStatus;
import com.hrserkan.utility.CodeGenerator;
import com.hrserkan.utility.JwtTokenManager;
import com.hrserkan.utility.ServiceManager;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class AuthService extends ServiceManager<Auth, Long> {

    private final IAuthRepository authRepository;
    private final JwtTokenManager jwtTokenManager;
    private final IUserManager userManager;//open feign için
    private final RegisterProducer registerProducer;//rabbit için

    public AuthService(IAuthRepository authRepository, JwtTokenManager jwtTokenManager, IUserManager userManager, RegisterProducer registerProducer) {
        super(authRepository);
        this.authRepository=authRepository;
        this.jwtTokenManager = jwtTokenManager;
        this.userManager = userManager;
        this.registerProducer = registerProducer;
    }

    @Transactional
    public RegisterResponseDto register(RegisterRequestDto registerRequestDto){
        Auth auth = IAuthMapper.INSTANCE.toAuth(registerRequestDto);
        auth.setActivationCode(CodeGenerator.generateCode());
        if(authRepository.existsByUsername(registerRequestDto.getUsername())){
            throw new AuthManagerException(ErrorType.USERNAME_ALREADY_EXIST);
        }
        save(auth);


       String token=jwtTokenManager.createToken(auth.getId()).
                orElseThrow(()-> new AuthManagerException(ErrorType.INVALID_TOKEN));

        userManager.save(IAuthMapper.INSTANCE.toUserSaveRequestDto(auth), "Bearer " + token);
        RegisterResponseDto registerResponseDto= IAuthMapper.INSTANCE.toRegisterResponseDto(auth);

        registerResponseDto.setToken(token);
        return registerResponseDto;
    }
    @Transactional
    public RegisterResponseDto registerWithRabbitMq(RegisterRequestDto registerRequestDto){
        Auth auth = IAuthMapper.INSTANCE.toAuth(registerRequestDto);
        auth.setActivationCode(CodeGenerator.generateCode());
        if(authRepository.existsByUsername(registerRequestDto.getUsername())){
            throw new AuthManagerException(ErrorType.USERNAME_ALREADY_EXIST);
        }
        save(auth);
        //rabbit mq ile haberleştirme

        registerProducer.sendNewUser(IAuthMapper.INSTANCE.toRegisterModel(auth));

        RegisterResponseDto registerResponseDto= IAuthMapper.INSTANCE.toRegisterResponseDto(auth);

        //register sonrası tokenı oluşturma
        String token=jwtTokenManager.createToken(auth.getId()).
                orElseThrow(()-> new AuthManagerException(ErrorType.INVALID_TOKEN));
        registerResponseDto.setToken(token);
        return registerResponseDto;
    }

    public String login(LoginRequestDto loginRequestDto){
        Optional<Auth> optionalAuth=authRepository.findOptionalByUsernameAndPassword(loginRequestDto.getUsername(), loginRequestDto.getPassword());
        if(optionalAuth.isEmpty()){
            throw new AuthManagerException(ErrorType.LOGIN_ERROR);
        }

        if (!optionalAuth.get().getStatus().equals(EStatus.ACTIVE)){
            throw new AuthManagerException(ErrorType.ACCOUNT_NOT_ACTIVE);
        }
            return jwtTokenManager.createToken(optionalAuth.get().getId(),optionalAuth.get().getRole()).
                    orElseThrow(()-> new AuthManagerException(ErrorType.INVALID_TOKEN));

    }
    @Transactional
    public String activateStatus(ActivateRequestDto activateRequestDto){
        Optional<Long> id=jwtTokenManager.getIdFromToken(activateRequestDto.getToken());
        if(id.isEmpty()){
            throw new AuthManagerException(ErrorType.INVALID_TOKEN);
        }

        Optional<Auth> optionalAuth=findById(id.get());
        if (optionalAuth.isEmpty()){
            throw new AuthManagerException(ErrorType.USER_NOT_FOUND);
        }
        if(optionalAuth.get().getStatus().equals(EStatus.ACTIVE)){
            throw new AuthManagerException(ErrorType.ALREADY_ACTIVE);
        }
        if (activateRequestDto.getActivationCode().equals(optionalAuth.get().getActivationCode())){
            optionalAuth.get().setStatus(EStatus.ACTIVE);
            update(optionalAuth.get());
            userManager.activateStatus(activateRequestDto.getToken());
        return "Account has been activated";
        } else{
            throw new AuthManagerException(ErrorType.INVALID_CODE);
        }
    }
    public String updateAuth(AuthUpdateRequestDto authUpdateRequestDto) {
        Optional<Auth> auth = findById(authUpdateRequestDto.getId());
        if (auth.isEmpty()) {
            throw new AuthManagerException(ErrorType.USER_NOT_FOUND);
        }
        auth.get().setEmail(authUpdateRequestDto.getEmail());
        auth.get().setUsername(authUpdateRequestDto.getUsername());
        update(auth.get());
        return "Successfully updated";
    }
}
