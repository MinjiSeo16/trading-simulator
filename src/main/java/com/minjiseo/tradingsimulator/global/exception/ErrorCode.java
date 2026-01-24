package com.minjiseo.tradingsimulator.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    // User
    EMAIL_DUPLICATE(HttpStatus.CONFLICT, "이미 사용 중인 이메일입니다");

    private final HttpStatus status;
    private final String message;
}
