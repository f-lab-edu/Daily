package com.flab.daily.controller;

import com.flab.daily.dto.response.MeetingResponseDTO;
import com.flab.daily.dto.response.PagingDTO;
import com.flab.daily.service.MeetingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/meeting")
public class MeetingController {

    private final MeetingService meetingService;

    /*프론트에서 페이지와 페이지당 보여지는 데이터 갯수 보내준다고 가정*/
    @GetMapping
    public ResponseEntity<PagingDTO> getMeetingList(int size, int page) {
        /*Meeting 목록 정보와 Page 정보를 가진 PagingDTO 반환*/
        PagingDTO pagingDTO = meetingService.findMeetingList(size, page);
        return ResponseEntity.status(HttpStatus.OK).body(pagingDTO);
    }

    @GetMapping("/{meetingId}")
    public ResponseEntity<MeetingResponseDTO> getMeetingOneById(@PathVariable @Valid Long meetingId) {
        MeetingResponseDTO meetingResponseDTO = meetingService.findMeetingOneById(meetingId);
        return ResponseEntity.status(HttpStatus.OK).body(meetingResponseDTO);
    }
}
