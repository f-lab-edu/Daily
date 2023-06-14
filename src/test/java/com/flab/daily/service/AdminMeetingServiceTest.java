package com.flab.daily.service;

import com.flab.daily.dto.request.MeetingRequestDTO;
import com.flab.daily.exception.IsExistCheckException;
import com.flab.daily.mapper.CategoryMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AdminMeetingServiceTest {

    @Mock
    CategoryMapper categoryMapper;

    @InjectMocks
    AdminMeetingService adminMeetingService;
    MeetingRequestDTO meetingRequestDTO;

    @BeforeEach
    void beforeEach() {
        meetingRequestDTO = MeetingRequestDTO.builder()
                .categoryId(2) // Invalid categoryId 확인을 위해 잘못된 값 주입
                .meetingName("축구하기")
                .meetingDescription("축구 같이 하실래요?")
                .meetingDate(LocalDateTime.now().plusDays(20))
                .meetingPlace("잠실운동장")
                .meetingPeople(10)
                .meetingImage(null)
                .createdBy("1234@gmail.com")
                .build();
    }



    //해당 카테고리 Id가 없으면 IsExistCheckException 발생
    @Test
    @DisplayName("Invalid CategoryId Check.")
    void test_Invalid_CategoryId__False() {

        //given
        when(categoryMapper.isValidExist(meetingRequestDTO.getCategoryId())).thenReturn(0); //카테고리값이 유효하지 않으면 0반환

        //when-then
        assertThrows(IsExistCheckException.class, () -> adminMeetingService.addMeeting(meetingRequestDTO));
    }


    //invalid email check

    //DB 서버 다운되었을 때 처리 check

    //SQL Exception

    //DB 유효성 검사 끝나고 mapper로 정상적으로 전달되는지 check
}
