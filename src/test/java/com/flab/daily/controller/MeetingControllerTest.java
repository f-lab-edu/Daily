package com.flab.daily.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flab.daily.dao.Pagination;
import com.flab.daily.dto.response.MeetingResponseDTO;
import com.flab.daily.dto.response.PagingDTO;
import com.flab.daily.service.MeetingService;
import com.flab.daily.utils.PagingUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    @Autowired
    ObjectMapper objectMapper;

    MeetingResponseDTO meetingResponseDTO;
    List<MeetingResponseDTO> meetingResponseDTOList;
    LocalDateTime localDateTime;

    @BeforeEach
    void beforeEach() {
        localDateTime = LocalDateTime.now().plusDays(20).withNano(0);

        meetingResponseDTO = MeetingResponseDTO.builder()
            .meetingId(1L)
            .categoryId(1L)
            .meetingName("야구하기")
            .meetingDescription("야구 같이 하실래요?")
            .meetingDate(localDateTime)
            .meetingPlace("잠실운동장")
            .meetingPeople(5)
            .currentPeople(0)
            .meetingImage("0")
            .createdBy("1234@gmail.com")
            .createdDate(localDateTime)
            .updatedDate(localDateTime)
            .build();
    }

    /*소모임 전체 검색 성공*/
    /*service로 데이터를 잘 보내고 service에서 받은 데이터를 잘 전달하는지 확인*/
    @Test
    @DisplayName("소모임 목록 검색 : 성공")
    public void findMeetingList_Success() throws Exception {
        /*given*/
        Pagination pagination = new Pagination(3, 1);
        PagingUtil pagingUtil = new PagingUtil(5, pagination);

        meetingResponseDTOList = new ArrayList<>();
        for(int i=0 ; i<5 ; i++) {
            meetingResponseDTOList.add(meetingResponseDTO);
        }

        PagingDTO pagingDTO = PagingDTO.builder()
                .pagingUtil(pagingUtil)
                .dataList(meetingResponseDTOList).build();

        when(meetingService.findMeetingList(3, 1)).thenReturn(pagingDTO);

        /*when-then*/
        mockMvc.perform(get("/meeting")
                        .param("size", "3")
                        .param("page", "1")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(pagingDTO)));
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
                .andExpect(jsonPath("$..meeting_date").value(localDateTime.toString()))
                .andExpect(jsonPath("$..meeting_place").value("잠실운동장"))
                .andExpect(jsonPath("$..meeting_people").value(5))
                .andExpect(jsonPath("$..current_people").value(0))
                .andExpect(jsonPath("$..meeting_image").value("0"))
                .andExpect(jsonPath("$..created_by").value("1234@gmail.com"))
                .andExpect(jsonPath("$..created_date").value(localDateTime.toString()))
                .andExpect(jsonPath("$..updated_date").value(localDateTime.toString()));
    }
}
