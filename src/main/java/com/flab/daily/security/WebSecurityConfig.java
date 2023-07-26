package com.flab.daily.security;

import com.flab.daily.jwt.JwtAuthenticationFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Slf4j
/*스프링 시큐리티 설정을 위한 빈 생성을 위한 어노테이션*/
@Configuration
/*모든 URL 요청이 해당 클래스를 거치게 하여 스프링 시큐리티의 보안을 활성화 시켜 처리되게 하는 어노테이션
-> 내부적으로 SpringSecurityFilterChain이 동작하여 모든 URL에 적용됨*/
@EnableWebSecurity
public class WebSecurityConfig {

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration
    ) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    /*암호화에 사용될 Bcrypt 빈 생성*/
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /*스프링 시큐리티 체인 빈 생성 - 요청 URL 설정*/
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable);

        httpSecurity.authorizeHttpRequests((request) -> request
                        .requestMatchers("/", "/login", "/members", "/meeting").permitAll() /*메인, 소모임, 로그인, 회원가입 페이지 외 제한*/
                .anyRequest().authenticated());


        /*Session 비활성화*/
        httpSecurity.sessionManagement((session) -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        httpSecurity.addFilterAfter(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }
}
