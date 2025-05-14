package com.siat.secretboard.auth.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.siat.secretboard.auth.domain.TokenEntity;

public interface TokenRepository extends JpaRepository<TokenEntity, String> {
}
