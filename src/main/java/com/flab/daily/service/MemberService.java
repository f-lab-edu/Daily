package com.flab.daily.service;

import com.flab.daily.dao.MemberDAO;
import com.flab.daily.dto.request.MemberRequestDTO;
import com.flab.daily.exception.IsExistUserByEmail;
import com.flab.daily.exception.ErrorCode;
import com.flab.daily.mapper.MemberMapper;
import com.flab.daily.type.MemberType;
import com.flab.daily.utils.SHA256Util;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    public final MemberMapper memberMapper;


    public void signUp(MemberRequestDTO memberRequestDTO) {
        int isMember = memberMapper.getMember(memberRequestDTO.getEmail());
        if (isMember > 0) {
            throw new IsExistUserByEmail(ErrorCode.IS_EXIST_USER_BY_EMAIL);
        }
        MemberDAO memberDAO = MemberDAO.builder()
                .email(memberRequestDTO.getEmail())
                .password(SHA256Util.encrypt(memberRequestDTO.getPassword()))
                .nickname(memberRequestDTO.getNickname())
                .memberType(MemberType.USER)
                .build();
        memberMapper.insertMember(memberDAO);
    }
}
