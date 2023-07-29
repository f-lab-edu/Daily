package com.flab.daily.security.jwt;

import com.flab.daily.exception.ErrorCode;
import com.flab.daily.exception.JwtCustomException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
/*JWT 토큰으로 인증하고 SecurityContextHolder에 추가하는 필터를 커스텀하는 클래스*/
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtProvider jwtProvider;

    /*SecurityContextHolder에 JWT를 저장하는 함수*/
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String jwt = jwtProvider.resolveToken(request);
        try {
            /*토큰 검증*/
            if (StringUtils.hasText(jwt) && jwtProvider.validateToken(jwt)) {
                Authentication authentication = jwtProvider.getAuthentication(jwt);
                /*검증 통과하면 SecurityContextHolder에 저장*/
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch(JwtCustomException e) {
            SecurityContextHolder.clearContext();
            response.sendError(ErrorCode.INVALID_TOKEN.getCode(), ErrorCode.INVALID_TOKEN.getMessage());
            return;
        }
        filterChain.doFilter(request, response); /*다음 필터 체인으로 넘어가도록 함*/
    }
}
