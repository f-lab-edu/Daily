package com.flab.daily.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/*응답 메세지에 함께 담겨서 보내질 Paging 정보 클래스*/
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PaginationResponseDTO {
    @JsonProperty(value="total_size")
    private Long totalSize; /*데이터 전체 수*/

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
}
