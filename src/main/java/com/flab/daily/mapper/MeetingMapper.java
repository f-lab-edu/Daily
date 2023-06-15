package com.flab.daily.mapper;

import com.flab.daily.dao.MeetingDAO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MeetingMapper {
    int addMeeting(MeetingDAO meetingDAO);
}
