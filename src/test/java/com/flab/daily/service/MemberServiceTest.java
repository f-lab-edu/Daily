package com.flab.daily.service;


import com.flab.daily.dao.MemberDAO;
import com.flab.daily.dto.request.MemberRequestDTO;
import com.flab.daily.exception.DuplicateCheckException;
import com.flab.daily.mapper.MemberMapper;
import com.flab.daily.type.MemberType;
import com.flab.daily.utils.SHA256Util;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MemberServiceTest {

    @Mock
    MemberMapper memberMapper;
    @InjectMocks
    MemberService memberService;
    MemberRequestDTO memberRequestDTO;
    MemberDAO memberDAO;

    @BeforeEach
    void init() {
        memberRequestDTO = MemberRequestDTO.builder()
                .email("test12@naver.com")
                .password("test12312")
                .nickname("tese")
                .memberType(MemberType.USER)
                .build();
    }

    public MemberDAO setMemberDAO(MemberRequestDTO memberRequestDTO) {
        memberDAO = MemberDAO.builder()
                .email(memberRequestDTO.getEmail())
                .password(SHA256Util.encrypt(memberRequestDTO.getPassword()))
                .nickname(memberRequestDTO.getNickname())
                .memberType(MemberType.USER)
                .build();
        return memberDAO;
    }


    @Test
    @DisplayName("회원 가입 정상 작동")
    void signUpSuccess() {
        // 이메일이 중복되지 않는 경우 0을 반환
        when(memberMapper.getMember(memberRequestDTO.getEmail())).thenReturn(0);
        memberDAO = setMemberDAO(memberRequestDTO);
        // 삽입이 정상적으로 이루어진 경우 1을 반환
        when(memberMapper.insertMember(memberDAO)).thenReturn(1);
        memberService.signUp(memberRequestDTO);
        // 함수가 정상적으로 실행 되었는지 검사
        verify(memberMapper, times(1)).getMember(memberRequestDTO.getEmail());
        verify(memberMapper, times(1)).insertMember(memberDAO);
    }

    @Test
    @DisplayName("이메일 중복으로 인한 가입 실패")
    void isExistUserByEmail() {
        // 이메일이 중복되었을 경우 1을 반환
        when(memberMapper.getMember(memberRequestDTO.getEmail())).thenReturn(1);
        assertThrows(DuplicateCheckException.class, () -> memberService.signUp(memberRequestDTO));
    }

    @Test
    @DisplayName("insert()가 정상적으로 작동하지 안았을 경우")
    void signUpFailed() {
        // 이메일이 중복되지 않는 경우 0을 반환
        when(memberMapper.getMember(memberRequestDTO.getEmail())).thenReturn(0);
        memberDAO = setMemberDAO(memberRequestDTO);
        // 삽입이 정상적으로 이루어지지 않았을 경우 0을 반환
        when(memberMapper.insertMember(memberDAO)).thenThrow(new RuntimeException());
        // 예외 발생 검증
        assertThrows(RuntimeException.class, () -> memberService.signUp(memberRequestDTO));
    }

}
