package com.flab.daily.service;

import com.flab.daily.dao.MemberDAO;
import com.flab.daily.dto.request.MemberLoginDTO;
import com.flab.daily.dto.request.MemberRequestDTO;
import com.flab.daily.dto.response.JwtResponseDTO;
import com.flab.daily.exception.DuplicateCheckException;
import com.flab.daily.exception.ErrorCode;
import com.flab.daily.exception.IsExistCheckException;
import com.flab.daily.security.jwt.JwtProvider;
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
    public JwtResponseDTO login(MemberLoginDTO memberLoginDTO) {
        /*비밀번호 확인*/
        MemberDAO memberDAO = memberMapper.selectMemberByEmail(memberLoginDTO.getEmail());
        if(!bCryptPasswordEncoder.matches(memberLoginDTO.getPassword(), memberDAO.getPassword())) {
            log.error("LOGIN FAIL : 아이디, 비밀번호 불일치");
            throw new IsExistCheckException(ErrorCode.NOT_FOUND_EMAIL);
        }

        /*JWT 생성*/
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(memberLoginDTO.getEmail(),
                        memberLoginDTO.getPassword())
        );
        String accessToken = jwtProvider.generateAccessToken(authentication);

        /*반환 객체*/
        JwtResponseDTO jwtResponseDTO = JwtResponseDTO.builder()
                .result("success")
                .accessToken(accessToken)
                .build();
        return jwtResponseDTO;
    }
}
