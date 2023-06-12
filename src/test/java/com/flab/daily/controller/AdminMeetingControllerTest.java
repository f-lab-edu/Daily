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

    @BeforeEach
    void beforeEach() {
        code = "$..code";
        result = "$..result";
        message = "$..message";
    }

    //이메일 형식 검사
    @Test
    @DisplayName("@Email Format Check.")
    void test_Email_Format_False() throws Exception {
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

    //글자수 검사
    @Test
    @DisplayName("@Max Character Count Check.")
    void test_Character_Count_False() throws Exception {
        meetingRequestDto = MeetingRequestDto.builder()
                .categoryId(1)
                .meetingName("축구가 너무 하고 싶은 사람들이 많이 모인 소모임이고 " +
                        "공이 없어도 되고 신발도 없어도 되고 유니폼도 없어도 되고 " +
                        "아무나 오시면 되는 축구 소모임입니다.") //45자 이상 주입
                .meetingDescription("축구 같이 하실래요?")
                .meetingDate(LocalDateTime.now())
                .meetingPlace("잠실운동장")
                .meetingPeople(10)
                .meetingImage(null)
                .createdBy("1234@gmail.com")
                .build();

        doNothing().when(adminMeetingService).addMeeting(any());

        mockMvc.perform(post("/admin/meetings")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(meetingRequestDto)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath(code).value(400))
                .andExpect(jsonPath(result).value("Validation Failed"))
                .andExpect(jsonPath(message).value("45자까지 작성이 가능합니다."));
    }

    //null 값 검사
    @Test
    @DisplayName("@NotNull Check.")
    void test_Not_Null_False() throws Exception {
        meetingRequestDto = MeetingRequestDto.builder()
                .categoryId(1)
                .meetingName("축구하기")
                .meetingDescription(null) // null 주입
                .meetingDate(LocalDateTime.now())
                .meetingPlace("잠실운동장")
                .meetingPeople(10)
                .meetingImage(null)
                .createdBy("1234@gmail.com")
                .build();

        doNothing().when(adminMeetingService).addMeeting(any());

        mockMvc.perform(post("/admin/meetings")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(meetingRequestDto)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath(code).value(400))
                .andExpect(jsonPath(result).value("Validation Failed"))
                .andExpect(jsonPath(message).value("소모임에 대해 소개해 주세요."));
    }


    //whitespace값, @Size 검사
    @Test
    @DisplayName("@NotBlank, @Size Check.")
    void test_Not_WhiteSpace_False() throws Exception {
        meetingRequestDto = MeetingRequestDto.builder()
                .categoryId(1)
                .meetingName("축구하기")
                .meetingDescription(" ") // whitespace
                .meetingDate(LocalDateTime.now())
                .meetingPlace("잠실운동장")
                .meetingPeople(10)
                .meetingImage(null)
                .createdBy("1234@gmail.com")
                .build();

        doNothing().when(adminMeetingService).addMeeting(any());

        mockMvc.perform(post("/admin/meetings")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(meetingRequestDto)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath(code).value(400))
                .andExpect(jsonPath(result).value("Validation Failed"))
                .andExpect(jsonPath(message).value("소모임에 대해 소개해 주세요."));
    }

    //Integer 타입에 String 값 입력 상황 검사
    @Test
    @DisplayName("Invalid DataType Check.")
    void test_Invalid_DataType_False() throws Exception {
        doNothing().when(adminMeetingService).addMeeting(any());

        mockMvc.perform(post("/admin/meetings")
                        .param("meetingPeople", "두명")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath(code).value(400))
                .andExpect(jsonPath(result).value("Validation Failed"))
                .andExpect(jsonPath(message).value("데이터 타입이 맞지 않습니다."));
    }
    
    //소모임 진행 날짜가 작성 날짜보다 이후인지 검사
    @Test
    @DisplayName("Invalid LocalDate Check.")
    void test_Invalid_LocalDate_False() throws Exception {
        //현재보다 이전 날짜 주입
        LocalDateTime localDateTime = LocalDateTime.of(2021, 9, 21, 06, 31);

        meetingRequestDto = MeetingRequestDto.builder()
                .categoryId(1)
                .meetingName("축구하기")
                .meetingDescription("축구 같이 하실래요?")
                .meetingDate(localDateTime)
                .meetingPlace("잠실운동장")
                .meetingPeople(10)
                .meetingImage(null)
                .createdBy("1234@gmail.com")
                .build();

        doNothing().when(adminMeetingService).addMeeting(any());

        mockMvc.perform(post("/admin/meetings")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(meetingRequestDto)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath(code).value(400))
                .andExpect(jsonPath(result).value("Validation Failed"))
                .andExpect(jsonPath(message).value("소모임 날짜가 현재보다 이전일 수 없습니다."));
    }

    //날짜 형식 검사
    @Test
    @DisplayName("Invalid LocalDateTime Format Check.")
    void test_LocalDate_Format_False() throws Exception {
        doNothing().when(adminMeetingService).addMeeting(any());

        mockMvc.perform(post("/admin/meetings")
                        .param("meetingDate", "2022-10-23")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath(code).value(400))
                .andExpect(jsonPath(result).value("Validation Failed"))
                .andExpect(jsonPath(message).value("데이터 타입이 맞지 않습니다."));
    }

    //유효성 검사를 통과한 후에도 잘 넘어가지는지 검사
    @Test
    @DisplayName("isCreated Check")
    void isCreated_Success() throws Exception {
        meetingRequestDto = MeetingRequestDto.builder()
                .categoryId(1)
                .meetingName("축구하기")
                .meetingDescription("축구 같이 하실래요?")
                .meetingDate(LocalDateTime.now().plusDays(20))
                .meetingPlace("잠실운동장")
                .meetingPeople(10)
                .meetingImage(null)
                .createdBy("1234@gmail.com")
                .build();

        doNothing().when(adminMeetingService).addMeeting(any());

        mockMvc.perform(post("/admin/meetings")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(meetingRequestDto)))
                .andDo(print())
                .andExpect(status().isCreated());
    }

}
