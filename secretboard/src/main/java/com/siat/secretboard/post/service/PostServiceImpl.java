package com.siat.secretboard.post.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.siat.secretboard.common.exception.ResourceNotFoundException;
import com.siat.secretboard.post.dao.repository.PostRepository;
import com.siat.secretboard.post.domain.PostEntity;
import com.siat.secretboard.post.dto.PostRequestDTO;
import com.siat.secretboard.post.dto.PostResponseDTO;
import com.siat.secretboard.member.domain.MemberEntity;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepository postRepository;

    @Transactional
    public PostResponseDTO createPost(PostRequestDTO params) {
        PostEntity entity = PostEntity.builder()
                    .title(params.getTitle())
                    .content(params.getContent())
                    .author(params.getAuthor())
                    .group(params.getGroup())
                    .build();
        PostEntity savedPost = postRepository.save(entity);
        return convertToResponseDTO(savedPost);
    }

    public PostResponseDTO readPost(Long id) {
        PostEntity entity = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found"));
        return convertToResponseDTO(entity);
    }

    @Transactional
    public PostResponseDTO updatePost(Long id, PostRequestDTO dto, MemberEntity user) {
        PostEntity entity = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found"));
        if (!entity.getGroup().getId().equals(user.getGroup().getId())) {
            throw new IllegalArgumentException("잘못된 그룹 접근");
        }
        entity.setTitle(dto.getTitle());
        entity.setContent(dto.getContent());
        PostEntity updatedPost = postRepository.save(entity);
        return convertToResponseDTO(updatedPost);
    }

    @Transactional
    public int deletePost(Long id, MemberEntity user) {
        PostEntity entity = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found"));
        if (!entity.getGroup().getId().equals(user.getGroup().getId())) {
            throw new IllegalArgumentException("잘못된 그룹 접근");
        }
        if (entity.getIsDelete()) {
            return 0;
        }
        entity.setIsDelete(true);
        postRepository.save(entity);
        return 1;
    }

    public List<PostResponseDTO> readPostsByGroup(Long groupId) {
        List<PostEntity> posts = postRepository.findAll()
                .stream()
                .filter(post -> !post.getIsDelete() && post.getGroup().getId().equals(groupId))
                .collect(Collectors.toList());
        return posts.stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    public List<PostResponseDTO> searchPostListByGroup(String title, Long groupId) {
        List<PostEntity> posts = postRepository.searchByTitleAndGroup(title, groupId);
        return posts.stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    private PostResponseDTO convertToResponseDTO(PostEntity savedPost) {
        return new PostResponseDTO(
            savedPost.getId(),
            savedPost.getTitle(),
            savedPost.getContent(),
            savedPost.getAuthor()
        );
    }
}
