package com.flab.daily.dao;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CategoryDAO {
    private String categoryName;
    private String createdBy;
}
