package com.hrserkan.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorType {

    INTERNAL_ERROR_SERVER(5200,"Server Error",HttpStatus.INTERNAL_SERVER_ERROR),
    BAD_REQUEST(4200,"Wrong parameter",HttpStatus.BAD_REQUEST),
    USER_NOT_FOUND(4210, "No such user",HttpStatus.NOT_FOUND),
    ACCOUNT_NOT_ACTIVE(4211, "Account not active",HttpStatus.BAD_REQUEST),
    INVALID_CODE(4212, "Invalid code",HttpStatus.BAD_REQUEST),
    UNEXPECTED_ERROR(4214,"Unexpected error",HttpStatus.BAD_REQUEST),
    USER_NOT_CREATED(4216,"Kullanıcı oluşturulamadı", HttpStatus.BAD_REQUEST),
    INVALID_TOKEN(4218, "Invalid Token !!!",HttpStatus.BAD_REQUEST),
    TOKEN_NOT_CREATED(4219, "Token not created !!!",HttpStatus.BAD_REQUEST);

    private int code;
    private String message;
    HttpStatus httpStatus;
}
