package com.flab.daily.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryResponseDTO {
    @JsonProperty(value = "category_id")
    private Long categoryId;

    @JsonProperty(value = "category_name")
    private String categoryName;

    @JsonProperty(value = "created_by")
    private String createdBy;

    @JsonProperty(value = "updated_by")
    private String updatedBy;

    @JsonProperty(value = "created_date")
    private LocalDateTime createdDate;

    @JsonProperty(value = "updated_date")
    private LocalDateTime updatedDate;
}
