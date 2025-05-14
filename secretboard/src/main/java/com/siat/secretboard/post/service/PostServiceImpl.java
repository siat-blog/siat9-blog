package com.siat.secretboard.post.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.siat.secretboard.common.exception.ResourceNotFoundException;
import com.siat.secretboard.post.dao.repository.PostRepository;
import com.siat.secretboard.post.domain.PostEntity;
import com.siat.secretboard.post.dto.PostRequestDTO;
import com.siat.secretboard.post.dto.PostResponseDTO;

import oracle.jdbc.proxy.annotation.Post;


@Service
public class PostServiceImpl implements PostService{
    @Autowired
    private PostRepository postRepository;

    // 생성
    @Transactional
    public PostResponseDTO createPost(PostRequestDTO params) {
        PostEntity.builder()
                    .title(params.getTitle())
                    .content(params.getContent())
                    .createdDate(new Date().getTime()) // 해당 타입 체크
                    .build();
        // Post newPost = new Post(
        //     dto.getTitle(),
        //     dto.getContent(),
        //     dto.getAuthor()
        // );
        Post savedPost = postRepository.save(newPost);
        return convertToResponseDTO(savedPost);
    }

    // 단건 조회
    public PostResponseDTO readPost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found"));
        return convertToResponseDTO(post);
    }

    // 수정
    @Transactional
    public PostResponseDTO updatePost(Long id, PostRequestDTO dto) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found"));
        
        post.setTitle(dto.getTitle());
        post.setContent(dto.getContent());
        post.setAuthor(dto.getAuthor());
        
        Post updatedPost = postRepository.save(post);
        return convertToResponseDTO(updatedPost);
    }

    // 삭제
    @Transactional
    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }

    // Entity → DTO 변환 메서드
    private PostResponseDTO convertToResponseDTO(Post post) {
        return new PostResponseDTO(
            post.getId(),
            post.getTitle(),
            post.getContent(),
            post.getAuthor()
        );
    }
}
