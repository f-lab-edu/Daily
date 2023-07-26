package com.flab.daily.security;


import com.flab.daily.dao.MemberDAO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class MemberDetail implements UserDetails {

    private String email;
    private String password;
    private String role;

    public MemberDetail(MemberDAO memberDAO) {
        this.email = memberDAO.getEmail();
        this.password = memberDAO.getPassword();
        this.role = "ROLE_" + memberDAO.getMemberType();
    }

    /*사용자에게 부여된 권한을 지정*/
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(this.role));
    }

    /*계정 비밀번호*/
    @Override
    public String getPassword() { return this.password; }

    /*계정 이메일*/
    @Override
    public String getUsername() {
        return this.email;
    }

    /*계정 만료 여부*/
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /*계정 잠금 상태 여부*/
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /*비밀번호 만료 유무*/
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /*계정 활성화 유무*/
    @Override
    public boolean isEnabled() {
        return true;
    }
}