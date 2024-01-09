package com.flab.daily.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.flab.daily.utils.type.MemberType;
import com.flab.daily.utils.validator.EnumValidator;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberRequestDTO {
    // TODO: 패턴 두개 비교 필요

    /**
     *  CASE_INSENSITIVE : 대소문자를 구분하지 않는 일치를 활성화
     *  https://docs.oracle.com/javase/6/docs/api/java/util/regex/Pattern.html#CASE_INSENSITIVE
     *  [a-z0-9]+ : 영어 소문자, 1~9까지 숫자만 허용하는 규칙이 최소 한개 이상
     *  \\.[a-z]{2,3} : .이후 영어 소문자 2~3 자리
     */
    @NotNull(message = "이메일은 필수 값입니다.")
    @Email(regexp = "[a-z0-9]+@[a-z0-9.]+\\.[a-z]{2,3}",
            flags = Pattern.Flag.CASE_INSENSITIVE,
            message = "이메일 형식에 맞지 않습니다.")
    private String email;

    /**
     * ^ : 뒤에나오는 패턴이 일치한 경우
     * (?=.*\d) : 숫자를 사용한 패턴이 하위에 있는지 확인
     * (?=.*[a-z|A-Z]) : 영문을 사용한 패턴이 있는지 확인
     * (?=.*[^\w\d\s:]) : 문자, 숫자, 공백, : 조합이 아닌경우
     *  ([^\s]){8,16}$ : 공백 없이 8~ 16 글자로 끝나는
     */
    @NotNull(message = "패스워드는 필수 값입니다.")
    /*@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$"
            , message = "비밀번호는 영문, 숫자, 특수문자 중 3종류 이상을 조합하여 최소 8자 이상 입력해 주세요")*/
    private String password;

    @NotBlank(message = "닉네임은 필수 값입니다.")
    private String nickname;

    @JsonProperty(value = "member_type")
    private MemberType memberType;
}
