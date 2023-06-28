package com.flab.daily.service;

import com.flab.daily.dto.response.MeetingResponseDTO;
import com.flab.daily.exception.IsExistCheckException;
import com.flab.daily.mapper.MeetingMapper;
import com.flab.daily.paging.Pageable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MeetingServiceTest {

    @Mock
    MeetingMapper meetingMapper;

    @InjectMocks
    MeetingService meetingService;

    MeetingResponseDTO meetingResponseDTO;
    Pageable pageable;

    @BeforeEach
    void beforeEach() {
        meetingResponseDTO = MeetingResponseDTO.builder()
            .meetingId(1L)
            .categoryId(1L)
            .meetingName("야구하기")
            .meetingDescription("야구 같이 하실래요?")
            .meetingDate(LocalDateTime.now().plusDays(20))
            .meetingPlace("잠실운동장")
            .meetingPeople(5)
            .currentPeople(0)
            .meetingImage("0")
            .createdBy("1234@gmail.com")
            .createdDate(LocalDateTime.now())
            .updatedDate(LocalDateTime.now())
            .build();
    }

    /*Paging Test*/
    @Test
    @DisplayName("소모임 전체 조회 : 페이징 처리")
    public void findMeetingList_Paging_False() {
        /*given*/
        when(meetingMapper.findMeetingList(pageable)).thenReturn(List.of());
        /*when-then*/
        verify(meetingMapper, times(1)).findMeetingList(pageable);
    }

    /* DB에 없는 MeetingId인 경우 */
    @Test
    @DisplayName("소모임 단건 검색 : 유효하지 않은 MeetingId로 인한 실패")
    public void findMeetingOne_InvalidParameter_False(){
        /* given */
        when(meetingMapper.findMeetingOneById(1L)).thenReturn(null);
        /* when - then */
        assertThrows(IsExistCheckException.class, () -> meetingService.findMeetingOneById(1L));
    }

    /* FindMeetingOne Service 성공하는 경우 */
    @Test
    @DisplayName("소모임 단건 검색 : 성공")
    public void findMeetingOne_Success() {
        /* given */
        when(meetingMapper.findMeetingOneById(1L)).thenReturn(meetingResponseDTO);
        /* when - then */
        assertThat(meetingService.findMeetingOneById(1L)).isEqualTo(meetingResponseDTO);
    }
}
