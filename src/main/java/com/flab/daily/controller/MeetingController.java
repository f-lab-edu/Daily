package com.flab.daily.controller;

import com.flab.daily.dto.response.MeetingResponseDTO;
import com.flab.daily.service.MeetingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
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

    @GetMapping(value="/meeting/{meetingId}")
    public ResponseEntity<MeetingResponseDTO> findMeetingOneById(@PathVariable Long meetingId) {
        MeetingResponseDTO meetingResponseDTO = meetingService.findMeetingOneById(meetingId);
        return ResponseEntity.status(HttpStatus.OK).body(meetingResponseDTO);
    }
}
