package com.flab.daily.service;

import com.flab.daily.dto.response.MeetingResponseDTO;
import com.flab.daily.dto.response.PaginationResponseDTO;
import com.flab.daily.exception.ErrorCode;
import com.flab.daily.exception.IsExistCheckException;
import com.flab.daily.mapper.MeetingMapper;
import com.flab.daily.paging.Pagination;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional
public class MeetingService {

    private final MeetingMapper meetingMapper;

    public Map<String, Object> findMeetingList(int size, int page) {
        /*총 소모임 수*/
        Long totalMeetingSize = meetingMapper.countMeetingAll();

        /*Paging 정보 처리*/
        Pagination pagination = new Pagination(totalMeetingSize, size, page);

        /*페이지에 맞는 소모임 리스트 추출*/
        List<MeetingResponseDTO> meetingList = meetingMapper.findMeetingList(pagination);

        /*응답 메세지에 보낼 페이지 정보를 저장*/
        PaginationResponseDTO paginationInfo = PaginationResponseDTO.builder()
                .totalSize(pagination.getTotalSize())
                .pageSize(pagination.getSize())
                .totalPage(pagination.getTotalPage())
                .currentPage(pagination.getPage())
                .prevPage(pagination.isPrevPage())
                .nextPage(pagination.isNextPage())
                .build();

        /*소모임 데이터와 페이지에 대한 정보 담아서 반환*/
        Map<String, Object> meetingResponseMap = new HashMap<>();
        meetingResponseMap.put("meetingList", meetingList);
        meetingResponseMap.put("paginationInfo", paginationInfo);

        return meetingResponseMap;
    }

    public MeetingResponseDTO findMeetingOneById(Long meetingId) {
        MeetingResponseDTO meetingResponseDTO = meetingMapper.findMeetingOneById(meetingId);
        if(meetingResponseDTO == null) {
            throw new IsExistCheckException(ErrorCode.NOT_FOUND_MEETING);
        } else {
            return meetingResponseDTO;
        }
    }
}
