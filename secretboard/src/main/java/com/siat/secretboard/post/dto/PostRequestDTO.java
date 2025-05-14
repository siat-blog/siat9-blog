package com.siat.secretboard.post.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
// 수정과 생성시 BODY에 담겨서 요청.
public class PostRequestDTO {

    @NotBlank(message = "게시글 번호는 필수 입력 항목입니다.")
    private Long id; // 로그인 추가. -> 일단 postIdx에 해당하므로 필요하다.
    
    //private Long memberId; // 

    @NotBlank(message = "제목은 필수 입력 항목입니다.")
    @Size(max = 80, message = "제목은 최대 80자까지 입력 가능합니다.")
    private String title;
    
    @NotBlank(message = "내용은 필수 입력 항목입니다.")
    private String content;

    @NotBlank(message = "작성자는 필수 입력 항목입니다.")
    private String author;

    @NotBlank(message = "그룹 ID는 필수 입력 항목입니다.")
    private Long groupId;
}