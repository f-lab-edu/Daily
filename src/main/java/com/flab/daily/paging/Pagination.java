package com.flab.daily.paging;

import lombok.Getter;

@Getter
public class Pagination {

    private int totalSize;
    private int totalPage;
    private int startPage;
    private int endPage;
    private int limit;
    private boolean prevPage;
    private boolean nextPage;

    public Pagination(int totalSize, Pageable pageable) {
        if(totalSize > 0) {
            this.totalSize = totalSize;
            calculate(pageable);
        }
    }

    private void calculate(Pageable pageable) {
        totalPage = ((totalSize-1)/pageable.getSize()+1);
        if(pageable.getPage() > totalPage) {
            pageable.setPage(totalPage);
        }

        limit = (pageable.getPage()-1)*pageable.getSize();

        prevPage = (startPage != 1);
        nextPage = (endPage*pageable.getSize()) < totalSize;
    }
}
