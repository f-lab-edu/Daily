package com.flab.daily.controller;

import com.flab.daily.dto.MeetingDTO;
import com.flab.daily.service.AdminMeetingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/admin")
@RequiredArgsConstructor
@RestController
public class AdminMeetingController {

    private final AdminMeetingService adminMeetingService;


    @PostMapping("/meetings")
    public void addMeeting(@RequestBody MeetingDTO meetingDTO) {



    }


}
