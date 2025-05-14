package com.siat.secretboard.auth.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class RefreshTokenEntity {
    @Id
    private String refreshToken;
}
