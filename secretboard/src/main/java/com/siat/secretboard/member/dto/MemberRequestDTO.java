package com.siat.secretboard.member.dto;

import lombok.Getter;


@Getter
public class MemberRequestDTO {
    private String memberNickname;
    private String memberId;
    private String memberPassword;
    private Long boardIdx;
    
}
