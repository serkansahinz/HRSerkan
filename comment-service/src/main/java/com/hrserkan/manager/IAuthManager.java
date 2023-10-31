package com.hrserkan.manager;

import com.hrserkan.dto.request.AuthUpdateRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.hrserkan.constant.EndPoints.*;

@FeignClient(url = "http://localhost:6060/api/v1/auth",decode404 = true,name = "userprofile-auth")
public interface IAuthManager {

    @PutMapping(UPDATE)
    ResponseEntity<String> updateAuth(@RequestBody AuthUpdateRequestDto authUpdateRequestDto, @RequestHeader("Authorization")String token);

}
