package com.siat.secretboard.board.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class BoardRequestDTO {
    @Schema(description = "게시판 고유 인덱스", example = "1")
    private Long boardIdx;
    @NotBlank(message = "게시판 이름은 필수입니다.")
    @Schema(description = "게시판 이름", example = "호빗게시판")
    private String boardName;
    @NotBlank(message = "게시판 등록 일은 필수입니다.")
    @Schema(description = "게시판 등록 일", example = "2023-10-01")
    private String regDate;
    @NotBlank(message = "게시판 수정 일은 필수입니다.")
    @Schema(description = "게시판 수정 일", example = "2023-10-02")
    private String updateDate;
    @NotBlank(message = "게시판 삭제 여부는 필수입니다.")
    @Schema(description = "게시판 삭제 여부", example = "0")
    private int isDelete;
    @NotBlank(message = "게시판 설명은 필수입니다.")
    @Schema(description = "게시판 설명", example = "호빗에 대한 모든 것을 나누는 게시판입니다.")
    private String boardDescription;
}
