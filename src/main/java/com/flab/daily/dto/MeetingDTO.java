package com.flab.daily.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MeetingDTO {


    @NotNull(message = "카테고리를 선택해 주세요.")
    @JsonProperty(value = "category_id")
    private Long categoryId;

    @Size
    @NotBlank(message = "소모임 이름을 입력해 주세요.")
    @JsonProperty(value = "meeting_name")
    private String meetingName;

    @NotBlank(message = "소모임에 대햐 소개해 주세요.")
    @JsonProperty(value = "meeting_description")
    private String meetingDescription;

    @NotNull(message = "소모임 날짜를 입력해 주세요.")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    @JsonProperty(value = "meeting_date")
    private LocalDateTime meetingDate;

    @NotBlank(message = "소모임 장소를 입력해 주세요.")
    @JsonProperty(value = "meeting_place")
    private String meetingPlace;

    @JsonProperty(value = "current_people")
    private Integer currentPeople;

    @NotNull(message = "소모임 모집 인원수를 입력해 주세요.")
    @JsonProperty(value = "meeting_people")
    private Integer meetingPeople;

    @JsonProperty(value = "meeting_image")
    private String meetingImage;

    @Email
    @NotBlank(message = "소모임 관리자 이메일 주소(ID)를 입력해 주세요.")
    @JsonProperty(value = "created_by")
    private String createdBy;


}
