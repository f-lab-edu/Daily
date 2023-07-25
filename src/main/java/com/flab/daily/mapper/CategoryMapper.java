package com.flab.daily.mapper;

import com.flab.daily.dao.CategoryDAO;
import com.flab.daily.dao.Pagination;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryMapper {
    Long getCategoryListTotal();
    List<CategoryDAO> getCategoryList(Pagination pagination);
    int isExistCategoryById(Long categoryId);
    int isExistCategoryByName(String categoryName);
    int addCategory(CategoryDAO categoryDAO);
    int updateCategory(CategoryDAO categoryDAO);
}
