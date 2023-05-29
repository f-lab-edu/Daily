package com.flab.daily.service;

import com.flab.daily.dto.MeetingDTO;
import com.flab.daily.mapper.MeetingMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminMeetingService {

    private final MeetingMapper meetingMapper;

    public MeetingDTO getMeeting() {
        return meetingMapper.getMeeting();
    }


}
