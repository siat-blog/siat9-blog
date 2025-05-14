package com.siat.secretboard.board.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class BoardRequestDTO {
    @Schema(description = "게시판 고유 인덱스", example = "1")
    private Long boardIdx;
    @Schema(description = "게시판 이름", example = "호빗게시판")
    private String boardName;
    @Schema(description = "게시판 등록 일", example = "2023-10-01")
    private String regDate;
    @Schema(description = "게시판 수정 일", example = "2023-10-02")
    private String updateDate;
    @Schema(description = "게시판 삭제 여부", example = "0")
    private int isDelete;
    @Schema(description = "게시판 설명", example = "호빗에 대한 모든 것을 나누는 게시판입니다.")
    private String boardDescription;
}
