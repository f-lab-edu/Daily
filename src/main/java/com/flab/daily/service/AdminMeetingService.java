package com.flab.daily.service;


import com.flab.daily.dto.MeetingDTO;
import com.flab.daily.mapper.MeetingMapper;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AdminMeetingService {

    private final MeetingMapper meetingMapper;
    private final HttpSession httpSession;

    @Transactional
    public void addMeeting(MeetingDTO meetingDTO) {

        // HttpSession 통해서 created_by 계정 추가
        String email = httpSession.getAttribute("email") + "";

        MeetingDTO meetingInfo = MeetingDTO.builder()
                        .category_id(meetingDTO.getCategory_id())
                        .meeting_name(meetingDTO.getMeeting_name())
                        .meeting_description(meetingDTO.getMeeting_description())
                        .meeting_date(meetingDTO.getMeeting_date()) //date 계산 필요
                        .meeting_place(meetingDTO.getMeeting_place())
                        .meeting_people(meetingDTO.getMeeting_people())
                        .meeting_image(meetingDTO.getMeeting_image())
                        .created_by(email)
                .build();

        meetingMapper.addMeeting(meetingInfo);

    }

}
