package com.flab.daily.dto.response;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class MemberResponseDTO {
    private Long memberId;
    private String email;
    private String password;
    private String nickname;
    private String memberType;
    private LocalDateTime createdDate;
    private LocalDateTime updateDate;
}
