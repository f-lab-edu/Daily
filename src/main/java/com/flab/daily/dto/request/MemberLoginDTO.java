package com.flab.daily.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberLoginDTO {
    @NotNull(message = "이메일은 필수 값입니다.")
    @Email(regexp = "[a-z0-9]+@[a-z0-9.]+\\.[a-z]{2,3}",
            flags = Pattern.Flag.CASE_INSENSITIVE,
            message = "이메일 형식에 맞지 않습니다.")
    private String email;

    @NotNull(message = "패스워드는 필수 값입니다.")
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z|A-Z])(?=.*[^\\w\\d\\s:])([^\\s]){8,16}$"
            , message = "영문, 숫자, 특수문자 중 3종류 이상을 조합하여 최소 8자 이상 입력해 주세요")
    private String password;
}
