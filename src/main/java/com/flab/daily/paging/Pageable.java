package com.flab.daily.paging;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Pageable {
    private int size; /*한 페이지당 보여질 데이터 갯수*/
    private int page; /*접속한 페이지*/
    private Pagination pagination;
}
