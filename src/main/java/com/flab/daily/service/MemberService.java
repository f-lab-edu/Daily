package com.flab.daily.service;


import com.flab.daily.dto.Member;
import com.flab.daily.dto.MemberType;
import com.flab.daily.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberMapper memberMapper;

    public void signUp(Member memberDto) {
        Member member = Member.builder()
                .email(memberDto.getEmail())
                .password(memberDto.getPassword())
                .nickname(memberDto.getNickname())
                .memberType(MemberType.USER)
                .build();
        memberMapper.insertMember(member);
    }

}
