package com.flab.daily.mapper;

import com.flab.daily.dto.Member;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {
    void insertMember(Member member);
}
