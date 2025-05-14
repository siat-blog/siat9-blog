package com.siat.secretboard.auth.service;

import com.siat.secretboard.auth.dto.LoginRequestDTO;
import com.siat.secretboard.auth.dto.MemberInfo;
import com.siat.secretboard.auth.dto.TokenInfoDTO;
import com.siat.secretboard.member.domain.MemberEntity;

public interface AuthService {
    public TokenInfoDTO login(LoginRequestDTO loginRequest);
}
