package com.flab.daily.dao;

import lombok.Getter;

/*DB로 데이터 요청 보낼 때 사용되는 페이지 정보 클래스*/
@Getter
public class Pagination {
    private int size; /*한 페이지당 보여질 데이터 갯수*/
    private int page; /*접속한 페이지*/
    private int startSize; /*시작할 데이터 순서 -> OFFSET에 사용*/

    public Pagination(int size, int page) {
        this.size = size;
        this.page = page;
        startSize = size * (page-1);
    }
}
