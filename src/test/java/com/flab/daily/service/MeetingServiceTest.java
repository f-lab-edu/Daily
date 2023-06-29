package com.flab.daily.service;

import com.flab.daily.dto.response.MeetingResponseDTO;
import com.flab.daily.exception.IsExistCheckException;
import com.flab.daily.mapper.MeetingMapper;
import com.flab.daily.paging.Pagination;
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
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MeetingServiceTest {

    @Mock
    MeetingMapper meetingMapper;

    @InjectMocks
    MeetingService meetingService;

    MeetingResponseDTO meetingResponseDTO;
    Pagination pagination;

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

    /*소모임 전체 갯수, 페이징 처리된 목록 조회 확인*/
    @Test
    @DisplayName("소모임 전체 조회 : 갯수 추출, 페이징 적용 확인")
    public void findMeetingList_Paging_Check() {
        /*given*/
        pagination = new Pagination(18L, 12, 1);
        when(meetingMapper.countMeetingAll()).thenReturn(18L); /*올바르게 데이터 수를 가져온다고 가정*/
        when(meetingMapper.findMeetingList(pagination)).thenReturn(List.of()); /*올바르게 소모임 목록을 가져온다고 가정*/
        /*when*/
        meetingMapper.countMeetingAll();
        meetingMapper.findMeetingList(pagination);
        /*then*/
        verify(meetingMapper, times(1)).countMeetingAll();
        verify(meetingMapper, times(1)).findMeetingList(pagination);
    }

    /*소모임 목록 성공*/
    @Test
    @DisplayName("소모임 전체 조회 : 성공")
    public void findMeetingList_Success() {
        /*given*/
        pagination = new Pagination(18L, 12, 1);
        when(meetingMapper.findMeetingList(pagination)).thenReturn(List.of());
        /*when-then*/
        assertThat(meetingMapper.findMeetingList(pagination)).isEqualTo(List.of());
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
