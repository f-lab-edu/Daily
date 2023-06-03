package com.flab.daily.service;

import com.flab.daily.dao.MeetingDAO;
import com.flab.daily.dto.request.RequestMeetingDTO;
import com.flab.daily.mapper.CategoryMapper;
import com.flab.daily.mapper.MeetingMapper;
import com.flab.daily.utils.exception.ErrorCode;
import com.flab.daily.utils.exception.ValidCheckException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminMeetingService {

    private final MeetingMapper meetingMapper;
    private final CategoryMapper categoryMapper;

    public void addMeeting(RequestMeetingDTO requestMeetingDTO) {

        //유효한 카테고리값ID인지 검사
        int checkCategory = categoryMapper.isValidExist(requestMeetingDTO.getCategoryId());

        if(checkCategory!=1){
            throw new ValidCheckException(ErrorCode.NOT_FOUND_CATEGORY);
        }

        MeetingDAO meetingInfo = MeetingDAO.builder()
                .categoryId(requestMeetingDTO.getCategoryId())
                .meetingName(requestMeetingDTO.getMeetingName())
                .meetingDescription(requestMeetingDTO.getMeetingDescription())
                .meetingDate(requestMeetingDTO.getMeetingDate())
                .meetingPlace(requestMeetingDTO.getMeetingPlace())
                .meetingPeople(requestMeetingDTO.getMeetingPeople())
                .meetingImage(requestMeetingDTO.getMeetingImage())
                .createdBy(requestMeetingDTO.getCreatedBy())
                .build();


        meetingMapper.addMeeting(meetingInfo);
    }

}
