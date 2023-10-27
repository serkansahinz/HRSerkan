package com.hrserkan.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserProfileUpdateRequestDto {
    private String token;//loginden gelen token(ikinci token), register tokenı activate status içindi auth serviste
    @NotBlank(message = "Please enter a username")
    private String username;
    private String name;
    private String surName;
    @Email
    private String email;
    private String phone;
    private String address;
    private LocalDate birthDate;
    private String avatar;
    private String about;
    private Double salary;

}
