package com.flab.daily.service;

import com.flab.daily.dto.request.MeetingRequestDTO;
import com.flab.daily.exception.IsExistCheckException;
import com.flab.daily.mapper.CategoryMapper;
import com.flab.daily.mapper.MemberMapper;
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

    @Mock
    MemberMapper memberMapper;

    @InjectMocks
    AdminMeetingService adminMeetingService;
    MeetingRequestDTO meetingRequestDTO;

    @BeforeEach
    void beforeEach() {
        //Test에 사용될 DTO
        meetingRequestDTO = MeetingRequestDTO.builder()
                .categoryId(1)
                .meetingName("축구하기")
                .meetingDescription("축구 같이 하실래요?")
                .meetingDate(LocalDateTime.now().plusDays(20))
                .meetingPlace("잠실운동장")
                .meetingPeople(10)
                .meetingImage(null)
                .createdBy("test@naver.com")
                .build();
    }

    //DB에 저장된 유효한 카테고리 아이디값이 맞는지 체크하는 함수
    @Test
    @DisplayName("Invalid CategoryId Check.")
    void test_Invalid_CategoryId_False() {
        //given
        //categoryMapper.isValidExist를 호출하였을 때 에러 발생하도록 설정
        when(categoryMapper.isValidExist(meetingRequestDTO.getCategoryId())).thenReturn(0);
        //memberMapper.isValidExist를 호출하였을 때 정상 설정
        when(memberMapper.isValidExist(meetingRequestDTO.getCreatedBy())).thenReturn(1);
        //when-then
        assertThrows(IsExistCheckException.class, () -> adminMeetingService.addMeeting(meetingRequestDTO));
    }

    //DB에 저장된 Email이 체크하는 함수
    @Test
    @DisplayName("Invalid Email Check.")
    void test_Invalid_Email_False() {
        //given
        //categoryMapper.isValidExist를 호출하였을 때 정상 설정
        when(categoryMapper.isValidExist(meetingRequestDTO.getCategoryId())).thenReturn(1);
        //memberMapper.isValidExist를 호출하였을 때 에러 발생하도록 설정
        when(memberMapper.isValidExist(meetingRequestDTO.getCreatedBy())).thenReturn(0);
        //when-then
        assertThrows(IsExistCheckException.class, () -> adminMeetingService.addMeeting(meetingRequestDTO));
    }
    
    //DB 서버 다운되었을 때 처리 check

    

    //SQL Exception

    //DB 유효성 검사 끝나고 mapper로 정상적으로 전달되는지 check
}
