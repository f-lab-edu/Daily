package com.flab.daily.dto.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Category {

    스포츠(1, "스포츠");

    private final long categoryId;
    private final String categoryName;
}
