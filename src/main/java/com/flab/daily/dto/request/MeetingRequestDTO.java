package com.flab.daily.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.*;
import org.checkerframework.common.value.qual.EnumVal;

import java.time.LocalDateTime;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MeetingRequestDTO {

    @NotNull(message = "카테고리를 선택해 주세요.")
    @JsonProperty(value = "category_id")
    private String categoryId;

    @Size(min = 0, max = 45, message = "45자 미만으로만 작성이 가능합니다.")
    @NotBlank(message = "소모임 이름을 입력해 주세요.")
    @JsonProperty(value = "meeting_name")
    private String meetingName;

    @Size(min = 0, max = 255, message = "255자 미만으로만 작성이 가능합니다.")
    @NotBlank(message = "소모임에 대해 소개해 주세요.")
    @JsonProperty(value = "meeting_description")
    private String meetingDescription;

    @NotNull(message = "소모임 날짜를 입력해 주세요.")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    @JsonProperty(value = "meeting_date")
    private LocalDateTime meetingDate;

    @Size(min = 0, max = 255, message = "45자 미만으로만 작성이 가능합니다.")
    @NotBlank(message = "소모임 장소를 입력해 주세요.")
    @JsonProperty(value = "meeting_place")
    private String meetingPlace;

    @NotNull(message = "소모임 모집 인원수를 입력해 주세요.")
    @JsonProperty(value = "meeting_people")
    private Integer meetingPeople;

    @JsonProperty(value = "meeting_image")
    private String meetingImage;

    @Email(message = "유효하지 않는 이메일 형식입니다.")
    @NotEmpty
    @JsonProperty(value = "created_by")
    private String createdBy;


}