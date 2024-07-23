package com.example.demo.crypto;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CoinRepository extends JpaRepository<CoinEntity, Long> {
}
