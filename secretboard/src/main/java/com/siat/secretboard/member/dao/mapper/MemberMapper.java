package com.siat.secretboard.member.dao.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.siat.secretboard.auth.dto.LoginRequestDTO;
import com.siat.secretboard.auth.dto.MemberInfo;

@Mapper
public interface MemberMapper {
    public MemberInfo selectMemberInfoByIdAndPassword(LoginRequestDTO memberIdx);
}
