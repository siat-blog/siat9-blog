package com.siat.secretboard.member.dto;

import lombok.Builder;
import lombok.Getter;


// @Getter
@Builder
public class MemberResponseDTO {
    // 수정해야함.
    private String memberId; // memberIdx랑 구분된 상태이며, 어떤 것을 이용할지 논의 해봐야함.
    private String memberNickname;
    private String memberPassword;
    private String boardType; // 일단 추가. -> 수정해야함.
    
}
