package com.flab.daily.mapper;

import com.flab.daily.dao.MemberDAO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {
    int insertMember(MemberDAO member);
    int getMember(String email);
}
