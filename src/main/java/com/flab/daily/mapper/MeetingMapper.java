package com.flab.daily.mapper;

import com.flab.daily.dao.MeetingDAO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
public interface MeetingMapper {
    void addMeeting(MeetingDAO meetingDAO);
}
