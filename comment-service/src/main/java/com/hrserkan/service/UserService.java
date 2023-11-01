package com.hrserkan.service;

import com.hrserkan.dto.request.AuthUpdateRequestDto;
import com.hrserkan.dto.request.UserProfileUpdateRequestDto;
import com.hrserkan.dto.request.UserSaveRequestDto;
import com.hrserkan.exception.ErrorType;
import com.hrserkan.exception.UserManagerException;
import com.hrserkan.manager.IAuthManager;
import com.hrserkan.mapper.IUserMapper;
import com.hrserkan.repository.IUserRepository;
import com.hrserkan.repository.entity.Comment;
import com.hrserkan.repository.enums.EStatus;
import com.hrserkan.utility.JwtTokenManager;
import com.hrserkan.utility.ServiceManager;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService extends ServiceManager<Comment, Long> {

    private final IUserRepository userRepository;
    private final JwtTokenManager jwtTokenManager;
    private final IAuthManager authManager;

    public UserService(IUserRepository userRepository, JwtTokenManager jwtTokenManager, IAuthManager authManager) {
        super(userRepository);
        this.userRepository = userRepository;
        this.jwtTokenManager = jwtTokenManager;
        this.authManager = authManager;
    }




}
