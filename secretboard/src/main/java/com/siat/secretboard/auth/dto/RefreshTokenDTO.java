package com.siat.secretboard.auth.dto;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;


@Getter
@ToString
public class RefreshTokenDTO {
    private String refreshToken;
    private LocalDateTime expireDate;
    private LocalDateTime regDate;
    private Long memberIdx;
    private String boardName;
    @Builder
    public RefreshTokenDTO(String refreshToken, LocalDateTime expireDate, LocalDateTime regDate, Long memberIdx,String boardName) {
        this.refreshToken = refreshToken;
        this.expireDate = expireDate;
        this.regDate = regDate;
        this.memberIdx = memberIdx;
        this.boardName=boardName;
    }
    
}
