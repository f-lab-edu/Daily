package com.flab.daily.mapper;

import com.flab.daily.dao.MeetingDao;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MeetingMapper {
    void addMeeting(MeetingDao meetingDAO);
}
