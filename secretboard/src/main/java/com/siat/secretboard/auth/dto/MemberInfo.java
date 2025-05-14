package com.siat.secretboard.auth.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberInfo {
    public String memberIdx;
    public String memberId;
    public String memberNickname;
    public String boardName;
    @Builder
    public MemberInfo(String memberId, String memberNickname, String boardName) {
        this.memberId = memberId;
        this.memberNickname = memberNickname;
        this.boardName = boardName;
    }
}
