package com.siat.secretboard.common.filter;

import java.io.IOException;
import java.util.List;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.siat.secretboard.auth.dto.MemberInfo;
import com.siat.secretboard.auth.dto.RefreshTokenDTO;
import com.siat.secretboard.auth.provider.JwtProvider;
import com.siat.secretboard.common.util.JwtUtil;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Component
public class JwtFilter extends OncePerRequestFilter {
    private final JwtProvider jwtProvider;
    private static final List<String> allowUrlList=List.of("/api/auth/refresh","/api/member/signup","/api/auth/login","/favicon.ico");
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        if(request.getMethod().equals(HttpMethod.OPTIONS)){
            return true;
        }
        if(request.getRequestURI().startsWith("/swagger-ui")|| request.getRequestURI().startsWith("/v3/api-docs")){
            return true;
        }
        if(allowUrlList.contains(request.getRequestURI())){
            return true;
        }
        return false;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String jwt = JwtUtil.resolveToken(request);
        String requestURI =request.getRequestURI();
        log.debug("토큰 확인 : {}",jwt);
        if (StringUtils.hasText(jwt)) {
            if(jwtProvider.validateAccessToken(jwt)) {
                Claims accessTokenInfo =jwtProvider.parseClaims(jwt);
              request.setAttribute("memberInfo", MemberInfo.builder()
                                            .boardName(accessTokenInfo.get("boardGrade",String.class))
                                            .memberId(accessTokenInfo.getSubject())
                                            .memberIdx(accessTokenInfo.get("memberIdx",String.class))
                                            .memberNickname(accessTokenInfo.get("memberNickname",String.class))
                                            .build());
                    filterChain.doFilter(request, response);
                    return;
            }else {
                String refreshTokenCookie="";
                Cookie[] cookies = request.getCookies();
                if(cookies!=null) {
                    for (Cookie cookie : cookies) {
                        if("refresh_token".equals(cookie.getName())) {
                            refreshTokenCookie=cookie.getValue();
                        }
                    }
                    if(StringUtils.hasText(refreshTokenCookie)){
                        String newAccessToken = jwtProvider.createAccessToken(refreshTokenCookie);
                        Claims tokenInfo =jwtProvider.parseClaims(newAccessToken);
                        if(StringUtils.hasText(newAccessToken)){
                            log.debug("토큰 재발급 : {}",newAccessToken);
                            request.setAttribute("memberInfo", MemberInfo.builder()
                                            .boardName(tokenInfo.get("boardGrade",String.class))
                                            .memberId(tokenInfo.getSubject())
                                            .memberIdx(tokenInfo.get("memberIdx",String.class))
                                            .memberNickname(tokenInfo.get("memberNickname",String.class))
                                            .build());
                            response.setHeader(JwtUtil.AUTHORIZATION_HEADER, JwtUtil.JWT_TYPE+newAccessToken);
                            
                            filterChain.doFilter(request, response);
                            return;
                        }
                    }
                }

            }
        } else {
            log.debug("유효한 JWT 토큰이 없습니다, uri: {}", requestURI);
            
        }
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
        log.debug("필터이동");
        return;
        // filterChain.doFilter(request, response);
    }
}
