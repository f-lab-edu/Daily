package com.flab.daily.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.flab.daily.utils.PagingUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

/*응답 메세지에 함께 담겨서 보내질 Paging 정보, 데이터 정보 클래스*/
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PagingDTO {
    @JsonProperty(value="paging_info")
    private PagingUtil pagingUtil; /*페이징 정보*/

    @JsonProperty(value="data_list")
    private List dataList; /*페이징 처리될 데이터 리스트*/
}
