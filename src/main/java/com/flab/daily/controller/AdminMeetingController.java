package com.flab.daily.controller;

import com.flab.daily.dto.MeetingDTO;
import com.flab.daily.service.AdminMeetingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/admin")
@RestController
@RequiredArgsConstructor
public class AdminMeetingController {

    private final AdminMeetingService adminMeetingService;

    @PostMapping("/meetings")
    public void addMeeting(@RequestBody MeetingDTO meetingDTO) {
        adminMeetingService.addMeeting(meetingDTO);
    }

    @GetMapping("/meetings")
    public MeetingDTO getMeeting() {
        return adminMeetingService.getMeeting();
    }

}
