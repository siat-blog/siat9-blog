package com.siat.secretboard.member.dto;

import lombok.Builder;
import lombok.Getter;


// @Getter
@Builder
public class MemberResponseDTO {
    // 수정해야함.
    private String memberNickname;
    private Long memberId;
    private String memberPassword;
    private String boardType; // 일단 추가. -> 수정해야함.
    
}
