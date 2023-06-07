package com.flab.daily.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flab.daily.dto.request.MeetingRequestDto;
import com.flab.daily.service.AdminMeetingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(controllers = AdminMeetingController.class)
public class AdminMeetingControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    AdminMeetingService adminMeetingService;

    @Autowired
    ObjectMapper objectMapper;

    MeetingRequestDto meetingRequestDto;
    String code;
    String result;
    String message;

    // jsonPath의 사용될 값들 초기화 -> 넘어올 json 데이터중 code, result, message 값 사용
    // $ : root 개념
    @BeforeEach
    void beforeEach() {
        code = "$..code";
        result = "$..result";
        message = "$..message";
    }

    //이메일 형식 체크
    @Test
    @DisplayName("Invalid email format check.")
    void invalid_email() throws Exception {
        //유효성 검사에 사용될 dto 작성
        meetingRequestDto = MeetingRequestDto.builder()
                .categoryId(1)
                .meetingName("축구하기")
                .meetingDescription("축구 같이 하실래요?")
                .meetingDate(LocalDateTime.now())
                .meetingPlace("잠실운동장")
                .meetingPeople(10)
                .meetingImage(null)
                .createdBy("1234@gmailcom") //맞지 않은 email 양식 주입
                .build();

        doNothing().when(adminMeetingService).addMeeting(any());

        mockMvc.perform(post("/admin/meetings")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(meetingRequestDto)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath(code).value(400))
                .andExpect(jsonPath(result).value("Validation Failed"))
                .andExpect(jsonPath(message).value("유효하지 않는 이메일 형식입니다."));
    }
    

    //글자수 초과



    //날짜 형식 체크


    //Integer 타입에 String 값 입력 체크


    //소모임 진행 날짜가 작성 날짜보다 이후인지 확인

    //null 값 확인


    //whitespace 값 확인















}
