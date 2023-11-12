package com.flab.daily.dto.response;

import com.flab.daily.utils.type.MemberType;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class JwtResponseDTO {
    private String result;
    private String email;
    private MemberType role;
    private String accessToken;
}
