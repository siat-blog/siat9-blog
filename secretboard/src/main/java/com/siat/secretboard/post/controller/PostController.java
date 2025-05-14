package com.siat.secretboard.post.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.siat.secretboard.post.dto.PostRequestDTO;
import com.siat.secretboard.post.dto.PostResponseDTO;
import com.siat.secretboard.post.service.PostService;
import com.siat.secretboard.member.domain.MemberEntity;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/post")
@Validated
public class PostController {
    @Autowired
    private PostService service;

    @GetMapping("/list")
    public ResponseEntity<List<PostResponseDTO>> readPosts(@AuthenticationPrincipal MemberEntity user) {
        List<PostResponseDTO> list = service.readPostsByGroup(user.getGroup().getId());
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostResponseDTO> readPost(@Valid @PathVariable(name = "id") Long idx) {
        PostResponseDTO postResponseDTO = service.readPost(idx);
        return ResponseEntity.ok().body(postResponseDTO);
    }

    @PostMapping
    public ResponseEntity<?> createPost(@Valid @RequestBody PostRequestDTO params, @AuthenticationPrincipal MemberEntity user) {
        if (!user.getGroup().getId().equals(params.getGroupId())) {
            throw new IllegalArgumentException("잘못된 그룹 접근");
        }
        service.createPost(params);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostResponseDTO> updatePost(@Valid @PathVariable(name = "id") Long idx, @RequestBody PostRequestDTO params, @AuthenticationPrincipal MemberEntity user) {
        PostResponseDTO postResponseDTO = service.updatePost(idx, params, user);
        return ResponseEntity.ok().body(postResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePost(@Valid @PathVariable(name = "id") Long idx, @AuthenticationPrincipal MemberEntity user) {
        service.deletePost(idx, user);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<PostResponseDTO>> searchPostList(@Valid @RequestParam String title, @AuthenticationPrincipal MemberEntity user) {
        List<PostResponseDTO> list = service.searchPostListByGroup(title, user.getGroup().getId());
        return ResponseEntity.ok().body(list);
    }
}
