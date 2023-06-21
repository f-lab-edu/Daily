package com.flab.daily.service;

import com.flab.daily.dao.CategoryDAO;
import com.flab.daily.dto.request.CategoryRequestDTO;
import com.flab.daily.exception.DuplicateCheckException;
import com.flab.daily.exception.IsExistCheckException;
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
}
