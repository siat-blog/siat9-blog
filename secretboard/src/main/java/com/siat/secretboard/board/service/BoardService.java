package com.siat.secretboard.board.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.siat.secretboard.board.dto.BoardRequestDTO;
@Service
public interface BoardService {
    List<BoardRequestDTO> readBoardList();
    BoardRequestDTO readBoardById(Long boardIdx);
    void createBoard(BoardRequestDTO board);
    void updateBoard(BoardRequestDTO board);
}
