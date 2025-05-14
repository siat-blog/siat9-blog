package com.siat.secretboard.board.dto;

import lombok.Data;

@Data
public class BoardRequestDTO {
    private Long boardIdx;
    private String boardName;
    private String regDate;
    private String updateDate;
    private int isDelete;
    private String boardDescription;
}
