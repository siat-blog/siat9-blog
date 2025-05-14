package com.siat.secretboard.post.service;

import com.siat.secretboard.post.dto.PostRequestDTO;
import com.siat.secretboard.post.dto.PostResponseDTO;

public interface PostService {
    public PostResponseDTO readPost(Long id);
    public PostResponseDTO createPost(PostRequestDTO dto);
    public PostResponseDTO updatePost(Long id, PostRequestDTO dto);
    public void deletePost(Long id); // d
    
}
