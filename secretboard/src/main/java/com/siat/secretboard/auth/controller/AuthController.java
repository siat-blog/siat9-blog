package com.siat.secretboard.auth.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.siat.secretboard.auth.dto.LoginRequestDTO;
import com.siat.secretboard.auth.dto.MemberInfo;
import com.siat.secretboard.auth.dto.TokenInfoDTO;
import com.siat.secretboard.auth.service.AuthService;
import com.siat.secretboard.common.util.JwtUtil;
import com.siat.secretboard.member.domain.MemberEntity;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody LoginRequestDTO loginRequest,HttpServletResponse response){
        if(!StringUtils.hasText(loginRequest.getId())||!StringUtils.hasText(loginRequest.getPassword())){
            return ResponseEntity.badRequest().build();
        }
        TokenInfoDTO tokenInfo=authService.login(loginRequest);
        if(tokenInfo!=null){
             Cookie cookie = new Cookie("refresh_token", tokenInfo.getRefreshToken());
            cookie.setHttpOnly(true);
            cookie.setMaxAge(JwtUtil.getRefreshTokenExpiredTime());
            cookie.setPath("/");
            // cookie.setSecure(true); // https 적용시 활성화
            response.addCookie(cookie);
            response.setHeader(JwtUtil.AUTHORIZATION_HEADER, JwtUtil.JWT_TYPE+tokenInfo.getAccessToken());
            return ResponseEntity.ok().build();
        }
        
        return ResponseEntity.badRequest().build();
    }

    
}
