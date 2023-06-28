package com.flab.daily.service;

import com.flab.daily.dto.response.MeetingResponseDTO;
import com.flab.daily.exception.ErrorCode;
import com.flab.daily.exception.IsExistCheckException;
import com.flab.daily.mapper.MeetingMapper;
import com.flab.daily.paging.Pageable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MeetingService {

    private final MeetingMapper meetingMapper;

    public List<MeetingResponseDTO> findMeetingList(int size, int page) {
        Pageable pageable = new Pageable(size, page);
        int totalSize = meetingMapper.countMeetingAll();

        List<MeetingResponseDTO> meetingList = meetingMapper.findMeetingList(pageable);
        return meetingList;
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
