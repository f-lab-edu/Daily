package com.flab.daily.jwt;


import com.flab.daily.exception.JwtCustomException;
import com.flab.daily.security.MemberDetailsService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import com.flab.daily.exception.ErrorCode;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class JwtProvider {

    @Value("${jwt.secret}") /*application.yaml에 설정해놓은 key*/
    private String key;
    @Value(("${jwt.token-length}"))
    private long expireTime;

    private final MemberDetailsService memberDetailsService;


    private Key getSecretKey(String key) {
        byte[] KeyBytes = key.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(KeyBytes);
    }

    /*Access Token 생성 함수*/
    public String generateAccessToken(Authentication authentication) {  /*Authentication 클래스를 인자로 받음*/
        /*사용자 권한 확인*/
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(", "));

        long currentTime = (new Date()).getTime();

        /*JWT Payload에 들어갈 정보*/
        Claims claims = Jwts.claims()
                .setSubject("access_token")
                .setSubject(authentication.getName())
                .setIssuedAt(new Date())
                .setExpiration(new Date(currentTime+expireTime));
        claims.put("auth", authorities);

        /*토큰 생성*/
        String accessToken = Jwts.builder()
                /*Header*/
                .setHeaderParam("alg", "HS256")
                .setHeaderParam("type", "JWT")
                /*Payload*/
                .setClaims(claims)
                /*Signature*/
                .signWith(SignatureAlgorithm.HS256, key)
                .compact();

        return accessToken;
    }

    /*request header로부터 토큰 정보 갖고 오는 함수*/
    String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization"); /*Authorization: Bearer (JWT String값)*/
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    /*토큰으로부터 사용자 정보 확인 함수*/
    public Authentication getAuthentication(String accessToken) {
        Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(accessToken).getBody();
        if (claims.get("auth") == null) {
            throw null;
        }
        UserDetails memberDetails = memberDetailsService.loadUserByUsername(claims.getSubject());
        return new UsernamePasswordAuthenticationToken(memberDetailsService, "", memberDetails.getAuthorities());
    }

    /*토큰 유효성 검증 확인 함수 : 기간, 만료일자*/
    public boolean validateToken(String jwt) {
        try {
            Jwts.parser().setSigningKey(key).parseClaimsJws(jwt);
            return true;
        } catch (Exception e) {
            throw new JwtCustomException(ErrorCode.INVALID_TOKEN);
        }
    }
}
