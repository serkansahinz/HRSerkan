package com.hrserkan.controller;

import com.hrserkan.dto.request.ActivateRequestDto;
import com.hrserkan.dto.request.AuthUpdateRequestDto;
import com.hrserkan.dto.request.LoginRequestDto;
import com.hrserkan.dto.request.RegisterRequestDto;
import com.hrserkan.dto.response.RegisterResponseDto;
import com.hrserkan.manager.IUserManager;
import com.hrserkan.repository.entity.Auth;
import com.hrserkan.service.AuthService;
import com.hrserkan.utility.JwtTokenManager;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.hrserkan.constant.EndPoints.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(AUTH)

public class AuthController {

    private final AuthService authService;
    private final JwtTokenManager jwtTokenManager;


    @GetMapping(FIND_ALL)
    public ResponseEntity<List<Auth>> findAll(){
        return ResponseEntity.ok(authService.findAll());
    }

    @GetMapping("/create_token")
    public ResponseEntity<String> createToken(Long id){
        return ResponseEntity.ok(jwtTokenManager.createToken(id).get());
    }
    @GetMapping("/get_id-from_token")
    public ResponseEntity<Long> getIdFromToken(String token){
        return ResponseEntity.ok(jwtTokenManager.getIdFromToken(token).get());
    }
    @PostMapping(REGISTER)
    public ResponseEntity<RegisterResponseDto> register(@RequestBody @Valid RegisterRequestDto registerRequestDto){
        return ResponseEntity.ok(authService.register(registerRequestDto));
    }
    @PostMapping(REGISTER+"_with_rabbitmq")
    public ResponseEntity<RegisterResponseDto> registerWithRabbitMq(@RequestBody @Valid RegisterRequestDto registerRequestDto){
        return ResponseEntity.ok(authService.registerWithRabbitMq(registerRequestDto));
    }
    @PostMapping(LOGIN)
    public ResponseEntity<String> login(@RequestBody @Valid LoginRequestDto loginRequestDto){
        return ResponseEntity.ok(authService.login(loginRequestDto));
    }
    @PostMapping(ACTIVATE_STATUS)
    public ResponseEntity<String> login(@RequestBody @Valid ActivateRequestDto activateRequestDto){
        return ResponseEntity.ok(authService.activateStatus(activateRequestDto));
    }
    //update yazılacak
    @PutMapping(UPDATE)
    public ResponseEntity<String> updateAuth(@RequestBody AuthUpdateRequestDto authUpdateRequestDto , @RequestHeader("Authorization")String token){
        return ResponseEntity.ok(authService.updateAuth(authUpdateRequestDto));
    }
}
