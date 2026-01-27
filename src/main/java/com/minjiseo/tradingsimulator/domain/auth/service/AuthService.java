package com.minjiseo.tradingsimulator.domain.auth.service;

import com.minjiseo.tradingsimulator.domain.auth.dto.LoginRequest;
import com.minjiseo.tradingsimulator.domain.auth.dto.LoginResponse;
import com.minjiseo.tradingsimulator.domain.user.entity.User;
import com.minjiseo.tradingsimulator.domain.user.repository.UserRepository;
import com.minjiseo.tradingsimulator.global.exception.CustomException;
import com.minjiseo.tradingsimulator.global.exception.ErrorCode;
import com.minjiseo.tradingsimulator.global.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional(readOnly = true)
    public LoginResponse login(LoginRequest request) {
        // 이메일로 사용자 조회
        User user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        // 비밀번호 검증
        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new CustomException(ErrorCode.INVALID_PASSWORD);
        }

        // JWT 토큰 발급
        String accessToken = jwtTokenProvider.createToken(user.getId());

        return new LoginResponse(accessToken);
    }
}
