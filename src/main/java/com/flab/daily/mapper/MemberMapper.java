package com.flab.daily.mapper;

import com.flab.daily.dao.MemberDAO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {
    int insertMember(MemberDAO member);
    MemberDAO selectMemberByEmail(String email);
    int getMember(String email);
}
