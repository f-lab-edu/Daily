package com.flab.daily.mapper;

import com.flab.daily.dao.MeetingDAO;
import com.flab.daily.dto.response.MeetingResponseDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MeetingMapper {
    int addMeeting(MeetingDAO meetingDAO);

    MeetingResponseDTO findMeetingOneById(Long meetingId);
}
