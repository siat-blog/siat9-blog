package com.siat.secretboard.auth.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class LoginRequestDTO {
    private String memberId;
    private String memberPassword;
    @Builder
    public LoginRequestDTO(String memberId, String memberPassword) {
        this.memberId = memberId;
        this.memberPassword = memberPassword;
    }
    
}
