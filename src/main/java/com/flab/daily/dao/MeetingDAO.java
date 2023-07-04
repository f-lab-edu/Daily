package com.flab.daily.dao;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;


@Builder
@Data
public class MeetingDAO {
    private Long meetingId;
    private Long categoryId;
    private String meetingName;
    private String meetingDescription;
    private LocalDateTime meetingDate;
    private String meetingPlace;
    private Integer meetingPeople;
    private Integer currentPeople;
    private String meetingImage;
    private String createdBy;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}
