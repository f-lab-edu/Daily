package com.flab.daily.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MeetingResponseDTO {
    @JsonProperty(value = "meeting_id")
    private Long meetingId;

    @JsonProperty(value = "category_id")
    private Long categoryId;

    @JsonProperty(value = "meeting_name")
    private String meetingName;

    @JsonProperty(value = "meeting_description")
    private String meetingDescription;

    @JsonProperty(value = "meeting_date")
    private LocalDateTime meetingDate;

    @JsonProperty(value = "meeting_place")
    private String meetingPlace;

    @JsonProperty(value = "meeting_people")
    private Integer meetingPeople;

    @JsonProperty(value = "current_people")
    private Integer currentPeople;

    @JsonProperty(value = "meeting_image")
    private String meetingImage;

    @JsonProperty(value = "created_by")
    private String createdBy;

    @JsonProperty(value = "created_date")
    private LocalDateTime createdDate;

    @JsonProperty(value = "updated_date")
    private LocalDateTime updatedDate;
}
