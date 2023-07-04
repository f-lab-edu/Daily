package com.flab.daily.mapper;

import com.flab.daily.dao.CategoryDAO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CategoryMapper {
    int isExistCategoryById(Long categoryId);
    int isExistCategoryByName(String categoryName);
    int addCategory(CategoryDAO categoryDAO);
}
