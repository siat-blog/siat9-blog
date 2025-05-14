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

import oracle.jdbc.proxy.annotation.Post;


@Service
public class PostServiceImpl implements PostService{
    @Autowired
    private PostRepository postRepository;

    // 생성
    @Transactional
    public PostResponseDTO createPost(PostRequestDTO params) {
        PostEntity entity = PostEntity.builder()
                    // memberidx
                    .title(params.getTitle())
                    .content(params.getContent())
                    .author(params.getAuthor())
                    .build();
        
        PostEntity savedPost =  postRepository.save(entity);
        return convertToResponseDTO(savedPost);
    }

    // 단건 조회
    public PostResponseDTO readPost(Long id) {
        PostEntity entity = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found"));
        return convertToResponseDTO(entity);
    }

    // 수정
    @Transactional
    public PostResponseDTO updatePost(Long id, PostRequestDTO dto) {
        PostEntity entity = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found"));
        
        entity.setTitle(dto.getTitle());
        entity.setContent(dto.getContent());
        entity.setAuthor(dto.getAuthor());
        // update를 체크해야한다.
        
        PostEntity updatedPost = postRepository.save(entity);
        return convertToResponseDTO(updatedPost);
    }

    // 삭제
    @Transactional
    // void를 어떻게 처리할까? string으로 남겨줄까?
    public int deletePost(Long id) {
        // 일단 해당 행을 찾아야한다. 행을 찾아서 가져온다.  -> 생성, 수정(지금은 삭제도 논리적 삭제)
        // 행을 가져온다. entity 코드가 존재해야함. 
        // id로 PostEntity 조회
        PostEntity entity = postRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Post not found"));

        // is_delete를 0 에서 1로 변경할꺼야. 우선 삭제된 행을 삭제 또 시킬 수 있어서 일단 0인 것에 대해 삭제
        if (entity.getIsDelete()) { // 이미 1이면 true로 삭제 적용할 필요 x
            return 0; // 삭제 동작 수행 하지 않음. -> 이미 삭제된걸수도 있고 삭제과정 에러가 난걸수도 있음
        }

        // 수정(지금은 삭제도 논리적 삭제)
        entity.setIsDelete(true);
        postRepository.save(entity);

        return 1;

    }

    public List<PostResponseDTO> readPosts(){
        // 전체리스트 불러오기 -> 대신 현재 boardIdx를 기준으로 불러와야한다.
        // postRepository.

        List<PostEntity> posts = postRepository.findAll()
                .stream()
                .filter(post -> !post.getIsDelete()) // 삭제되지 않은 게시글만 필터링
                //.filter(/*여기서 post랑 member가 연결되어있어서 이를 토대로 비교 가능하면 된다. */)
                .collect(Collectors.toList());

        return posts.stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }
    public List<PostResponseDTO> searchPostList(String title){
        // 검색하기
        // postRepository.

        return null;
    }
    // Entity → DTO 변환 메서드
    private PostResponseDTO convertToResponseDTO(PostEntity savedPost) {
        return new PostResponseDTO(
            savedPost.getId(),
            // savedPost.getMemberId(),
            savedPost.getTitle(),
            savedPost.getContent(),
            savedPost.getAuthor()
        );
    }

}
