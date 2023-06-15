package com.flab.daily.service;

import com.flab.daily.type.MemberType;
import com.flab.daily.dto.request.MemberRequestDTO;
import com.flab.daily.dao.MemberDAO;
import com.flab.daily.exception.DuplicateCheckException;
import com.flab.daily.exception.ErrorCode;
import com.flab.daily.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberMapper memberMapper;
    private final PasswordEncoder passwordEncoder;


    public void signUp(MemberRequestDTO memberRequestDTO) {

        int isMember = memberMapper.getMember(memberRequestDTO.getEmail());

        if (isMember > 0) {
            throw new DuplicateCheckException(ErrorCode.DUPLICATE_EMAIL);
        }

        MemberDAO memberDAO = MemberDAO.builder()
                .email(memberRequestDTO.getEmail())
                .password(passwordEncoder.encode(memberRequestDTO.getPassword()))
                .nickname(memberRequestDTO.getNickname())
                .memberType(MemberType.USER)
                .build();

        memberMapper.insertMember(memberDAO);
    }
}
