package com.siat.secretboard.post.service;

import java.util.List;

import com.siat.secretboard.post.dto.PostRequestDTO;
import com.siat.secretboard.post.dto.PostResponseDTO;
import com.siat.secretboard.member.domain.MemberEntity;

public interface PostService {
    public PostResponseDTO readPost(Long id);
    public PostResponseDTO createPost(PostRequestDTO dto);
    public PostResponseDTO updatePost(Long id, PostRequestDTO dto, MemberEntity user);
    public int deletePost(Long id, MemberEntity user);
    public List<PostResponseDTO> readPostsByGroup(Long groupId);
    public List<PostResponseDTO> searchPostListByGroup(String title, Long groupId);
}
