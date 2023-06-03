package com.flab.daily.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {
    int isValidExist(String createdBy);
}
