package com.flab.daily.service;

import com.flab.daily.dto.response.MeetingResponseDTO;
import com.flab.daily.exception.IsExistCheckException;
import com.flab.daily.mapper.MeetingMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MeetingServiceTest {

    @Mock
    MeetingMapper meetingMapper;

    @InjectMocks
    MeetingService meetingService;

    MeetingResponseDTO meetingResponseDTO;

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

    /* DB에 없는 MeetingId인 경우 */
    @Test
    @DisplayName("Invalid MeetingId False Check.")
    public void findMeetingOne_InvalidParameter_False(){
        /* given */
        when(meetingMapper.findMeetingOneById(1L)).thenReturn(null);
        /* when - then */
        assertThrows(IsExistCheckException.class, () -> meetingService.findMeetingOneById(1L));
    }

    /* FindMeetingOne Service 성공하는 경우 */
    @Test
    @DisplayName("FindMeetingOne Success Check.")
    public void findMeetingOne_Success() {
        /* given */
        when(meetingMapper.findMeetingOneById(1L)).thenReturn(meetingResponseDTO);
        /* when - then */
        assertThat(meetingService.findMeetingOneById(1L)).isEqualTo(meetingResponseDTO);
    }
}
