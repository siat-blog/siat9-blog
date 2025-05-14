package com.siat.secretboard.common.filter;

import java.io.IOException;
import java.util.List;

import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.siat.secretboard.auth.dto.RefreshTokenDTO;
import com.siat.secretboard.auth.provider.JwtProvider;
import com.siat.secretboard.common.util.JwtUtil;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    private final JwtProvider jwtProvider;
    private static final List<String> allowUrlList=List.of("/api/auth/refresh","/api/member/signup","/api/auth/login","/favicon.ico");
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        if(allowUrlList.contains(request.getRequestURI())){
            return true;
        }
        return false;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String jwt = JwtUtil.resolveToken(request);
        String requestURI =request.getRequestURI();
        if (StringUtils.hasText(jwt)) {
            if(jwtProvider.validateAccessToken(jwt)) {
              
            }else {
                String refreshTokenCookie="";
                Cookie[] cookies = request.getCookies();
                if(cookies!=null) {
                    for (Cookie cookie : cookies) {
                        if("refresh_token".equals(cookie.getName())) {
                            refreshTokenCookie=cookie.getValue();
                        }
                    }
                    if(StringUtils.hasText(refreshTokenCookie)
                            && jwtProvider.validateRefreshToken(refreshTokenCookie)) {
                        RefreshTokenDTO refreshToken = jwtProvider.findRefreshToken(refreshTokenCookie);
                        if(refreshToken==null) return;
                        response.setHeader(JwtUtil.AUTHORIZATION_HEADER, jwtProvider.createAccessToken(refreshToken.getMemberIdx(),refreshToken.getBoard, refreshToken.getPlatform()));
                    }
                }

            }
        } else {
            log.debug("유효한 JWT 토큰이 없습니다, uri: {}", requestURI);
            filterChain.doFilter(request, response);
            return;
        }
        log.debug("필터이동");
        filterChain.doFilter(request, response);
    }
}
