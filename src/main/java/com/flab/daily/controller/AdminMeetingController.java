package com.flab.daily.controller;

import com.flab.daily.dto.request.MeetingRequestDAO;
import com.flab.daily.service.AdminMeetingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/admin")
@RestController
@RequiredArgsConstructor
public class AdminMeetingController {

    private final AdminMeetingService adminMeetingService;

    @PostMapping("/meetings")
    public ResponseEntity<Object> addMeeting(@Valid @RequestBody MeetingRequestDAO meetingRequestDAO) {
        adminMeetingService.addMeeting(meetingRequestDAO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
