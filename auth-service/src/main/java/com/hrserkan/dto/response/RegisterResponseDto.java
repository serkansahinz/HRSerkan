package com.hrserkan.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterResponseDto {
    private String token;
    private Long id;//olmasa da olur todo proje bitince kaldırılabilir zaten token var
    private String activationCode;
    private String username;
}
