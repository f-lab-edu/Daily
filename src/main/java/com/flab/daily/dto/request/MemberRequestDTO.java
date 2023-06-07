package com.flab.daily.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MemberRequestDTO {
    // TODO: 패턴 두개 비교 필요
//    @Pattern(regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\\\.[A-Za-z0-9_-]+)*@[^-][A-Za- z0-9-]+(\\\\.[A-Za-z0-9-]+)*(\\\\.[A-Za-z]{2,})$")
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}",
            flags = Pattern.Flag.CASE_INSENSITIVE,
            message = "이메일 형식에 맞지 않습니다")
    private String email;


    // TODO:현재 특수문자, 영문자, 영어(대소문자 구분 없이) 입력 되게 되어있음 확인후 변경
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z|A-Z])(?=.*[^\\w\\d\\s:])([^\\s]){8,16}$"
            , message = "영문, 숫자, 특수문자 중 2종류 이상을 조합하여 최소 10자 이상 입력해 주세요")
    private String password;

    @NotEmpty(message = "닉네임은 필수 값입니다.")
    private String nickname;

}
