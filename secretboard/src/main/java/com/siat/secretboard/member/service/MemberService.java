package com.siat.secretboard.member.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.siat.secretboard.member.dto.MemberRequestDTO;
import com.siat.secretboard.member.dto.MemberResponseDTO;

@Service
public interface MemberService {

    public MemberResponseDTO signup(MemberRequestDTO params);
    public MemberResponseDTO login(MemberRequestDTO params);
    
}
