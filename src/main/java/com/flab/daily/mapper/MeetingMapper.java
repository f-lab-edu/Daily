package com.flab.daily.mapper;

import com.flab.daily.dto.MeetingDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface MeetingMapper {

    void addMeeting(MeetingDTO meetingDTO);

    MeetingDTO getMeeting();
}
