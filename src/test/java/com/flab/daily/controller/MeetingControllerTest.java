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
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
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
    int size;
    int page;

    @BeforeEach
    void beforeEach() {
        size = 12;
        page = 1;

        meetingResponseDTO = MeetingResponseDTO.builder()
            .meetingId(1L)
            .categoryId(1L)
            .meetingName("야구하기")
            .meetingDescription("야구 같이 하실래요?")
            .meetingDate(LocalDateTime.now().plusDays(20).withNano(0))
            .meetingPlace("잠실운동장")
            .meetingPeople(5)
            .currentPeople(0)
            .meetingImage("0")
            .createdBy("1234@gmail.com")
            .createdDate(LocalDateTime.now().withNano(0))
            .updatedDate(LocalDateTime.now().withNano(0))
            .build();
    }

    /*소모임 전체 검색 성공*/
    @Test
    @DisplayName("소모임 목록 검색 : 성공")
    public void findMeetingList_Success() throws Exception {
        /*given*/
        when(meetingService.findMeetingList(size, page)).thenReturn(List.of());
        /*when-then*/
        mockMvc.perform(get("/meeting")
                        .param("size", "12")
                        .param("page", "1")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    /* 소모임 단건 검색 성공 */
    @Test
    @DisplayName("소모임 단건 검색 : 성공")
    public void findMeetingOne_Success() throws Exception {
        /*given*/
        when(meetingService.findMeetingOneById(1L)).thenReturn(meetingResponseDTO);

        /*when - then*/
        mockMvc.perform(get("/meeting/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$..meeting_id").value(1))
                .andExpect(jsonPath("$..category_id").value(1))
                .andExpect(jsonPath("$..meeting_name").value("야구하기"))
                .andExpect(jsonPath("$..meeting_description").value("야구 같이 하실래요?"))
                .andExpect(jsonPath("$..meeting_date").value(LocalDateTime.now().plusDays(20).withNano(0).toString()))
                .andExpect(jsonPath("$..meeting_place").value("잠실운동장"))
                .andExpect(jsonPath("$..meeting_people").value(5))
                .andExpect(jsonPath("$..current_people").value(0))
                .andExpect(jsonPath("$..meeting_image").value("0"))
                .andExpect(jsonPath("$..created_by").value("1234@gmail.com"))
                .andExpect(jsonPath("$..created_date").value(LocalDateTime.now().withNano(0).toString()))
                .andExpect(jsonPath("$..updated_date").value(LocalDateTime.now().withNano(0).toString()));
    }
}
