package com.hrserkan.controller;


import com.hrserkan.dto.request.UserProfileUpdateRequestDto;
import com.hrserkan.dto.request.UserSaveRequestDto;
import com.hrserkan.repository.entity.Comment;
import com.hrserkan.service.UserService;
import com.hrserkan.utility.JwtTokenManager;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;

import java.util.List;

import static com.hrserkan.constant.EndPoints.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(USER)

public class UserController {

    private final UserService userService;
    private final JwtTokenManager jwtTokenManager;

    @GetMapping(FIND_ALL)
    public ResponseEntity<List<Comment>>findAll(){
        return ResponseEntity.ok(userService.findAll());
    }

    @PostMapping(SAVE)
    public ResponseEntity<Boolean> save(@RequestBody UserSaveRequestDto userSaveRequestDto){
        return ResponseEntity.ok(userService.createNewUser(userSaveRequestDto));
    }

    @PostMapping(ACTIVATE_STATUS)
    public ResponseEntity<String> activateStatus(@RequestParam String token){
        return ResponseEntity.ok(userService.activateStatus(token));
    }
    @PutMapping(UPDATE)
    public ResponseEntity<String> updateUserProfile(@Valid @RequestBody UserProfileUpdateRequestDto userProfileUpdateRequestDto){
        return ResponseEntity.ok(userService.updateUserProfile(userProfileUpdateRequestDto));
    }

}
