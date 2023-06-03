package com.flab.daily.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MeetingResponseDto {
    private Long categoryId;
    private String meetingName;
    private String meetingDescription;
    private LocalDateTime meetingDate;
    private String meetingPlace;
    private Integer meetingPeople;
    private String meetingImage;
    private String createdBy;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}
