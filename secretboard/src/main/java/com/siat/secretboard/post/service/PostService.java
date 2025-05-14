package com.siat.secretboard.post.service;

import java.util.List;

import com.siat.secretboard.post.dto.PostRequestDTO;
import com.siat.secretboard.post.dto.PostResponseDTO;

public interface PostService {
    public PostResponseDTO readPost(Long id);
    public PostResponseDTO createPost(PostRequestDTO dto);
    public PostResponseDTO updatePost(Long id, PostRequestDTO dto);
    public int deletePost(Long id); // d
    public List<PostResponseDTO> readPosts();
    public List<PostResponseDTO> searchPostList();
    
}
