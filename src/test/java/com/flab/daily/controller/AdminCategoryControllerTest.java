package com.flab.daily.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flab.daily.mock.WithAuthUser;
import com.flab.daily.dao.Pagination;
import com.flab.daily.dto.request.CategoryRequestDTO;
import com.flab.daily.dto.response.CategoryResponseDTO;
import com.flab.daily.dto.response.PagingDTO;
import com.flab.daily.mapper.MemberMapper;
import com.flab.daily.service.AdminCategoryService;
import com.flab.daily.utils.PagingUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;


@WebMvcTest(controllers = AdminCategoryController.class)
public class AdminCategoryControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    AdminCategoryService adminCategoryService;

    @MockBean
    MemberMapper memberMapper;

    CategoryRequestDTO categoryRequestDTO;
    String code;
    String message;

    @BeforeEach
    void beforeEach() {
        code = "$..code";
        message = "$..message";
    }

    //category 글자수가 초과된 경우
    @Test
    @DisplayName("카테고리 이름이 글자수를 초과한 경우")
    @WithAuthUser(email="test@naver.com", role="ADMIN")
    public void addCategoryFalseByCharacterCount() throws Exception {
        //given
        categoryRequestDTO = CategoryRequestDTO.builder()
                .categoryName("미술하고 싶은 사람은 여기로 오시고 싶은 사람은 저기로 오시고 싶은 사람은 그곳으로 오세요.")
                .createdBy("test@naver.com")
                .updatedBy("test@naver.com")
                .build();

        doNothing().when(adminCategoryService).addCategory(any());

        //when-then
        mockMvc.perform(post("/admin/categories")
                        .header("Authorization", "JWT FOR TEST")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(categoryRequestDTO)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath(code).value(400))
                .andExpect(jsonPath(message).value("카테고리명은 20자까지 작성이 가능합니다."));
    }

    //값을 입력하지 않은 경우
    @Test
    @DisplayName("Category @NotBlank Check.")
    public void addCategoryFalseNotBlank() throws Exception{
        //given
        categoryRequestDTO = CategoryRequestDTO.builder()
                .categoryName(" ")
                .createdBy("test@naver.com")
                .updatedBy("test@naver.com")
                .build();

        doNothing().when(adminCategoryService).addCategory(any());

        //when-then
        mockMvc.perform(post("/admin/categories")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(categoryRequestDTO)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath(code).value(400))
                .andExpect(jsonPath(message).value("카테고리명을 입력해 주세요."));
    }

    //사용자 이메일 양식이 맞지 않는 경우
    @Test
    @DisplayName("@Email Format Check.")
    public void addCategoryFalseByEmail() throws Exception{
        //given
        categoryRequestDTO = CategoryRequestDTO.builder()
                .categoryName("미술")
                .createdBy("test@naver.com")
                .updatedBy("test@naver.com")
                .build();

        doNothing().when(adminCategoryService).addCategory(any());

        //when-then
        mockMvc.perform(post("/admin/categories")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(categoryRequestDTO)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath(code).value(400))
                .andExpect(jsonPath(message).value("유효하지 않는 이메일 형식입니다."));
    }

    //성공했을 경우
    @Test
    @DisplayName("AddCategory Success Controller Check.")
    public void addCategorySuccess() throws Exception{
        //given
        categoryRequestDTO = CategoryRequestDTO.builder()
                .categoryName("미술")
                .createdBy("test@naver.com")
                .updatedBy("test@naver.com")
                .build();

        doNothing().when(adminCategoryService).addCategory(any());
        //when-then
        mockMvc.perform(post("/admin/categories")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(categoryRequestDTO)))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("카테고리 변경 성공")
    public void changeCategorySuccess() throws Exception {
        // given
        categoryRequestDTO = CategoryRequestDTO.builder()
                .categoryName("카테고리01")
                .createdBy("test@naver.com")
                .updatedBy("test@naver.com")
                .build();
        // when
        doNothing().when(adminCategoryService).changeCategory(any(), anyLong());
        ResultActions actions = mockMvc.perform(patch("/admin/categories/12")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(categoryRequestDTO)));
        // then
        actions.andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("이메일이 형식이 비어있는 경우")
    public void changeCategoryNotFoundEmail() throws Exception {
        // given
        categoryRequestDTO = CategoryRequestDTO.builder()
                .categoryName("카테고리01")
                .createdBy("test@naver.com")
                .build();
        // when
        doNothing().when(adminCategoryService).changeCategory(any(), anyLong());
        ResultActions actions = mockMvc.perform(patch("/admin/categories/12")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(categoryRequestDTO)));
        actions.andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("400"))
                .andExpect(jsonPath("$.message").value("이메일은 필수 값입니다."));
    }

    @Test
    @DisplayName("이메일 유효성 실패")
    public void changeCategoryValidationFailedEmail() throws Exception {
        // given
        categoryRequestDTO = CategoryRequestDTO.builder()
                .categoryName("카테고리01")
                .createdBy("test@naver.com")
                .updatedBy("test")
                .build();
        // when
        doNothing().when(adminCategoryService).changeCategory(any(), anyLong());
        ResultActions actions = mockMvc.perform(patch("/admin/categories/12")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(categoryRequestDTO)));
        // then
        actions.andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("400"))
                .andExpect(jsonPath("$.message").value("이메일 형식에 맞지 않습니다."));
    }

    @Test
    @DisplayName("카테고리 이름이 비어있는 경우")
    public void changeCategoryEmptyCategoryName() throws Exception {
        // given
        categoryRequestDTO = CategoryRequestDTO.builder()
                .createdBy("test@naver.com")
                .updatedBy("test@naver.com")
                .build();
        // when
        doNothing().when(adminCategoryService).changeCategory(any(), anyLong());
        ResultActions actions = mockMvc.perform(patch("/admin/categories/12")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(categoryRequestDTO)));

        // then
        actions.andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("400"))
                .andExpect(jsonPath("$.message").value("카테고리명을 입력해 주세요."));
    }


    @Test
    @DisplayName("카테고리 이름이 형식이 맞지 않는 경우")
    public void changeCategoryValidationFailedCategoryName() throws Exception {
        // given
        categoryRequestDTO = CategoryRequestDTO.builder()
                .categoryName("카테고리의 이름이 너무 너무 너무 너무 긴 경우")
                .createdBy("test@naver.com")
                .updatedBy("test@naver.com")
                .build();
        // when
        doNothing().when(adminCategoryService).changeCategory(any(), anyLong());
        ResultActions actions = mockMvc.perform(patch("/admin/categories/12")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(categoryRequestDTO)));
        // then
        actions.andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("400"))
                .andExpect(jsonPath("$.message").value("카테고리명은 20자까지 작성이 가능합니다."));
    }

    @Test
    @DisplayName("카테고리 목록 조회 성공")
    public void getCategoryListSuccess() throws Exception {
        // given
        int page = 1;
        int size = 2;
        int total = 3;
        Pagination pagination = new Pagination(size,page);
        PagingUtil pagingUtil = new PagingUtil(total, pagination);
        var list = new ArrayList<CategoryResponseDTO>();
        for (long i = 0; i < size; i++) {
            CategoryResponseDTO categoryResponseDTO =  CategoryResponseDTO.builder()
                    .categoryId(i + 1)
                    .categoryName("카테고리" + (i + 1))
                    .build();
            list.add(categoryResponseDTO);
        }
        PagingDTO pagingDTO = PagingDTO.builder()
                .dataList(list)
                .pagingUtil(pagingUtil)
                .build();
        //when
        when(adminCategoryService.getCategoryList(size, page)).thenReturn(pagingDTO);
        ResultActions actions = mockMvc.perform(get("/admin/categories")
                .param("size", "2")
                .param("page", "1")
                .contentType(MediaType.APPLICATION_JSON));
        //Then
        actions.andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(pagingDTO)));
    }

}
