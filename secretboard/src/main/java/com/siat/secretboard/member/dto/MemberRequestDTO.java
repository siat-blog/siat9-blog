package com.siat.secretboard.member.dto;

import lombok.Getter;
import lombok.ToString;


@Getter
// 회원 생성시만 고려한 DTO
@ToString
public class MemberRequestDTO {
    // 일단 회원가입에 필요한 데이터만 추가. -> 필요시 추가해서 넣자.(다만 너무 많이 차이가 나거나 여러번 요청하는 경우 따로 dto를 만들거나 List를 만드는게 좋다.)
    private String memberId;
    private String memberPassword;
    private String memberNickname;
    private String boardType;
    
}
