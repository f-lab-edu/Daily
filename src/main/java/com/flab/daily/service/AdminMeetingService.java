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
                .categoryId(meetingDTO.getCategoryId())
                .meetingName(meetingDTO.getMeetingName())
                .meetingDescription(meetingDTO.getMeetingDescription())
                .meetingDate(meetingDTO.getMeetingDate())
                .meetingPlace(meetingDTO.getMeetingPlace())
                .currentPeople(meetingDTO.getCurrentPeople())
                .meetingPeople(meetingDTO.getMeetingPeople())
                .meetingImage(meetingDTO.getMeetingImage())
                .createdBy(meetingDTO.getCreatedBy())
                .build();


        meetingMapper.addMeeting(meetingDTO);
    }

    public MeetingDTO getMeeting() {
        return meetingMapper.getMeeting();
    }


}
