package com.flab.daily.service;

import com.flab.daily.dao.CategoryDAO;
import com.flab.daily.dto.request.CategoryRequestDTO;
import com.flab.daily.exception.DuplicateCheckException;
import com.flab.daily.exception.ErrorCode;
import com.flab.daily.exception.IsExistCheckException;
import com.flab.daily.mapper.CategoryMapper;
import com.flab.daily.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AdminCategoryService {

    private final CategoryMapper categoryMapper;
    private final MemberMapper memberMapper;

    public void addCategory(CategoryRequestDTO categoryRequestDTO) {
        //카테고리명 중복 확인
        int checkCategory = categoryMapper.isExistCategoryByName(categoryRequestDTO.getCategoryName());
        if (checkCategory != 0) {
            throw new DuplicateCheckException(ErrorCode.VALUE_ALREADY_EXISTS);
        }

        //유효한 Email인지 검사
        int createdByEmail = memberMapper.getMember(categoryRequestDTO.getCreatedBy());
        if (createdByEmail != 1) {
            throw new IsExistCheckException(ErrorCode.NOT_FOUND_EMAIL);
        }

        CategoryDAO categoryInfo = CategoryDAO.builder()
                .categoryName(categoryRequestDTO.getCategoryName())
                .createdBy(categoryRequestDTO.getCreatedBy())
                .build();

        categoryMapper.addCategory(categoryInfo);

        //insert한 데이터 PK값 추출해서 확인
        Long result = categoryInfo.getCategoryId();
        if (result == 0) {
            throw new RuntimeException("Failed to addCategory.");
        }
    }

    public void changeCategory(CategoryRequestDTO categoryRequestDTO, Long categoryId) {

        int checkCategory = categoryMapper.isExistCategoryById(categoryId);
        if (checkCategory == 0) {
            throw new IsExistCheckException(ErrorCode.NOT_FOUND_CATEGORY);
        }
        checkedCategoryNameDuplication(categoryRequestDTO.getCategoryName());
        checkedEmailValidation(categoryRequestDTO.getUpdatedBy());
        CategoryDAO categoryInfo = CategoryDAO.builder()
                .categoryId(categoryId)
                .categoryName(categoryRequestDTO.getCategoryName())
                .updatedBy(categoryRequestDTO.getUpdatedBy())
                .build();
        categoryMapper.updateCategory(categoryInfo);

    }

    // 이메일 및 카테고리 확인
    private void checkedEmailValidation(String email) {
        //유효한 Email인지 검사
        int checkedEmail = memberMapper.getMember(email);
        if (checkedEmail != 1) {
            throw new IsExistCheckException(ErrorCode.NOT_FOUND_EMAIL);
        }
    }

    // 카테고리 이름 중복 체크
    private void checkedCategoryNameDuplication(String categoryName) {
        int checkedCategoryName = categoryMapper.isExistCategoryByName(categoryName);
        if (checkedCategoryName != 0) {
            throw new DuplicateCheckException(ErrorCode.DUPLICATED_BY_CATEGORY_NAME);
        }
    }
}
