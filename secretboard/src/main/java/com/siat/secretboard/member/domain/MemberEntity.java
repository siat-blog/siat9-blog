package com.siat.secretboard.member.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

import com.siat.secretboard.common.converter.BooleanToIntConverter;

@Entity
@Builder
@Getter
@Setter
@Table(name="member")
@NoArgsConstructor
@AllArgsConstructor
public class MemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MEMBER_SEQ")
    @SequenceGenerator(name="MEMBER_SEQ",sequenceName = "MEMBER_SEQ",allocationSize = 1)
    @Column(name = "member_idx")
    private Long memberIdx;

    @Column(name = "board_idx", nullable = false)
    private Long boardIdx;

    @Column(name = "member_id", nullable = false, length = 50)
    private String memberId;

    @Column(name = "member_password", nullable = false, length = 512)
    private String memberPassword;

    @Column(name = "member_nickname", nullable = false, length = 100)
    private String memberNickname;

    @Column(name = "reg_date", nullable = false, updatable = false)
    private LocalDateTime regDate;

    @Column(name = "update_date", nullable = false)
    private LocalDateTime updateDate;

    @Column(name = "is_delete", nullable = false)
    @Convert(converter = BooleanToIntConverter.class)
    private Boolean isDelete;

    @PrePersist
    protected void onCreate() {
        if (isDelete == null) {
            isDelete = false;
        }
        this.regDate = LocalDateTime.now();
        this.updateDate = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updateDate = LocalDateTime.now();
    }
}