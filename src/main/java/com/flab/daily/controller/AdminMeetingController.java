package com.flab.daily.controller;

import com.flab.daily.dto.request.MeetingRequestDto;
import com.flab.daily.service.AdminMeetingService;
import com.flab.daily.utils.exception.ErrorCode;
import com.flab.daily.utils.exception.IsInvalidLocalDateException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RequestMapping("/admin")
@RestController
@RequiredArgsConstructor
public class AdminMeetingController {

    private final AdminMeetingService adminMeetingService;

    @PostMapping("/meetings")
    public ResponseEntity<Object> addMeeting(@Valid @RequestBody MeetingRequestDto meetingRequestDTO) {
        //소모임 날짜가 현재 날짜보다 이후인지 확인
        if(meetingRequestDTO.getMeetingDate().isBefore(LocalDateTime.now())) {
            throw new IsInvalidLocalDateException(ErrorCode.INVALID_LOCALDATE);
        }
        adminMeetingService.addMeeting(meetingRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build(); // 201 Created.
    }

}
