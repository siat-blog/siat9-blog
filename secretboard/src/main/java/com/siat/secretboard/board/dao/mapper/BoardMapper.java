package com.siat.secretboard.board.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.siat.secretboard.board.dto.BoardRequestDTO;

@Mapper
public interface BoardMapper {
    List<BoardRequestDTO> selectBoardList();
    BoardRequestDTO selectBoardById(Long boardIdx);
    void insertBoard(BoardRequestDTO board);
    void updateBoard(BoardRequestDTO board);
}
