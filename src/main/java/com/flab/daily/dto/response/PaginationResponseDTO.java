package com.flab.daily.dto.response;

import com.flab.daily.paging.Pagination;

import java.util.ArrayList;
import java.util.List;

public class PaginationResponseDTO {
    private List<Object> dataList = new ArrayList<>();
    private Pagination pagination;

    public PaginationResponseDTO(List<Object> dataList, Pagination pagination) {
        this.dataList.addAll(dataList);
        this.pagination = pagination;
    }
}
