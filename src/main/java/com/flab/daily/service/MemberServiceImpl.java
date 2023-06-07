package com.flab.daily.service;

import com.flab.daily.dto.MemberType;
import com.flab.daily.dto.request.MemberRequestDTO;
import com.flab.daily.dto.response.MemberResponseDTO;
import com.flab.daily.dao.MemberDAO;
import com.flab.daily.exception.DuplicateCheckException;
import com.flab.daily.exception.ErrorCode;
import com.flab.daily.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberMapper memberMapper;
    private final PasswordEncoder passwordEncoder;


    @Override
    public void signUp(MemberRequestDTO memberRequestDTO) {

        MemberDAO memberDAO = MemberDAO.builder()
                .email(memberRequestDTO.getEmail())
                .password(memberRequestDTO.getPassword())
                .nickname(memberRequestDTO.getNickname())
                .memberType(MemberType.USER)
                .build();

        MemberResponseDTO memberResponseDTO = memberMapper.getMember(memberDAO);

        if (memberResponseDTO != null) {
            throw new DuplicateCheckException(new ErrorCode(HttpStatus.BAD_REQUEST, "이미 존재하는 데이터입니다."));
        }

        this.passwordEncryption(memberDAO);
        memberMapper.insertMember(memberDAO);

    }


    private void passwordEncryption(MemberDAO member) {
        member.setPassword(passwordEncoder.encode(member.getPassword()));
    }
}
