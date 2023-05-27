package com.flab.daily.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface MeetingMapper {

    void addMeeting(Map<String, String> map);
}
