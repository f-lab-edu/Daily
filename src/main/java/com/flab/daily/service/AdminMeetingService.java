package com.flab.daily.service;

import com.flab.daily.dto.MeetingDTO;
import com.flab.daily.mapper.MeetingMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminMeetingService {

    private final MeetingMapper meetingMapper;

    public void addMeeting(MeetingDTO meetingDTO) {
        MeetingDTO meetingInfo = MeetingDTO.builder()
                .category_id(meetingDTO.getCategory_id())
                .meeting_name(meetingDTO.getMeeting_name())
                .meeting_description(meetingDTO.getMeeting_description())
                .meeting_date(meetingDTO.getMeeting_date()) //date 계산 필요
                .meeting_place(meetingDTO.getMeeting_place())
                .meeting_people(meetingDTO.getMeeting_people())
                .meeting_image(meetingDTO.getMeeting_image())
                .created_by(meetingDTO.getCreated_by())
                .build();

        meetingMapper.addMeeting(meetingInfo);
    }

    public MeetingDTO getMeeting() {
        return meetingMapper.getMeeting();
    }


}
