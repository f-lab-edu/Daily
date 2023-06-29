package com.flab.daily.paging;

import lombok.Getter;

@Getter
public class Pagination {
    private int size; /*한 페이지당 보여질 데이터 갯수*/
    private int page; /*접속한 페이지*/
    private int startSize; /*시작할 데이터 순서 -> OFFSET에 사용*/

    private Integer totalSize = 0; /*데이터 전체 수*/
    private int totalPage = 0; /*전체 페이지 수*/
    private boolean prevPage = false; /*이전 페이지 유무*/
    private boolean nextPage= false; /*다음 페이지 유무*/

    public Pagination (int totalSize, int size, int page) {
        this.size = size;
        this.page = page;
        this.totalSize = totalSize;
        this.startSize = size * (page-1);
        calculate();
    }

    private void calculate() {
        if(totalSize != 0) {
            totalPage = ((totalSize-1)/size+1); /*전체 페이지 추출*/
            prevPage = (page != 1); /*현재 페이지가 1이 아니면 true*/
            nextPage = (page < totalPage); /*현재 페이지가 전체 페이지 수보다 작으면 true*/
        }
    }
}
