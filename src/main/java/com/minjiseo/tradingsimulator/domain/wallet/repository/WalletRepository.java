package com.minjiseo.tradingsimulator.domain.wallet.repository;

import com.minjiseo.tradingsimulator.domain.wallet.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository extends JpaRepository<Wallet, Long> {
}
