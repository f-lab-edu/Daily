package com.flab.daily.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CategoryMapper {

    int isValidExist(long categoryId);
}
