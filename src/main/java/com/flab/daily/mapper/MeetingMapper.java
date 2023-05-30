package com.flab.daily.mapper;

import com.flab.daily.dto.MeetingDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Mapper
@Repository
public interface MeetingMapper {

    // void addMeeting(Map<String, String> map);

    MeetingDTO getMeeting();
}
