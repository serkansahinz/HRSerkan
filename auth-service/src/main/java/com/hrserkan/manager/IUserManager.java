package com.hrserkan.manager;

import com.hrserkan.dto.request.UserSaveRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.hrserkan.constant.EndPoints.*;

//user-controller/save
//discovery server
//feign arka tarafta RestTemplate mantığında bir yapı kullanır.
@FeignClient(url = "http://localhost:6062/api/v1/user" ,decode404 = true,name = "auth-userprofile")  //interface proxy
public interface IUserManager {

        @PostMapping(SAVE)
        ResponseEntity<Boolean> save(@RequestBody UserSaveRequestDto userSaveRequestDto, @RequestHeader("Authorization") String token);

        @PostMapping(ACTIVATE_STATUS)
        ResponseEntity<String> activateStatus(@RequestParam String token);

}
