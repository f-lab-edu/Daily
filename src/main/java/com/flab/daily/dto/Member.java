package com.flab.daily.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member {

    @NotNull(message = "이메일을 입력해 주세요.")
    @Email(message = "이메일 형식에 맞지 않습니다.")
    private String email;

    @NotNull(message = "비밀번호를 입력해 주세요")
    private String password;

    @NotNull(message = "닉네임을 입력해 주세요")
    private String nickname;

    private MemberType memberType;
}
