package com.flab.daily.controller;

import com.flab.daily.dto.request.CategoryRequestDTO;
import com.flab.daily.service.AdminCategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminCategoryController {

    private final AdminCategoryService adminCategoryService;

    @PostMapping(value = "/categories")
    public ResponseEntity<Object> addCategory(@Valid @RequestBody CategoryRequestDTO categoryRequestDTO) {
        adminCategoryService.addCategory(categoryRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PatchMapping(value="/categories/{categoryId}")
    public ResponseEntity<Void> changeCategory(@Valid @RequestBody CategoryRequestDTO categoryRequestDTO, @PathVariable Long categoryId){
        adminCategoryService.changeCategory(categoryRequestDTO, categoryId);
        return ResponseEntity.noContent().build();
    }
}
