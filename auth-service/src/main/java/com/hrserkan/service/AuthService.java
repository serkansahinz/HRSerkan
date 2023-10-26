package com.hrserkan.service;

import com.hrserkan.dto.request.ActivateRequestDto;
import com.hrserkan.dto.request.LoginRequestDto;
import com.hrserkan.dto.request.RegisterRequestDto;
import com.hrserkan.dto.response.RegisterResponseDto;
import com.hrserkan.exception.AuthManagerException;
import com.hrserkan.exception.ErrorType;
import com.hrserkan.mapper.IAuthMapper;
import com.hrserkan.repository.IAuthRepository;
import com.hrserkan.repository.entity.Auth;
import com.hrserkan.repository.enums.EStatus;
import com.hrserkan.utility.CodeGenerator;
import com.hrserkan.utility.JwtTokenManager;
import com.hrserkan.utility.ServiceManager;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService extends ServiceManager<Auth, Long> {

    private final IAuthRepository authRepository;
    private final JwtTokenManager jwtTokenManager;

    public AuthService(IAuthRepository authRepository, JwtTokenManager jwtTokenManager) {
        super(authRepository);
        this.authRepository=authRepository;
        this.jwtTokenManager = jwtTokenManager;
    }

    public RegisterResponseDto register(RegisterRequestDto dto){
        Auth auth = IAuthMapper.INSTANCE.toAuth(dto);
        auth.setActivationCode(CodeGenerator.generateCode());
        if(authRepository.existsByUsername(dto.getUsername())){
            throw new AuthManagerException(ErrorType.USERNAME_ALREADY_EXIST);
        }
        save(auth);
        RegisterResponseDto registerResponseDto= IAuthMapper.INSTANCE.toRegisterResponseDto(auth);
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
        return "Account has been activated";
        } else{
            throw new AuthManagerException(ErrorType.INVALID_CODE);
        }

    }
}
