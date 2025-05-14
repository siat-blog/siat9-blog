package com.siat.secretboard.post.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
// 수정과 생성시 BODY에 담겨서 요청.
public class PostRequestDTO {
    private Long id; // 로그인 추가.
    //private Long memberId; // 

    private String title;
    private String content;
    private String author;
}