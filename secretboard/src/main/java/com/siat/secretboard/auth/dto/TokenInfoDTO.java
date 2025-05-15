package com.siat.secretboard.auth.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class TokenInfoDTO {
    private String accessToken;
    private String refreshToken;
    @Builder
    public TokenInfoDTO(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    
}
