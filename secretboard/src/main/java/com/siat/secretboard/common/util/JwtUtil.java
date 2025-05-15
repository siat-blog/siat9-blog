package com.siat.secretboard.common.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class JwtUtil {
    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String JWT_TYPE ="Bearer ";

    private static int accessTokenExpiredTime=20;
    private static int refreshTokenExpiredTime=60*60*24*1;



    public static String resolveToken(HttpServletRequest request) {
        String bearerToken= request.getHeader(AUTHORIZATION_HEADER);

        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(JWT_TYPE)) {
            return bearerToken.substring(7);
        }

        return null;
    }
    public static String resolveToken(String bearerToken) {

        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(JWT_TYPE)) {
            return bearerToken.substring(7);
        }

        return null;
    }
    public static int getAccessTokenExpiredTime() {
        return (int) (accessTokenExpiredTime>=Integer.MAX_VALUE?60*60*24*365:accessTokenExpiredTime);
    }

    public static int getRefreshTokenExpiredTime() {
        return (int) (refreshTokenExpiredTime>=Integer.MAX_VALUE?60*60*24*365:refreshTokenExpiredTime);
    }
}