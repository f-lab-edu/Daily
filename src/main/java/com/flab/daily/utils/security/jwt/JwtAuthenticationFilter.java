package com.flab.daily.utils.security.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flab.daily.utils.exception.ErrorCode;
import com.flab.daily.utils.exception.ErrorResponse;
import com.flab.daily.utils.exception.JwtCustomException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@Component
/*JWT 토큰으로 인증하고 SecurityContextHolder에 추가하는 필터를 커스텀하는 클래스*/
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtProvider jwtProvider;

    /*SecurityContextHolder에 JWT를 저장*/
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        log.info("JwtAuthFilter 접속");

        String jwt = jwtProvider.resolveToken(request);
        String url = request.getRequestURI();

        if(!((url.equals("/members/login")) || (url.equals("/members/signup")) || (url.equals("/ping")) ||
                (url.startsWith("/meeting/category")) || (url.startsWith("/meeting")) || (url.equals("/")))) {

            if(jwt == null) {
                log.error("NOT EXIST TOKEN.");
                jwtExceptionMessage(response, ErrorCode.NOT_EXIST_TOKEN.getMessage(), ErrorCode.NOT_EXIST_TOKEN.getCode());
                return;
            } else {
                try {
                    /*토큰 검증*/
                    if (StringUtils.hasText(jwt) && jwtProvider.validateToken(jwt)) {
                        Authentication authentication = jwtProvider.getAuthentication(jwt);
                        /*검증 통과하면 SecurityContextHolder에 저장*/
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    }
                } catch(JwtCustomException e) {
                    SecurityContextHolder.clearContext();
                    log.error("INVALID JWT TOKEN.");
                    jwtExceptionMessage(response, e.getErrorCode().getMessage(), e.getErrorCode().getCode());
                    return;
                }
            }
        }
        filterChain.doFilter(request, response); /*다음 필터 체인으로 넘어가도록 함*/
    }

    public void jwtExceptionMessage(HttpServletResponse response, String message, int httpStatus) {
        response.setStatus(httpStatus);
        response.setContentType("application/json");
        try {
            String responseJson = new ObjectMapper().writeValueAsString(new ErrorResponse(httpStatus, message));
            response.getWriter().write(responseJson);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
