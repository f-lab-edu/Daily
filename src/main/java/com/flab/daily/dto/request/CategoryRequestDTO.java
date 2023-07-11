package com.flab.daily.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryRequestDTO {

    @JsonProperty(value = "category_name")
    @Size(max = 20, message = "카테고리명은 20자까지 작성이 가능합니다.")
    @NotBlank(message = "카테고리명을 입력해 주세요.")
    private String categoryName;

    @JsonProperty(value = "created_by")
    @Email(message = "유효하지 않는 이메일 형식입니다.",
            regexp = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$")
    @NotEmpty(message = "이메일을 입력해 주세요.")
    private String createdBy;

    @JsonProperty(value = "updated_by")
    @NotNull(message = "이메일은 필수 값입니다.")
    @Email(regexp = "[a-z0-9]+@[a-z0-9.]+\\.[a-z]{2,3}",
            flags = Pattern.Flag.CASE_INSENSITIVE,
            message = "이메일 형식에 맞지 않습니다.")
    private String updatedBy;

}
