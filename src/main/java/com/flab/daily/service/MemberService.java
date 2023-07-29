package com.flab.daily.service;

import com.flab.daily.dao.MemberDAO;
import com.flab.daily.dto.request.MemberRequestDTO;
import com.flab.daily.exception.DuplicateCheckException;
import com.flab.daily.exception.ErrorCode;
import com.flab.daily.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    public final MemberMapper memberMapper;
    public final BCryptPasswordEncoder bCryptPasswordEncoder;

    public void signUp(MemberRequestDTO memberRequestDTO) {
        int isMember = memberMapper.getMember(memberRequestDTO.getEmail());
        if (isMember > 0) throw new DuplicateCheckException(ErrorCode.IS_EXIST_USER_BY_EMAIL);

        MemberDAO memberDAO = MemberDAO.builder()
                .email(memberRequestDTO.getEmail())
                .password(bCryptPasswordEncoder.encode(memberRequestDTO.getPassword()))
                .nickname(memberRequestDTO.getNickname())
                .memberType(memberRequestDTO.getMemberType())
                .build();

        memberMapper.insertMember(memberDAO);
    }
}
