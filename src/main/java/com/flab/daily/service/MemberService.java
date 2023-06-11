package com.flab.daily.service;

import com.flab.daily.constant.MemberType;
import com.flab.daily.dto.request.MemberRequestDTO;
import com.flab.daily.dao.MemberDAO;
import com.flab.daily.exception.DuplicateCheckException;
import com.flab.daily.exception.ErrorCode;
import com.flab.daily.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberMapper memberMapper;


    public void signUp(MemberRequestDTO memberRequestDTO) {

        MemberDAO memberDAO = MemberDAO.builder()
                .email(memberRequestDTO.getEmail())
                .password(memberRequestDTO.getPassword())
                .nickname(memberRequestDTO.getNickname())
                .memberType(MemberType.USER)
                .build();

        int isMember = memberMapper.getMember(memberDAO);

        if (isMember > 0) {
            throw new DuplicateCheckException(ErrorCode.DUPLICATE_EMAIL);
        }

        memberMapper.insertMember(memberDAO);
    }
}
