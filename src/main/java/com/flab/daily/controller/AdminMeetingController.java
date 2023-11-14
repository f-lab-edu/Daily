package com.flab.daily.controller;

import com.flab.daily.dto.request.MeetingRequestDTO;
import com.flab.daily.dto.response.MeetingResponseDTO;
import com.flab.daily.service.AdminMeetingService;
import com.flab.daily.utils.security.jwt.JwtProvider;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name=" Daily 모임 API", description = "Daily 사이트의  모임 목록을 조회합니다.")
@RequestMapping("/admin")
@RestController
@RequiredArgsConstructor
public class AdminMeetingController {

    private final AdminMeetingService adminMeetingService;
    private final JwtProvider jwtProvider;

    @Operation(summary = "모임 추가", description = "새로운 모임을 추가합니다.")
    @PostMapping("/meetings")
    public ResponseEntity<Object> addMeeting(@Parameter(name="meetingRequestDTO", description = "모임 정보", required = true) @Valid @RequestBody MeetingRequestDTO meetingRequestDTO) {
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
