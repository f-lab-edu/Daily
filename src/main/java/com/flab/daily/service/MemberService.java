package com.flab.daily.service;

import com.flab.daily.dao.MemberDAO;
import com.flab.daily.dto.request.MemberLoginDTO;
import com.flab.daily.dto.request.MemberRequestDTO;
import com.flab.daily.dto.response.JwtResponseDTO;
import com.flab.daily.utils.dto.ResponseDTO;
import com.flab.daily.utils.exception.DuplicateCheckException;
import com.flab.daily.utils.exception.ErrorCode;
import com.flab.daily.utils.exception.IsExistCheckException;
import com.flab.daily.utils.security.jwt.JwtProvider;
import com.flab.daily.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    public final MemberMapper memberMapper;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

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

    /*로그인 함수*/
    public ResponseDTO login(MemberLoginDTO memberLoginDTO) {
        MemberDAO memberDAO = memberMapper.selectMemberByEmail(memberLoginDTO.getEmail());
        if(memberDAO == null) {
            log.error("ERROR : INVALID EMAIL");
            throw new IsExistCheckException(ErrorCode.NOT_FOUND_EMAIL);
        }
        if(!bCryptPasswordEncoder.matches(memberLoginDTO.getPassword(), memberDAO.getPassword())) {
            log.error("ERROR : LOGIN FAIL");
            throw new IsExistCheckException(ErrorCode.INVALID_ACCOUNT_USER);
        }

        return ResponseDTO.builder().result("success").build();
    }

    public void updateMemberInfo(MemberRequestDTO memberRequestDTO) {
        MemberDAO memberDAO = memberMapper.selectMemberByEmail(memberRequestDTO.getEmail());
        if (memberDAO==null) {
            throw new IsExistCheckException(ErrorCode.NOT_FOUND_EMAIL);
        }

        memberDAO.setPassword(bCryptPasswordEncoder.encode(memberRequestDTO.getPassword()));
        memberDAO.setNickname(memberRequestDTO.getNickname());

        memberMapper.updateMemberInfo(memberDAO);
    }
}
