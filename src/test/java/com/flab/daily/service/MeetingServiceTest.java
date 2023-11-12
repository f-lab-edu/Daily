package com.flab.daily.service;

import com.flab.daily.dao.MeetingDAO;
import com.flab.daily.dao.Pagination;
import com.flab.daily.utils.exception.IsExistCheckException;
import com.flab.daily.mapper.MeetingMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
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

    List<MeetingDAO> meetingList;
    MeetingDAO meetingDAO;
    Pagination pagination;

    @BeforeEach
    void beforeEach() {
        meetingList = new ArrayList<>();
        pagination = new Pagination(3,1);
        meetingDAO = MeetingDAO.builder()
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

    /*service 실행될 때 count 쿼리 실행되는지 확인*/
    @Test
    @DisplayName("소모임 전체 조회 : 소모임 전체 갯수 가져오는 함수 실행 확인")
    public void findMeetingList_Count_Check() {
        /*given*/
        when(meetingMapper.countMeetingAll()).thenReturn(10L);
        /*when*/
        meetingService.findMeetingList(3, 1);
        /*then*/
        verify(meetingMapper, times(1)).countMeetingAll();
    }

    /*meetingList 추출하는 함수 실행되는지 확인*/
    @Test
    @DisplayName("소모임 전체 조회 : meetingList 추출하는 함수 실행 확인")
    public void findMeetingList_Select_Check() {
        /*given*/
        when(meetingMapper.countMeetingAll()).thenReturn(10L);
        when(meetingMapper.findMeetingList(any())).thenReturn(List.of());
        /*when*/
        meetingService.findMeetingList(3, 1);
        /*when-then*/
        verify(meetingMapper, times(1)).countMeetingAll();
        verify(meetingMapper, times(1)).findMeetingList(any());
    }

    /*소모임 목록 성공*/
    @Test
    @DisplayName("소모임 전체 조회 : 성공")
    public void findMeetingList_Success() {
        /*given*/
        for(int i=0 ; i<5 ; i++) {
            meetingList.add(meetingDAO);
        }
        when(meetingMapper.countMeetingAll()).thenReturn(5L);
        when(meetingMapper.findMeetingList(any(Pagination.class))).thenReturn(meetingList);
        /*when-then*/
        assertThat(meetingService.findMeetingList(3, 1)).isNotNull(); /*DB에 값이 있으면 null을 반환하지 않는 것으로 확인*/
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
        when(meetingMapper.findMeetingOneById(1L)).thenReturn(meetingDAO);
        /* when - then */
        assertThat(meetingService.findMeetingOneById(1L)).isNotNull();
    }
}
