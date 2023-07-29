package com.flab.daily.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class JwtResponseDTO {
    private String result;
    private String email;
    private String role;
    private String accessToken;
}
