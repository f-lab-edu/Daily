package com.flab.daily.mapper;

import com.flab.daily.dao.MeetingDAO;
import com.flab.daily.dto.response.MeetingResponseDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MeetingMapper {
    int addMeeting(MeetingDAO meetingDAO);

    List<MeetingResponseDTO> findMeetingList();

    MeetingResponseDTO findMeetingOneById(Long meetingId);
}
