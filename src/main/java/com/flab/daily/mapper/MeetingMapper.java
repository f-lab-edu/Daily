package com.flab.daily.mapper;

import com.flab.daily.dao.MeetingDAO;
import com.flab.daily.dto.response.MeetingResponseDTO;
import com.flab.daily.paging.Pageable;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MeetingMapper {
    int addMeeting(MeetingDAO meetingDAO);

    int countMeetingAll();

    List<MeetingResponseDTO> findMeetingList(Pageable pageable);

    MeetingResponseDTO findMeetingOneById(Long meetingId);
}
