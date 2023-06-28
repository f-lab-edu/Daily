package com.flab.daily.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Pageable {
    private int size; /*한 페이지당 보여질 데이터 갯수*/
    private int page; /*접속한 페이지*/
}
