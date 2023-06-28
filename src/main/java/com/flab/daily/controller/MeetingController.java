package com.flab.daily.controller;

import com.flab.daily.dto.response.MeetingResponseDTO;
import com.flab.daily.service.MeetingService;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/meeting")
public class MeetingController {

    private final MeetingService meetingService;

    /*프론트에서 페이지와 페이지당 보여지는 데이터 갯수 보내준다고 가정*/
    @GetMapping
    public ResponseEntity<List<MeetingResponseDTO>> getMeetingList(int size, int page) {
        List<MeetingResponseDTO> meetingList = meetingService.findMeetingList(size, page);
        return ResponseEntity.status(HttpStatus.OK).body(meetingList);
    }

    @GetMapping("/{meetingId}")
    public ResponseEntity<MeetingResponseDTO> getMeetingOneById(@PathVariable @Valid Long meetingId) {
        MeetingResponseDTO meetingResponseDTO = meetingService.findMeetingOneById(meetingId);
        return ResponseEntity.status(HttpStatus.OK).body(meetingResponseDTO);
    }


}
