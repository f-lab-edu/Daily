package com.flab.daily.mapper;

import com.flab.daily.dao.MeetingDAO;
import com.flab.daily.dao.Pagination;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MeetingMapper {
    int addMeeting(MeetingDAO meetingDAO);
    Long countMeetingAll();
    List<MeetingDAO> findMeetingList(Pagination pagination);
    MeetingDAO findMeetingOneById(Long meetingId);
    Long countMeetingByCategoryId(Long categoryId);
    List<MeetingDAO> findMeetingListByCategoryId(@Param("pagination")Pagination pagination, @Param("categoryId")Long categoryId);
}
