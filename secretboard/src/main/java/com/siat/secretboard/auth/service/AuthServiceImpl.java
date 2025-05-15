package com.siat.secretboard.auth.service;

import org.springframework.stereotype.Service;

import com.siat.secretboard.auth.dto.LoginRequestDTO;
import com.siat.secretboard.auth.dto.MemberInfo;
import com.siat.secretboard.auth.dto.TokenInfoDTO;
import com.siat.secretboard.auth.provider.JwtProvider;
import com.siat.secretboard.member.dao.mapper.MemberMapper;
import com.siat.secretboard.member.domain.MemberEntity;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{
    private final MemberMapper memberMapper;
    private final JwtProvider jwtProvider;
    @Override
    public TokenInfoDTO login(LoginRequestDTO loginRequest) {
        MemberInfo memberInfo =memberMapper.selectMemberInfoByIdAndPassword(loginRequest);
        TokenInfoDTO token =jwtProvider.createAccessTokenAndRefreshToken(memberInfo);
        return token;
    }
}
