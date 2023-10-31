package com.flab.daily.service;

import com.flab.daily.dao.CategoryDAO;
import com.flab.daily.dto.request.CategoryRequestDTO;
import com.flab.daily.utils.exception.DuplicateCheckException;
import com.flab.daily.utils.exception.IsExistCheckException;
import com.flab.daily.mapper.CategoryMapper;
import com.flab.daily.mapper.MemberMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class AdminCategoryServiceTest {

    @Mock
    CategoryMapper categoryMapper;
    @Mock
    MemberMapper memberMapper;

    @InjectMocks
    AdminCategoryService adminCategoryService;

    CategoryDAO categoryDAO;
    CategoryRequestDTO categoryRequestDTO;

    @BeforeEach
    void beforeEach() {
        categoryRequestDTO = CategoryRequestDTO.builder()
                .categoryName("미술")
                .createdBy("test@naver.com")
                .updatedBy("test@naver.com")
                .build();
    }

    //category 이름이 이미 존재하는 경우
    @Test
    @DisplayName("False addCategory By Duplicate Category Name Check.")
    public void addCategoryFalseByDuplicateName() {
        //given
        when(categoryMapper.isExistCategoryByName(categoryRequestDTO.getCategoryName())).thenReturn(1);

        //when-then
        assertThrows(DuplicateCheckException.class, () -> adminCategoryService.addCategory(categoryRequestDTO));
    }

    //email이 존재하지 않는 경우
    @Test
    @DisplayName("False addCategory By Invalid Email Check.")
    public void addCategoryFalseByEmail() {
        //given
        when(categoryMapper.isExistCategoryByName(categoryRequestDTO.getCategoryName())).thenReturn(0);
        when(memberMapper.getMember(categoryRequestDTO.getCreatedBy())).thenReturn(0);

        //when-then
        assertThrows(IsExistCheckException.class, () -> adminCategoryService.addCategory(categoryRequestDTO));

    }

    //addCategory가 실패하는 경우 - SQLException
    @Test
    @DisplayName("False addCategory By SQLException Check.")
    public void addCategoryFalseBySQLException() {
        //given
        when(categoryMapper.isExistCategoryByName(categoryRequestDTO.getCategoryName())).thenReturn(0);
        when(memberMapper.getMember(categoryRequestDTO.getCreatedBy())).thenReturn(1);

        categoryDAO = CategoryDAO.builder()
                .categoryName(categoryRequestDTO.getCategoryName())
                .createdBy(categoryRequestDTO.getCreatedBy())
                .build();

        when(categoryMapper.addCategory(categoryDAO)).thenReturn(0);

        //when-then
        assertThrows(RuntimeException.class, () -> adminCategoryService.addCategory(categoryRequestDTO));
    }

    //addCategory가 성공하는 경우
    @Test
    @DisplayName("Success addCategory Service Check.")
    public void addCategorySuccess() {
        //given
        categoryDAO = CategoryDAO.builder()
                .categoryName(categoryRequestDTO.getCategoryName())
                .createdBy(categoryRequestDTO.getCreatedBy())
                .build();

        when(categoryMapper.addCategory(categoryDAO)).thenReturn(1);

        //when-then
        assertThat(categoryMapper.addCategory(categoryDAO)).isEqualTo(1);
    }

    @Test
    @DisplayName("카테고리가 정상적으로 변경된 경우")
    void changeCategorySuccess() {
        Long categoryId = 12L;
        // 카테고리가 존재한 경우
        when(categoryMapper.isExistCategoryById(categoryId)).thenReturn(1);
        // 동일한 카테고리 이름이 존재하지 않을 경우
        when(categoryMapper.isExistCategoryByName(categoryRequestDTO.getCategoryName())).thenReturn(0);
        // 이메일이 존재할 경우
        when(memberMapper.getMember(categoryRequestDTO.getUpdatedBy())).thenReturn(1);
        categoryDAO = CategoryDAO.builder()
                .categoryId(categoryId)
                .categoryName(categoryRequestDTO.getCategoryName())
                .updatedBy(categoryRequestDTO.getUpdatedBy())
                .build();
        adminCategoryService.changeCategory(categoryRequestDTO, categoryId);
        // then
        verify(categoryMapper, times(1)).isExistCategoryById(categoryId);
        verify(categoryMapper, times(1)).isExistCategoryByName(categoryRequestDTO.getCategoryName());
    }


    @Test
    @DisplayName("카테고리가 존재하지 않는 경우")
    void changeCategoryEmptyCategory() {
        Long categoryId = 12L;
        // 카테고리가 존재하지 않는 경우
        when(categoryMapper.isExistCategoryById(categoryId)).thenReturn(0);
        categoryDAO = CategoryDAO.builder()
                .categoryId(categoryId)
                .categoryName(categoryRequestDTO.getCategoryName())
                .updatedBy(categoryRequestDTO.getUpdatedBy())
                .build();
        // then
        assertThrows(IsExistCheckException.class, () -> adminCategoryService.changeCategory(categoryRequestDTO, categoryId));
    }

    @Test
    @DisplayName("카테고리 이름이 중복된 경우")
    void changeCategoryDuplicationCategory() {
        Long categoryId = 12L;
        // 카테고리가 존재한 경우
        when(categoryMapper.isExistCategoryById(categoryId)).thenReturn(1);
        // 동일한 카테고리 이름이 있는 경우
        when(categoryMapper.isExistCategoryByName(categoryRequestDTO.getCategoryName())).thenReturn(1);
        categoryDAO = CategoryDAO.builder()
                .categoryId(categoryId)
                .categoryName(categoryRequestDTO.getCategoryName())
                .updatedBy(categoryRequestDTO.getUpdatedBy())
                .build();
        // then
        assertThrows(DuplicateCheckException.class, () -> adminCategoryService.changeCategory(categoryRequestDTO, categoryId));
    }

    @Test
    @DisplayName("이메일이 없는 경우")
    void changeCategoryIsNotMember() {
        Long categoryId = 12L;
        // 카테고리가 존재한 경우
        when(categoryMapper.isExistCategoryById(categoryId)).thenReturn(1);
        // 동일한 카테고리 이름이 존재하지 않을 경우
        when(categoryMapper.isExistCategoryByName(categoryRequestDTO.getCategoryName())).thenReturn(0);
        // 이메일이 존재하지 않을경우
        when(memberMapper.getMember(categoryRequestDTO.getUpdatedBy())).thenReturn(0);
        categoryDAO = CategoryDAO.builder()
                .categoryId(categoryId)
                .categoryName(categoryRequestDTO.getCategoryName())
                .updatedBy(categoryRequestDTO.getUpdatedBy())
                .build();
        // then
        assertThrows(IsExistCheckException.class, () -> adminCategoryService.changeCategory(categoryRequestDTO, categoryId));
    }


    @Test
    @DisplayName("updqte가 정상적으로 작동하지 않는경우")
    void changeCategoryFailed() {
        Long categoryId = 12L;
        // 카테고리가 존재한 경우
        when(categoryMapper.isExistCategoryById(categoryId)).thenReturn(1);
        // 동일한 카테고리 이름이 존재하지 않을 경우
        when(categoryMapper.isExistCategoryByName(categoryRequestDTO.getCategoryName())).thenReturn(0);
        // 이메일이 존재할 경우
        when(memberMapper.getMember(categoryRequestDTO.getUpdatedBy())).thenReturn(1);
        categoryDAO = CategoryDAO.builder()
                .categoryId(categoryId)
                .categoryName(categoryRequestDTO.getCategoryName())
                .updatedBy(categoryRequestDTO.getUpdatedBy())
                .build();
        when(categoryMapper.updateCategory(categoryDAO)).thenThrow(new RuntimeException());
        // then
        assertThrows(RuntimeException.class, () -> adminCategoryService.changeCategory(categoryRequestDTO, categoryId));
    }
}
