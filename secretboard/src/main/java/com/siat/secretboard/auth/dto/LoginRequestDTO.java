package com.siat.secretboard.auth.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class LoginRequestDTO {
    private String id;
    private String password;
    @Builder
    public LoginRequestDTO(String id, String password) {
        this.id = id;
        this.password = password;
    }
    
}
