package com.siat.secretboard.auth.dao.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.siat.secretboard.auth.dto.RefreshTokenDTO;

@Mapper
public interface TokenMapper {
    public RefreshTokenDTO selectToken(String refreshToken);
}
