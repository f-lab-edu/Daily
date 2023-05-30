package com.flab.daily.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MeetingDTO {


    @NotNull
    @JsonProperty(value = "category_id")
    private Long categoryId;

    @NotNull
    @JsonProperty(value = "meeting_name")
    private String meetingName;

    @NotNull
    @JsonProperty(value = "meeting_description")
    private String meetingDescription;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    @JsonProperty(value = "meeting_date")
    private LocalDateTime meetingDate;

    @NotNull
    @JsonProperty(value = "meeting_place")
    private String meetingPlace;

    @JsonProperty(value = "current_people")
    private Integer currentPeople;

    @NotNull
    @JsonProperty(value = "meeting_people")
    private Integer meetingPeople;

    @JsonProperty(value = "meeting_image")
    private String meetingImage;

    @NotNull
    @JsonProperty(value = "created_by")
    private String createdBy;


}
