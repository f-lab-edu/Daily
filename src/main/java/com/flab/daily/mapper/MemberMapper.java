package com.flab.daily.mapper;

import com.flab.daily.dto.response.MemberResponseDTO;
import com.flab.daily.dao.MemberDAO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {
    void insertMember(MemberDAO member);
    MemberResponseDTO getMember(MemberDAO member);
}
