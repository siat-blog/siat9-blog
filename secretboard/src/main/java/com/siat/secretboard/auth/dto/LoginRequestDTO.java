package com.siat.secretboard.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LoginRequestDTO {
    @JsonProperty("memberId")
    private String id;
    @JsonProperty("memberPassword")
    private String password;
    @Builder
    public LoginRequestDTO(String id, String password) {
        this.id = id;
        this.password = password;
    }
    
}
