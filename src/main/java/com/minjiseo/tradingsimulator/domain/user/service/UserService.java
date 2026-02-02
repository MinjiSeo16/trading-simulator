package com.minjiseo.tradingsimulator.domain.user.service;

import com.minjiseo.tradingsimulator.domain.user.dto.SignupRequest;
import com.minjiseo.tradingsimulator.domain.user.dto.SignupResponse;
import com.minjiseo.tradingsimulator.domain.user.entity.User;
import com.minjiseo.tradingsimulator.domain.user.repository.UserRepository;
import com.minjiseo.tradingsimulator.domain.wallet.entity.Wallet;
import com.minjiseo.tradingsimulator.domain.wallet.repository.WalletRepository;
import com.minjiseo.tradingsimulator.global.exception.CustomException;
import com.minjiseo.tradingsimulator.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final WalletRepository walletRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public SignupResponse signup(SignupRequest request) {
        // 이메일 중복 체크
        if (userRepository.existsByEmail(request.email())) {
            throw new CustomException(ErrorCode.EMAIL_DUPLICATE);
        }

        // 비밀번호 암호화 후 저장
        String encodedPassword = passwordEncoder.encode(request.password());
        User user = User.of(request.email(), encodedPassword);
        User savedUser = userRepository.save(user);

        // Wallet 생성 및 초기 가상 자금 지급
        Wallet wallet = Wallet.createForSignup(savedUser);
        walletRepository.save(wallet);

        return SignupResponse.from(savedUser);
    }
}
