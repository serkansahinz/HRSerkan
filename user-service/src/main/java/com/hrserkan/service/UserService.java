package com.hrserkan.service;

import com.hrserkan.dto.request.AuthUpdateRequestDto;
import com.hrserkan.dto.request.UserProfileUpdateRequestDto;
import com.hrserkan.dto.request.UserSaveRequestDto;
import com.hrserkan.exception.ErrorType;
import com.hrserkan.exception.UserManagerException;
import com.hrserkan.manager.IAuthManager;
import com.hrserkan.mapper.IUserMapper;
import com.hrserkan.repository.IUserRepository;
import com.hrserkan.repository.entity.UserProfile;
import com.hrserkan.repository.enums.EStatus;
import com.hrserkan.utility.JwtTokenManager;
import com.hrserkan.utility.ServiceManager;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService extends ServiceManager<UserProfile, Long> {

    private final IUserRepository userRepository;
    private final JwtTokenManager jwtTokenManager;
    private final IAuthManager authManager;

    public UserService(IUserRepository userRepository, JwtTokenManager jwtTokenManager, IAuthManager authManager) {
        super(userRepository);
        this.userRepository = userRepository;
        this.jwtTokenManager = jwtTokenManager;
        this.authManager = authManager;
    }

    public Boolean createNewUser(UserSaveRequestDto userSaveRequestDto) {

        try {
            UserProfile userProfile = IUserMapper.INSTANCE.toUserProfile(userSaveRequestDto);
            save(userProfile);
            return true;
        } catch (Exception e) {
            throw new UserManagerException(ErrorType.USER_NOT_CREATED);
        }
    }
//    public Boolean createNewUserWithRabbitmq(RegisterModel registerModel) {
//
//        try {
//            UserProfile userProfile = IUserMapper.INSTANCE.toUserProfile(registerModel);
//            save(userProfile);
//            return true;
//        } catch (Exception e) {
//            throw new UserManagerException(ErrorType.USER_NOT_CREATED);
//        }
//    }

    public String activateStatus(String token) {
        Optional<Long> authId = jwtTokenManager.getAuthIdFromToken(token);//authtan gelen token bu register ile geldi
        if (authId.isEmpty()) {
            throw new UserManagerException(ErrorType.INVALID_TOKEN);
        }
        Optional<UserProfile> userProfile = userRepository.findByAuthId(authId.get());
        if (userProfile.isEmpty()) {
            throw new UserManagerException(ErrorType.USER_NOT_FOUND);
        }
        userProfile.get().setStatus(EStatus.ACTIVE);
        update(userProfile.get());
        return "Account has been activated";
    }

    @Transactional
    public String updateUserProfile(UserProfileUpdateRequestDto userProfileUpdateRequestDto) {
        Optional<Long> authId = jwtTokenManager.getAuthIdFromToken(userProfileUpdateRequestDto.getToken()); //authtan gelen token bu register ile geldi
        if (authId.isEmpty()) {
            throw new UserManagerException(ErrorType.INVALID_TOKEN);
        }
        Optional<UserProfile> userProfile = userRepository.findByAuthId(authId.get());
        if (userProfile.isEmpty()) {
            throw new UserManagerException(ErrorType.USER_NOT_FOUND);
        }
        if (!userProfile.get().getEmail().equals(userProfileUpdateRequestDto.getEmail())
                || !userProfile.get().getUsername().equals(userProfileUpdateRequestDto.getUsername())) {
            userProfile.get().setEmail(userProfileUpdateRequestDto.getEmail());
            userProfile.get().setUsername(userProfileUpdateRequestDto.getUsername());
            // auth servise istek atan bir metot yazıldı
            authManager.updateAuth(AuthUpdateRequestDto.builder()
                    .email(userProfileUpdateRequestDto.getEmail())
                    .username(userProfileUpdateRequestDto.getUsername())
                    .id(authId.get())
                    .build(),"Bearer "+ userProfileUpdateRequestDto.getToken());
        }
            userProfile.get().setAbout(userProfileUpdateRequestDto.getAbout());
            userProfile.get().setPhone(userProfileUpdateRequestDto.getPhone());
            userProfile.get().setAddress(userProfileUpdateRequestDto.getAddress());
            userProfile.get().setName(userProfileUpdateRequestDto.getName());
            userProfile.get().setSurName(userProfileUpdateRequestDto.getSurName());
            userProfile.get().setAvatar(userProfileUpdateRequestDto.getAvatar());
            update(userProfile.get());

        return "Account has been updated";

    }


}
