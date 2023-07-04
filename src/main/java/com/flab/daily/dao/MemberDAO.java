package com.flab.daily.dao;

import com.flab.daily.type.MemberType;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;


@Data
@Builder
public class MemberDAO {

    private Long memberId;
    private String email;
    private String password;
    private String nickname;
    private MemberType memberType;
    private LocalDateTime createdDate;
    private LocalDateTime updateDate;
}
