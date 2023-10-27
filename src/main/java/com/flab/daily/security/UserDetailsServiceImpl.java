package com.flab.daily.security;

import com.flab.daily.dao.MemberDAO;
import com.flab.daily.exception.ErrorCode;
import com.flab.daily.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final MemberMapper memberMapper;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        MemberDAO memberDAO = memberMapper.selectMemberByEmail(email);
        if(memberDAO == null) {
            throw new UsernameNotFoundException(ErrorCode.NOT_FOUND_EMAIL.getMessage());
        }
        return User.builder().username(memberDAO.getEmail())
                .password(memberDAO.getPassword())
                .roles(memberDAO.getMemberType().toString())
                .build();
    }
}
