package com.siat.secretboard.post.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

// 읽기에 대해서 요청 + 업데이트은 일단 어떻게 될까?
// 생성과 삭제 -> LIST로~!

// id 읽기,(수정) + ( 생성, )
public class PostResponseDTO {
    private Long id;
    private String title;
    private String content;
    private String author;
}