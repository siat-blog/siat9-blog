package com.siat.secretboard.auth.domain;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.siat.secretboard.auth.dto.RefreshTokenDTO;
import com.siat.secretboard.member.domain.MemberEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
@Table(name="token")
@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
public class RefreshTokenEntity {
    @Id
    private String refreshToken;
    @Column(name = "expire_date",nullable = false)
    private LocalDateTime expireDate;
    @CreatedDate
    @Column(name= "reg_date", nullable = false)
    private LocalDateTime regDate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_idx")
    private MemberEntity member;
    
    @Builder
    public RefreshTokenEntity(String refreshToken, LocalDateTime expireDate, LocalDateTime regDate, MemberEntity member) {
        this.refreshToken = refreshToken;
        this.expireDate = expireDate;
        this.regDate = regDate;
        this.member = member;
    }

    public RefreshTokenDTO toRefreshTokenDTO() {
      return RefreshTokenDTO.builder()
      .expireDate(this.expireDate)
      .refreshToken(this.refreshToken)
      .regDate(regDate)
      .build();
    }
}
