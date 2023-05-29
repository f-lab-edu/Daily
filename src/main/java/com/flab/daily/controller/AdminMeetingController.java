package com.flab.daily.controller;

import com.flab.daily.dto.MeetingDTO;
import com.flab.daily.service.AdminMeetingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/admin")
@RestController
@RequiredArgsConstructor
public class AdminMeetingController {

    private final AdminMeetingService adminMeetingService;

    @GetMapping("/meetings")
    public MeetingDTO getMeeting() {
        return adminMeetingService.getMeeting();
    }

}
