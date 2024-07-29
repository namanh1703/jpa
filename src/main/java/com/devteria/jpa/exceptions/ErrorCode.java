package com.devteria.jpa.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized error!", HttpStatus.INTERNAL_SERVER_ERROR),
    USER_EXISTED(1001, "User existed!", HttpStatus.BAD_REQUEST),
    INVALID_KEY(8888, "Invalid message key!", HttpStatus.BAD_REQUEST),
    USERNAME_INVALID(1002, "Username must be at least 4 characters", HttpStatus.BAD_REQUEST),
    USER_NOT_EXISTED(1004, "User not existed!",HttpStatus.NOT_FOUND),
    UNAUTHENTICATED(1005, "Unauthenticated!",HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(1006, "You do not have permission!",HttpStatus.FORBIDDEN),

    PASSWORD_INVALID(1003, "Password must be at least 8 characters", HttpStatus.BAD_REQUEST);
    private int code;
    private String message;
    private HttpStatusCode statusCode;

    ErrorCode(int code, String message, HttpStatusCode statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }
}
