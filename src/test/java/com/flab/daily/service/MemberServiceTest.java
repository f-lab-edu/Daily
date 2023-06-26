package com.flab.daily.service;


import com.flab.daily.dao.MemberDAO;
import com.flab.daily.dto.request.MemberRequestDTO;
import com.flab.daily.exception.ErrorCode;
import com.flab.daily.exception.IsExistUserByEmail;
import com.flab.daily.mapper.MemberMapper;
import com.flab.daily.type.MemberType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MemberServiceTest {

    @Mock
    MemberMapper memberMapper;

    MemberRequestDTO memberRequestDTO;

    MemberDAO memberDAO;


    @BeforeEach
    void init() {
        memberRequestDTO = MemberRequestDTO.builder()
                .email("tesstt1@naver.com")
                .password("test12312")
                .nickname("tese")
                .memberType(MemberType.USER)
                .build();
    }


    public MemberDAO setMemberDAO(MemberRequestDTO memberRequestDTO) {
        memberDAO = MemberDAO.builder()
                .email(memberRequestDTO.getEmail())
                .password(memberRequestDTO.getPassword())
                .nickname(memberRequestDTO.getNickname())
                .memberType(MemberType.USER)
                .build();
        return memberDAO;
    }


    @Nested
    class TestSigUp {
        @Test
        @DisplayName("회원 가입 성공")
        void signUpSuccess() {
            // 중복 검사 통과
            lenient().when(memberMapper.getMember(memberRequestDTO.getEmail())).thenReturn(0);
            memberDAO = setMemberDAO(memberRequestDTO);
            when(memberMapper.insertMember(memberDAO)).thenReturn(1);
            assertThat(memberMapper.insertMember(memberDAO)).isEqualTo(1);
        }

        @Test
        @DisplayName("이메일 중복")
        void isExistUserByEmail() {
            lenient().when(memberMapper.getMember(memberRequestDTO.getEmail())).thenThrow(new IsExistUserByEmail(ErrorCode.IS_EXIST_USER_BY_EMAIL));
            assertThrows(IsExistUserByEmail.class, () -> memberMapper.getMember(memberRequestDTO.getEmail()));
        }
    }


}
