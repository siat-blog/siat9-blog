package com.siat.secretboard.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.siat.secretboard.board.dto.BoardRequestDTO;
import com.siat.secretboard.board.service.BoardService;

@RestController
@RequestMapping("/api/board")
public class BoardController {
    @Autowired
    private BoardService boardService;

    @GetMapping
    public List<BoardRequestDTO> readBoards() {
        return boardService.readBoardList();
    }

    @GetMapping("/{id}")
    public BoardRequestDTO readBoard(@PathVariable("id") int id) {
        return boardService.readBoardById(id);
    }

    @PostMapping("/create")
    public void createBoard(@RequestBody BoardRequestDTO board) {
        boardService.createBoard(board);
    }

    @PutMapping("/{id}")
    public void updateBoard(@PathVariable("id") int id, @RequestBody BoardRequestDTO board) {
        board.setBoardIdx(id);
        boardService.updateBoard(board);
    }

    @DeleteMapping("/{id}/delete")
    public void deleteBoard(@PathVariable("id") int id) {
        boardService.deleteBoard(id);
    }
    
}
