package com.siat.secretboard.auth.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.siat.secretboard.auth.domain.RefreshTokenEntity;

public interface TokenRepository extends JpaRepository<RefreshTokenEntity, String> {
}
