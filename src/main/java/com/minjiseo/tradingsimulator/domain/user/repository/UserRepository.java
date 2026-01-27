package com.minjiseo.tradingsimulator.domain.user.repository;

import com.minjiseo.tradingsimulator.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // 이메일 중복 체크용
    boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);
}
