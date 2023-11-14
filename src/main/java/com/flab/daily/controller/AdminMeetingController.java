package com.flab.daily.controller;

import com.flab.daily.dto.request.MeetingRequestDTO;
import com.flab.daily.dto.response.MeetingResponseDTO;
import com.flab.daily.service.AdminMeetingService;
import com.flab.daily.utils.security.jwt.JwtProvider;
import jakarta.servlet.http.HttpServletRequest;
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
    private final JwtProvider jwtProvider;

    @PostMapping("/meetings")
    public ResponseEntity<Object> addMeeting(@Valid @RequestBody MeetingRequestDTO meetingRequestDTO) {
        adminMeetingService.addMeeting(meetingRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PatchMapping("/meetings/{meetingId}")
    public ResponseEntity<MeetingResponseDTO> updateMeetingInfo(@Valid @RequestBody MeetingRequestDTO meetingRequestDTO,
            @PathVariable Long meetingId, HttpServletRequest request) {
        String updatedEmail = jwtProvider.getEmailByAccessToken(request);
        MeetingResponseDTO meetingResponseDTO = adminMeetingService.updateMeetingInfo(meetingId, updatedEmail, meetingRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).body(meetingResponseDTO);
    }
}
