package com.minjiseo.tradingsimulator.domain.user.repository;

import com.minjiseo.tradingsimulator.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    // 이메일 중복 체크용
    boolean existsByEmail(String email);
}
