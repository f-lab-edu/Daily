package com.flab.daily.service;

import com.flab.daily.dao.MeetingDAO;
import com.flab.daily.dto.response.MeetingResponseDTO;
import com.flab.daily.dto.response.PagingDTO;
import com.flab.daily.mapper.CategoryMapper;
import com.flab.daily.utils.exception.ErrorCode;
import com.flab.daily.utils.exception.IsExistCheckException;
import com.flab.daily.mapper.MeetingMapper;
import com.flab.daily.dao.Pagination;
import com.flab.daily.utils.PagingUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MeetingService {

    private final MeetingMapper meetingMapper;
    private final CategoryMapper categoryMapper;

    @Transactional(readOnly = true)
    public PagingDTO findMeetingList(int size, int page) {
        /*총 소모임 수*/
        long totalMeetingSize = meetingMapper.countMeetingAll();

        /*DB로 보내질 페이징 정보 처리*/
        Pagination pagination = new Pagination(size, page);
        /*페이지에 맞는 소모임 리스트 추출*/
        List<MeetingDAO> meetingList = meetingMapper.findMeetingList(pagination);

        /*MeetingResponseDTO로 저장*/
        List<MeetingResponseDTO> meetingListInfo = new ArrayList<>();
        for (MeetingDAO meetingDAO : meetingList) {
            MeetingResponseDTO meetingResponseDTO = MeetingResponseDTO.builder()
                    .meetingId(meetingDAO.getMeetingId())
                    .categoryId(meetingDAO.getCategoryId())
                    .meetingName(meetingDAO.getMeetingName())
                    .meetingDescription(meetingDAO.getMeetingDescription())
                    .meetingDate(meetingDAO.getMeetingDate())
                    .meetingPlace(meetingDAO.getMeetingPlace())
                    .meetingPeople(meetingDAO.getMeetingPeople())
                    .currentPeople(meetingDAO.getCurrentPeople())
                    .meetingImage(meetingDAO.getMeetingImage())
                    .createdBy(meetingDAO.getCreatedBy())
                    .createdDate(meetingDAO.getCreatedDate())
                    .updatedDate(meetingDAO.getUpdatedDate())
                    .build();

            meetingListInfo.add(meetingResponseDTO);
        }

        /*응답 메세지에 담길 PagingUtil 정보 처리*/
        PagingUtil pagingUtil = new PagingUtil(totalMeetingSize, pagination);

        /*처리된 페이징 정보와 데이터 정보 저장하여 반환*/

        return PagingDTO.builder()
                .pagingUtil(pagingUtil)
                .dataList(meetingListInfo)
                .build();
    }

    @Transactional(readOnly = true)
    public MeetingResponseDTO findMeetingOneById(Long meetingId) {
        MeetingDAO meetingDAO = meetingMapper.findMeetingOneById(meetingId);
        if(meetingDAO == null) {
            throw new IsExistCheckException(ErrorCode.NOT_FOUND_MEETING);
        } else {
            return MeetingResponseDTO.builder()
                    .meetingId(meetingDAO.getMeetingId())
                    .categoryId(meetingDAO.getCategoryId())
                    .meetingName(meetingDAO.getMeetingName())
                    .meetingDescription(meetingDAO.getMeetingDescription())
                    .meetingDate(meetingDAO.getMeetingDate())
                    .meetingPlace(meetingDAO.getMeetingPlace())
                    .meetingPeople(meetingDAO.getMeetingPeople())
                    .currentPeople(meetingDAO.getCurrentPeople())
                    .meetingImage(meetingDAO.getMeetingImage())
                    .createdBy(meetingDAO.getCreatedBy())
                    .createdDate(meetingDAO.getCreatedDate())
                    .updatedDate(meetingDAO.getUpdatedDate())
                    .build();
        }
    }

    public PagingDTO getMeetingListByCategoryId(int size, int page, Long categoryId) {
        /*CategoryId 검사*/
        int categoryCheck = categoryMapper.isExistCategoryById(categoryId);
        if(categoryCheck == 0) {
            throw new IsExistCheckException(ErrorCode.NOT_FOUND_CATEGORY);
        }
        /*해당 카테고리에 속한 meeting 전체 수*/
        Long totalMeetingSize = meetingMapper.countMeetingByCategoryId(categoryId);

        Pagination pagination = new Pagination(size, page);

        List<MeetingDAO> meetingList = meetingMapper.findMeetingListByCategoryId(pagination, categoryId);

        List<MeetingResponseDTO> meetingListInfo = new ArrayList<>();
        for (MeetingDAO meetingDAO : meetingList) {
            MeetingResponseDTO meetingResponseDTO = MeetingResponseDTO.builder()
                    .meetingId(meetingDAO.getMeetingId())
                    .categoryId(meetingDAO.getCategoryId())
                    .meetingName(meetingDAO.getMeetingName())
                    .meetingDescription(meetingDAO.getMeetingDescription())
                    .meetingDate(meetingDAO.getMeetingDate())
                    .meetingPlace(meetingDAO.getMeetingPlace())
                    .meetingPeople(meetingDAO.getMeetingPeople())
                    .currentPeople(meetingDAO.getCurrentPeople())
                    .meetingImage(meetingDAO.getMeetingImage())
                    .createdBy(meetingDAO.getCreatedBy())
                    .createdDate(meetingDAO.getCreatedDate())
                    .updatedDate(meetingDAO.getUpdatedDate())
                    .build();
        }

        PagingUtil pagingUtil = new PagingUtil(totalMeetingSize, pagination);

        return PagingDTO.builder()
                .dataList(meetingList)
                .pagingUtil(pagingUtil)
                .build();
    }
}
