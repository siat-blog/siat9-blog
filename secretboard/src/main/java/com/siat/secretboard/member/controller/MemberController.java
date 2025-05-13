package com.siat.secretboard.member.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.siat.secretboard.member.dto.MemberRequestDTO;
import com.siat.secretboard.member.dto.MemberResponseDTO;
import com.siat.secretboard.member.service.MemberService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/member")
public class MemberController {
    @Autowired
    private MemberService service;

    @PostMapping("/signup")
    public ResponseEntity<MemberResponseDTO> signup(@RequestBody MemberRequestDTO params) {
        //TODO: process POST request
        System.out.println("debug >>> signup(ctrl) body!!!");

        // service
        /*responseDTO가 맞는지 모르겠음. 그냥 여부만 보내주면 될 것 같은데?*/
        MemberResponseDTO memberResponseDTO = service.signupService(params);
        
        return ResponseEntity.ok().body(memberResponseDTO);
    }
    @PostMapping("/login")
    public ResponseEntity<MemberResponseDTO>  login(@RequestBody MemberRequestDTO params) {
        //TODO: process POST request
        System.out.println("debug >>> login(ctrl) body!!!");
        
        // service
        /*responseDTO가 맞는지 모르겠음. 그냥 여부만 보내주면 될 것 같은데?*/
        service.loginService(params);

        // 
        return null;
    }
}
