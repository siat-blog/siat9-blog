package com.siat.secretboard.auth.provider;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.stream.Collectors;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.siat.secretboard.auth.dao.mapper.TokenMapper;
import com.siat.secretboard.auth.dao.repository.TokenRepository;
import com.siat.secretboard.auth.domain.TokenEntity;
import com.siat.secretboard.auth.dto.MemberInfo;
import com.siat.secretboard.auth.dto.RefreshTokenDTO;
import com.siat.secretboard.auth.dto.TokenInfoDTO;
import com.siat.secretboard.common.util.JwtUtil;
import com.siat.secretboard.member.dao.repository.MemberRepository;
import com.siat.secretboard.member.domain.MemberEntity;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtProvider {
    @Value("${secretboard.jwt.secret}")
    private String secretKey;

    @Value("${secretboard.config.timezone:Asia/Seoul}")
    public String TIME_ZONE;

    private final TokenMapper tokenMapper;
    private final TokenRepository tokenRepository;
    private final MemberRepository memberRepository;


    private Key key;
    private final long refreshTokenExpiredTime =JwtUtil.getRefreshTokenExpiredTime();
    private final long accessTokenExpiredTime= JwtUtil.getAccessTokenExpiredTime();

    @PostConstruct
    private void init() {
        byte[] keyBytes = this.secretKey.getBytes(StandardCharsets.UTF_8);
        this.key= Keys.hmacShaKeyFor(keyBytes);
    }

    
    // 토큰에서 회원 정보 추출
    public Claims parseClaims(String token) {
        return Jwts.parser().verifyWith((SecretKey) key).build().parseSignedClaims(token).getPayload();
    }
    
    public String createAccessToken(MemberInfo member) throws EntityNotFoundException {
        if(member==null){
            throw new EntityNotFoundException();
        }

        
        LocalDateTime now = LocalDateTime.now(ZoneId.of(TIME_ZONE));
        return Jwts.builder()
                .subject(member.getMemberId())
                .claim("memberIdx",member.getMemberIdx().toString())
                .claim("memberId",member.getMemberId().toString())
                .claim("memberNickName",member.getMemberNickname())
                .claim("boardGrade",member.getBoardName())
                .issuedAt(Date.from(now.atZone(ZoneId.of(TIME_ZONE)).toInstant()))
                .expiration(Date.from(now.plusSeconds(accessTokenExpiredTime).atZone(ZoneId.of(TIME_ZONE)).toInstant())) // set Expire Time
                .signWith(key)
                .compact();
    }
    public String createAccessToken(String refreshToken) throws EntityNotFoundException {
        RefreshTokenDTO tokenInfo = tokenMapper.selectToken(refreshToken);
        if(tokenInfo==null){
            throw new EntityNotFoundException("refreshToken not found");
        }
        MemberEntity member = memberRepository.findById(Long.valueOf(tokenInfo.getMemberIdx())).orElseThrow(()->new EntityNotFoundException("member not found"));
        LocalDateTime now = LocalDateTime.now(ZoneId.of(TIME_ZONE));
        return Jwts.builder()
                .subject(member.getMemberId())
                .claim("memberIdx",member.getMemberIdx().toString())
                .claim("memberId",member.getMemberId().toString())
                .claim("memberNickName",member.getMemberNickname())
                .claim("boardGrade",tokenInfo.getBoardName())
                .issuedAt(Date.from(now.atZone(ZoneId.of(TIME_ZONE)).toInstant()))
                .expiration(Date.from(now.plusSeconds(accessTokenExpiredTime).atZone(ZoneId.of(TIME_ZONE)).toInstant())) // set Expire Time
                .signWith(key)
                .compact();
    }
    
    
    public boolean validateAccessToken(String token) {
        try {
            Claims claims = parseClaims(token);
            return !claims.getExpiration().before(new Date());
            
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            log.info("잘못된 JWT 서명입니다.");
        }
        //        catch (ExpiredJwtException e) {
            //            log.info("만료된 JWT 토큰입니다.");
            //        }
            catch (UnsupportedJwtException e) {
                log.info("지원되지 않는 JWT 토큰입니다.");
            } catch (IllegalArgumentException e) {
                log.info("JWT 토큰이 잘못되었습니다.");
            }
            return false;
        }
        
        public boolean validateRefreshToken(String token) {
            try {
            Claims claims = parseClaims(token);
            return !claims.getExpiration().before(new Date());
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            log.error("오류 내용 {} : aaa {}",e.getMessage(),e.toString());
            log.info("잘못된 JWT 서명입니다.");
            // } catch (ExpiredJwtException e) {
                
            //     log.info("만료된 JWT 토큰입니다.");
            
            // } 
        }
            catch (UnsupportedJwtException e) {
                
                log.info("지원되지 않는 JWT 토큰입니다.");
            } catch (IllegalArgumentException e) {
                
                log.info("JWT 토큰이 잘못되었습니다.");
            }
            return false;
    }


        public TokenInfoDTO createAccessTokenAndRefreshToken(MemberInfo memberInfo) {
            return TokenInfoDTO.builder()
                    .accessToken(createAccessToken(memberInfo))
                    .refreshToken(createRefreshToken(memberInfo))
                    .build();
        }
        public String createRefreshToken(MemberInfo memberInfo) {
    
            LocalDateTime now = LocalDateTime.now(ZoneId.of(TIME_ZONE));
            LocalDateTime expireDate =now.plusSeconds(refreshTokenExpiredTime);
            String refreshToken=Jwts.builder()
                    .subject(memberInfo.getMemberId())
                    .claim("memberIdx",memberInfo.getMemberIdx())
                    .issuedAt(Date.from(now.atZone(ZoneId.of(TIME_ZONE)).toInstant()))
                    .expiration(Date.from(now.plusSeconds(refreshTokenExpiredTime).atZone(ZoneId.of(TIME_ZONE)).toInstant())) // set Expire Time
                    .signWith(key)
                    .compact();
            tokenRepository.save(TokenEntity.builder()
                                .expireDate(expireDate)
                                .refreshToken(refreshToken)
                                .regDate(now)
                                .member(MemberEntity.builder()
                                .memberIdx(Long.parseLong(memberInfo.getMemberIdx()))
                                .build())
                                .build()
                                );
            return refreshToken;
        }
        
        
        public RefreshTokenDTO findRefreshToken(String refreshToken) {
                try {
                        log.debug("토큰 값 {}",refreshToken);
                        RefreshTokenDTO refreshTokenDTO=tokenMapper.selectToken(refreshToken);
                        return refreshTokenDTO;
                    } catch (Exception e) {
                    
            log.info("refreshToken finder error : {}",e.getMessage());
        }
        return null;

    }


    // public String createAndSaveRefreshToken(MemberInfo member) {
    //     LocalDateTime now = LocalDateTime.now(ZoneId.of(TIME_ZONE));
    //     LocalDateTime expireTime=now.plusSeconds(refreshTokenExpiredTime);
    //     String refreshToken= Jwts.builder()
    //             .subject("refreshToken")
    //             .claim("memberIndex",member.get().toString())
    //             .issuedAt(Date.from(now.atZone(ZoneId.of(TIME_ZONE)).toInstant()))
    //             .expiration(Date.from(expireTime.atZone(ZoneId.of(TIME_ZONE)).toInstant())) // set Expire Time
    //             .signWith(key)
    //             .compact();


    //     tokenRepository.save(RefreshTokenEntity.builder()
    //             .refreshToken(refreshToken)
    //             .regDate(LocalDateTime.now())
    //             .expireTime(LocalDateTime.now().plusSeconds(accessTokenExpiredTime))
    //             .build()
    //     );
    //     return refreshToken;
    // }

}
