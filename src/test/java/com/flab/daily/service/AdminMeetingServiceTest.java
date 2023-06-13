package com.flab.daily.service;

import com.flab.daily.dao.MeetingDAO;
import com.flab.daily.mapper.MeetingMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;

import java.time.LocalDateTime;

@SpringBootTest
public class AdminMeetingServiceTest {

    @MockBean
    MeetingMapper meetingMapper;

    MeetingDAO meetingDao;


    //categoryId check
    @Test
    void addMeeting() {

        //when
        meetingDao = MeetingDAO.builder()
                .categoryId(1)
                .meetingName("축구하기")
                .meetingDescription("축구 같이 하실래요?")
                .meetingDate(LocalDateTime.now())
                .meetingPlace("잠실운동장")
                .meetingPeople(10)
                .meetingImage(null)
                .createdBy("test@gmail.com")
                .build();


        //given
        doNothing().when(meetingMapper).addMeeting(any());



        //then

    }


    //email check



    //dao check
}
