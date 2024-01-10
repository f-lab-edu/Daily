package com.flab.daily.mock;

import com.flab.daily.dao.MemberDAO;
import com.flab.daily.mapper.MemberMapper;
import com.flab.daily.utils.security.jwt.JwtProvider;
import com.flab.daily.utils.type.MemberType;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

@RequiredArgsConstructor
public class WithAuthUserSecurityContextFactory implements WithSecurityContextFactory<WithAuthUser> {

    private final MemberMapper memberMapper;
    private final JwtProvider jwtProvider;

    @Override
    public SecurityContext createSecurityContext(WithAuthUser annotation) {
        String email = annotation.email();

        MemberDAO memberDAO = MemberDAO.builder()
                .email(email)
                .password("test123!@#")
                .memberType(MemberType.USER)
                .nickname("hello")
                .build();

        memberMapper.insertMember(memberDAO);

        jwtProvider.validateToken(jwtProvider.generateAccessToken(email));

        UserDetails userDetails = User.builder().username(email)
                .password("test")
                .roles("USER")
                .build();

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());

        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(usernamePasswordAuthenticationToken);

        return securityContext;
    }
}
