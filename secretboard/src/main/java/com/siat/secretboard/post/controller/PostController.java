package com.siat.secretboard.post.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.siat.secretboard.auth.annotation.SiatAuthMember;
import com.siat.secretboard.auth.dto.MemberInfo;
import com.siat.secretboard.member.dto.MemberRequestDTO;
import com.siat.secretboard.member.service.MemberService;
import com.siat.secretboard.post.dto.PostRequestDTO;
import com.siat.secretboard.post.dto.PostResponseDTO;
import com.siat.secretboard.post.service.PostService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/post")
public class PostController {
    @Autowired
    private PostService service;

    // 게시판에서 전체게시글 불러오는 것도 post 도메인에서 진행하는게 맞지 않나?

    // 게시판에서 게시글 검색하는 것도 post 도메인에서 진행하는게 맞지 않나?

    // 일단 그거는 어떻게? -> 요청할때 확실하게 자기것만 체크할 수 있도록 세팅.
    @GetMapping("/list")
    public ResponseEntity<List<PostResponseDTO>> readPosts(@SiatAuthMember MemberInfo member) {
        //TODO: process POST request
        System.out.println("debug >>> signup(ctrl) body!!!");

        // service
        /*responseDTO가 맞는지 모르겠음. 그냥 여부만 보내주면 될 것 같은데?*/
        List<PostResponseDTO> list = service.readPosts(member);
        
        return ResponseEntity.ok().body(list);
    }


    // 
    @GetMapping("/{id}")
    public ResponseEntity<PostResponseDTO> readPost(@Valid @PathVariable(name = "id") Long idx, @SiatAuthMember MemberInfo member) { // Long타입
        //TODO: process POST request
        System.out.println("debug >>> signup(ctrl) body!!!");

        // service
        /*responseDTO가 맞는지 모르겠음. 그냥 여부만 보내주면 될 것 같은데?*/
        PostResponseDTO postResponseDTO = service.readPost(idx,member);
        
        return ResponseEntity.ok().body(postResponseDTO);
    }

    @PostMapping
    public ResponseEntity createPost(@Valid @RequestBody PostRequestDTO params, @SiatAuthMember MemberInfo member) {
        //TODO: process POST request
        System.out.println("debug >>> signup(ctrl) body!!!"+member.getMemberNickname());
        if(member!=null){
            params.setAuthor(member.getMemberNickname());
            params.setMemberId(Long.valueOf(member.getMemberIdx()));
            // service
            /*responseDTO가 맞는지 모르겠음. 그냥 여부만 보내주면 될 것 같은데?*/
            PostResponseDTO postResponseDTO = service.createPost(params);
        }
        
        return ResponseEntity.ok().build(); // 해당 코드 수정
    }
    @PutMapping("/{id}")
    public ResponseEntity<PostResponseDTO> updatePost(@Valid @PathVariable(name = "id") Long idx, @RequestBody PostRequestDTO params) {
        //TODO: process POST request
        System.out.println("debug >>> signup(ctrl) body!!!");

        // service
        /*responseDTO가 맞는지 모르겠음. 그냥 여부만 보내주면 될 것 같은데?*/
        PostResponseDTO postResponseDTO = service.updatePost(idx, params);
        
        return ResponseEntity.ok().body(postResponseDTO);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Integer> deletePost(@Valid @PathVariable(name = "id") Long idx) {
        //TODO: process POST request
        System.out.println("debug >>> login(ctrl) body!!!");
        
        // service
        /*responseDTO가 맞는지 모르겠음. 그냥 여부만 보내주면 될 것 같은데?*/
        Integer response = service.deletePost(idx); // int -> Integer (Auto-boxing)

        // 
        return ResponseEntity.ok().body(response); // 해당 코드 수정
    }

    @GetMapping("search")
    public ResponseEntity<List<PostResponseDTO>> searchPostList(@Valid @RequestParam String title, @SiatAuthMember MemberInfo member) {
        //TODO: process POST request
        System.out.println("debug >>> signup(ctrl) body!!!");

        // service
        /*responseDTO가 맞는지 모르겠음. 그냥 여부만 보내주면 될 것 같은데?*/
        List<PostResponseDTO> list = service.searchPostList(title,member);
        
        return ResponseEntity.ok().body(list);
    }
}
