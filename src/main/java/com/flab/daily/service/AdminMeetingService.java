package com.flab.daily.service;

import com.flab.daily.dao.MeetingDAO;
import com.flab.daily.dto.request.MeetingRequestDTO;
import com.flab.daily.mapper.CategoryMapper;
import com.flab.daily.mapper.MeetingMapper;
import com.flab.daily.mapper.MemberMapper;
import com.flab.daily.exception.ErrorCode;
import com.flab.daily.exception.IsExistCheckException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

@Service
@RequiredArgsConstructor
@Transactional
public class AdminMeetingService {

    private final MeetingMapper meetingMapper;
    private final CategoryMapper categoryMapper;
    private final MemberMapper memberMapper;

    public void addMeeting(MeetingRequestDTO meetingRequestDTO){
        //유효한 카테고리인지 검사
        int checkCategory = categoryMapper.isValidExist(meetingRequestDTO.getCategoryId());
        if(checkCategory!=1){
            throw new IsExistCheckException(ErrorCode.NOT_FOUND_CATEGORY);
        }

        //유효한 Email인지 검사
        int createdByEmail = memberMapper.isValidExist(meetingRequestDTO.getCreatedBy());
        if(createdByEmail!=1){
            throw new IsExistCheckException(ErrorCode.NOT_FOUND_EMAIL);
        }

        MeetingDAO meetingInfo = MeetingDAO.builder()
                .categoryId(meetingRequestDTO.getCategoryId())
                .meetingName(meetingRequestDTO.getMeetingName())
                .meetingDescription(meetingRequestDTO.getMeetingDescription())
                .meetingDate(meetingRequestDTO.getMeetingDate())
                .meetingPlace(meetingRequestDTO.getMeetingPlace())
                .meetingPeople(meetingRequestDTO.getMeetingPeople())
                .meetingImage(meetingRequestDTO.getMeetingImage())
                .createdBy(meetingRequestDTO.getCreatedBy())
                .build();

        try {
            meetingMapper.addMeeting(meetingInfo);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }

//        int result = meetingMapper.addMeeting(meetingInfo);
//
//
//        if(result!=1) {
//            throw new DataAccessException();
//        }

    }

}
