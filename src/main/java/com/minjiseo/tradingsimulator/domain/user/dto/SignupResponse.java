package com.minjiseo.tradingsimulator.domain.user.dto;

import com.minjiseo.tradingsimulator.domain.user.entity.User;

public record SignupResponse(
        Long id,
        String email
) {
    public static SignupResponse from(User user) {
        return new SignupResponse(user.getId(), user.getEmail());
    }
}
