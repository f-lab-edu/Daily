package com.flab.daily.controller;

import com.flab.daily.dto.response.MeetingResponseDTO;
import com.flab.daily.service.MeetingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = MeetingController.class)
public class MeetingControllerTest {

    @MockBean
    MeetingService meetingService;

    @Autowired
    MockMvc mockMvc;

    MeetingResponseDTO meetingResponseDTO;
    String code;
    String message;

    @BeforeEach
    void beforeEach() {
        code = "$..code";
        message = "$..message";
    }

    /* 소모임 단건 검색 성공 */
    @Test
    @DisplayName("FindMeetingOneById Success Check.")
    public void findMeetingOne_Success() throws Exception {
        /*given*/
        LocalDateTime meetingDate = LocalDateTime.now().plusDays(20).withNano(0);
        LocalDateTime createUpdateDate = LocalDateTime.now().withNano(0);

        meetingResponseDTO = MeetingResponseDTO.builder()
                .meetingId(1L)
                .categoryId(1L)
                .meetingName("야구하기")
                .meetingDescription("야구 같이 하실래요?")
                .meetingDate(meetingDate)
                .meetingPlace("잠실운동장")
                .meetingPeople(5)
                .currentPeople(0)
                .meetingImage("0")
                .createdBy("1234@gmail.com")
                .createdDate(createUpdateDate)
                .updatedDate(createUpdateDate)
                .build();

        when(meetingService.findMeetingOneById(1L)).thenReturn(meetingResponseDTO);

        /*when - then*/
        mockMvc.perform(get("/meeting/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$..meetingId").value(1))
                .andExpect(jsonPath("$..categoryId").value(1))
                .andExpect(jsonPath("$..meetingName").value("야구하기"))
                .andExpect(jsonPath("$..meetingDescription").value("야구 같이 하실래요?"))
                .andExpect(jsonPath("$..meetingDate").value(meetingDate.toString()))
                .andExpect(jsonPath("$..meetingPlace").value("잠실운동장"))
                .andExpect(jsonPath("$..meetingPeople").value(5))
                .andExpect(jsonPath("$..currentPeople").value(0))
                .andExpect(jsonPath("$..meetingImage").value("0"))
                .andExpect(jsonPath("$..createdBy").value("1234@gmail.com"))
                .andExpect(jsonPath("$..createdDate").value(createUpdateDate.toString()))
                .andExpect(jsonPath("$..updatedDate").value(createUpdateDate.toString()));
    }
}
