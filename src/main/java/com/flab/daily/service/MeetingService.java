package com.flab.daily.service;

import com.flab.daily.dto.response.MeetingResponseDTO;
import com.flab.daily.dto.response.PagingDTO;
import com.flab.daily.exception.ErrorCode;
import com.flab.daily.exception.IsExistCheckException;
import com.flab.daily.mapper.MeetingMapper;
import com.flab.daily.dao.Pagination;
import com.flab.daily.util.PagingUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MeetingService {

    private final MeetingMapper meetingMapper;

    public PagingDTO findMeetingList(int size, int page) {
        /*총 소모임 수*/
        long totalMeetingSize = meetingMapper.countMeetingAll();

        /*DB로 보내질 페이징 정보 처리*/
        Pagination pagination = new Pagination(size, page);
        /*페이지에 맞는 소모임 리스트*/
        List<MeetingResponseDTO> meetingList = meetingMapper.findMeetingList(pagination);

        /*응답 메세지에 담길 PagingUtil 정보 처리*/
        PagingUtil pagingUtil = new PagingUtil(totalMeetingSize, pagination);

        /*처리된 페이징 정보와 데이터 정보 저장하여 반환*/
        PagingDTO paginationInfo = PagingDTO.builder()
                .pagingUtil(pagingUtil)
                .dataList(meetingList)
                .build();

        return paginationInfo;
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
