package com.flab.daily.service;

import com.flab.daily.dao.MeetingDAO;
import com.flab.daily.dto.request.MeetingRequestDAO;
import com.flab.daily.mapper.CategoryMapper;
import com.flab.daily.mapper.MeetingMapper;
import com.flab.daily.mapper.MemberMapper;
import com.flab.daily.exception.ErrorCode;
import com.flab.daily.exception.IsExistCheckException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class AdminMeetingService {

    private final MeetingMapper meetingMapper;
    private final CategoryMapper categoryMapper;
    private final MemberMapper memberMapper;

    public void addMeeting(MeetingRequestDAO meetingRequestDAO) {
        //유효한 카테고리인지 검사
        int checkCategory = categoryMapper.isValidExist(meetingRequestDAO.getCategoryId());
        if(checkCategory!=1){
            throw new IsExistCheckException(ErrorCode.NOT_FOUND_CATEGORY);
        }

        //유효한 Email인지 검사
        int createdByEmail = memberMapper.isValidExist(meetingRequestDAO.getCreatedBy());
        if(createdByEmail!=1){
            throw new IsExistCheckException(ErrorCode.NOT_FOUND_EMAIL);
        }

        MeetingDAO meetingInfo = MeetingDAO.builder()
                .categoryId(meetingRequestDAO.getCategoryId())
                .meetingName(meetingRequestDAO.getMeetingName())
                .meetingDescription(meetingRequestDAO.getMeetingDescription())
                .meetingDate(meetingRequestDAO.getMeetingDate())
                .meetingPlace(meetingRequestDAO.getMeetingPlace())
                .meetingPeople(meetingRequestDAO.getMeetingPeople())
                .meetingImage(meetingRequestDAO.getMeetingImage())
                .createdBy(meetingRequestDAO.getCreatedBy())
                .build();

        meetingMapper.addMeeting(meetingInfo);
    }

}
