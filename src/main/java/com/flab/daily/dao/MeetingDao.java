package com.flab.daily.dao;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;


@Builder
@Data
public class MeetingDao {
    private long categoryId;
    private String meetingName;
    private String meetingDescription;
    private LocalDateTime meetingDate;
    private String meetingPlace;
    private Integer meetingPeople;
    private String meetingImage;
    private String createdBy;
}
