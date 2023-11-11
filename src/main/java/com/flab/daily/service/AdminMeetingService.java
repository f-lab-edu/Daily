package com.flab.daily.service;

import com.flab.daily.dao.MeetingDAO;
import com.flab.daily.dto.request.MeetingRequestDTO;
import com.flab.daily.dto.response.MeetingResponseDTO;
import com.flab.daily.mapper.CategoryMapper;
import com.flab.daily.mapper.MeetingMapper;
import com.flab.daily.mapper.MemberMapper;
import com.flab.daily.utils.exception.ErrorCode;
import com.flab.daily.utils.exception.IsExistCheckException;
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

    public void addMeeting(MeetingRequestDTO meetingRequestDTO) {
        //유효한 카테고리인지 검사
        int checkCategory = categoryMapper.isExistCategoryById(meetingRequestDTO.getCategoryId());
        if (checkCategory != 1) {
            throw new IsExistCheckException(ErrorCode.NOT_FOUND_CATEGORY);
        }

        //유효한 Email인지 검사
        int createdByEmail = memberMapper.getMember(meetingRequestDTO.getCreatedBy());
        if (createdByEmail != 1) {
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

        int result = meetingMapper.addMeeting(meetingInfo);
        if (result == 0) {
            throw new RuntimeException("Failed to addMeeting."); // RuntimeException 발생
        }
    }

    public MeetingResponseDTO updateMeetingInfo(Long meetingId, String updatedEmail, MeetingResponseDTO meetingResponseDTO) {

        /*1. meetingId 가 유효한 아이디값인지 확인*/

        /*2. 업데이트한 사용자가 생성자가 맞는지 확인*/

        /*3. 수정된 categoryId가 유효한 아이디값인지 확인*/

        return null;
    }
}
