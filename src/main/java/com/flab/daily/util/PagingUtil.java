package com.flab.daily.util;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.flab.daily.dao.Pagination;
import lombok.Getter;

@Getter
public class PagingUtil {
    @JsonProperty(value="total_size")
    private long totalSize; /*데이터 전체 수*/

    @JsonProperty(value="page_size")
    private int pageSize; /*한 페이지당 보여지는 데이터 갯수*/

    @JsonProperty(value="total_page")
    private int totalPage; /*전체 페이지 수*/

    @JsonProperty(value="current_page")
    private int currentPage; /*현재 페이지*/

    @JsonProperty(value="has_prev_page")
    private boolean prevPage; /*이전 페이지 유무*/

    @JsonProperty(value="has_next_page")
    private boolean nextPage; /*다음 페이지 유무*/

    public PagingUtil(long totalSize, Pagination pagination) {
        this.pageSize = pagination.getSize();
        this.currentPage = pagination.getPage();
        this.totalSize = totalSize;
        calculate(pagination); /*응답 메세지에 담을 페이징 정보 계산*/
    }

    private void calculate(Pagination pagination) {
        if(totalSize != 0) {
            totalPage = (int)((totalSize-1)/pagination.getSize()+1); /*전체 페이지 추출*/
            prevPage = (pagination.getPage() != 1); /*현재 페이지가 1이 아니면 true*/
            nextPage = (pagination.getPage() < totalPage); /*현재 페이지가 전체 페이지 수보다 작으면 true*/
        }
    }
}
