package com.minjiseo.tradingsimulator.domain.wallet.entity;

import com.minjiseo.tradingsimulator.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "wallets")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Wallet {

    private static final long DEFAULT_BALANCE = 50_000_000L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @Column(nullable = false)
    private Long balance;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    private Wallet(User user, Long balance) {
        this.user = user;
        this.balance = balance;
        this.createdAt = LocalDateTime.now();
    }

    public static Wallet createForSignup(User user) {
        return new Wallet(user, DEFAULT_BALANCE);
    }
}
