package com.siat.secretboard.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.siat.secretboard.board.dao.mapper.BoardMapper;
import com.siat.secretboard.board.dto.BoardRequestDTO;

@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    private BoardMapper boardMapper;

    @Override
    public List<BoardRequestDTO> readBoardList() {
        return boardMapper.selectBoardList();
    }

    @Override
    public BoardRequestDTO readBoardById(Long boardIdx) {
        return boardMapper.selectBoardById(boardIdx);
    }

    @Override
    public void createBoard(BoardRequestDTO board) {
        boardMapper.insertBoard(board);
    }

    @Override
    public void updateBoard(BoardRequestDTO board) {
        boardMapper.updateBoard(board);
    }
}