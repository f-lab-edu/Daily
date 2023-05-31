package com.flab.daily.controller;

import com.flab.daily.dto.MeetingDTO;
import com.flab.daily.service.AdminMeetingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/admin")
@RestController
@RequiredArgsConstructor
public class AdminMeetingController {

    private final AdminMeetingService adminMeetingService;

    @PostMapping("/meetings")
    public void addMeeting(@Valid @RequestBody MeetingDTO meetingDTO) {

        adminMeetingService.addMeeting(meetingDTO);

    }


    //MariaDB 연결 테스트용 - meeting 테이블에 있는 데이터 반환 확인 완료
    @GetMapping("/meetings")
    public MeetingDTO getMeeting() {
        return adminMeetingService.getMeeting();
    }

}
