package com.flab.daily.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flab.daily.dto.request.MemberRequestDTO;
import com.flab.daily.service.MemberService;
import com.flab.daily.type.MemberType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers = MemberController.class)
public class MemberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private MemberService memberService;

    MemberRequestDTO memberDTO;

    @BeforeEach
    void init() {

    }


    @Test
    @DisplayName("회원 가입 : 정상")
    void signUp() throws Exception {
        // given
        memberDTO = MemberRequestDTO.builder()
                .email("test1323@naver.com")
                .password("test123!@#")
                .memberType(MemberType.USER)
                .nickname("hello")
                .build();
        // when
        doNothing().when(memberService).signUp(any());
        ResultActions actions = mockMvc.perform(post("/members")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(memberDTO)));

        // then
        actions.andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("회원 가입 : 이메일 없음")
    void signUpNotEmail() throws Exception {
        // given
        memberDTO = MemberRequestDTO.builder()
                .password("test123!@#")
                .memberType(MemberType.USER)
                .nickname("hello")
                .build();

        // when
        doNothing().when(memberService).signUp(any());
        ResultActions actions = mockMvc.perform(post("/members")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(memberDTO)));


        // then
        actions.andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("400"))
                .andExpect(jsonPath("$.message").value("이메일은 필수 값입니다."));
    }

    @Test
    @DisplayName("회원 가입 : 이메일 양식이 맞지 않음")
    void signUpEmailInvalid() throws Exception {
        // given
        memberDTO = MemberRequestDTO.builder()
                .email("test1323@")
                .password("test123!@#")
                .memberType(MemberType.USER)
                .nickname("hello")
                .build();

        // when
        doNothing().when(memberService).signUp(any());
        ResultActions actions = mockMvc.perform(post("/members")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(memberDTO)));


        // then
        actions.andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("400"))
                .andExpect(jsonPath("$.message").value("이메일 형식에 맞지 않습니다."));
    }

    @Test
    @DisplayName("회원 가입 : 비밀번호 없음")
    void signUpNotPassword() throws Exception {
        // given
        memberDTO = MemberRequestDTO.builder()
                .email("test1323@naver.com")
                .memberType(MemberType.USER)
                .nickname("hello")
                .build();

        // when
        doNothing().when(memberService).signUp(any());
        ResultActions actions = mockMvc.perform(post("/members")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(memberDTO)));


        // then
        actions.andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("400"))
                .andExpect(jsonPath("$.message").value("패스워드는 필수 값입니다."));
    }

    @Test
    @DisplayName("회원 가입 : 비밀번호 형식 불일치")
    void signUpPasswordInvalid() throws Exception {
        // given
        memberDTO = MemberRequestDTO.builder()
                .email("test1323@naver.com")
                .password("test123")
                .memberType(MemberType.USER)
                .nickname("hello")
                .build();

        // when
        doNothing().when(memberService).signUp(any());
        ResultActions actions = mockMvc.perform(post("/members")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(memberDTO)));


        // then
        actions.andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("400"))
                .andExpect(jsonPath("$.message").value("영문, 숫자, 특수문자 중 3종류 이상을 조합하여 최소 8자 이상 입력해 주세요"));
    }


    @Test
    @DisplayName("회원 가입 : 닉네임 없음")
    void signUpNotNcikName() throws Exception {
        // given
        memberDTO = MemberRequestDTO.builder()
                .email("test1323@naver.com")
                .password("test123!@#")
                .memberType(MemberType.USER)
                .build();
        // when
        doNothing().when(memberService).signUp(any());

        // when
        doNothing().when(memberService).signUp(any());
        ResultActions actions = mockMvc.perform(post("/members")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(memberDTO)));

        // then
        actions.andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("400"))
                .andExpect(jsonPath("$.message").value("닉네임은 필수 값입니다."));
    }


}
