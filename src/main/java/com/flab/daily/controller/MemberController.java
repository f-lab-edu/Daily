package com.flab.daily.controller;

import com.flab.daily.dto.request.MemberLoginDTO;
import com.flab.daily.dto.request.MemberRequestDTO;
import com.flab.daily.dto.response.JwtResponseDTO;
import com.flab.daily.service.MemberService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.OK;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public ResponseEntity<Void> signUp(@RequestBody @Valid MemberRequestDTO memberRequestDTO){
        memberService.signUp(memberRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid MemberLoginDTO memberLoginDTO, HttpServletResponse response) {
        JwtResponseDTO jwtResponseDTO = memberService.login(memberLoginDTO);
        response.setHeader("Authorization", jwtResponseDTO.getAccessToken());
        return ResponseEntity.status(OK).body(jwtResponseDTO.getResult());
    }

    @PatchMapping("/members/{memberId}")
    public ResponseEntity<String> updateMemberInfo(@RequestBody @Valid MemberRequestDTO memberRequestDTO, @PathVariable Long memberId) {
        memberService.updateMemberInfo(memberId, memberRequestDTO);
        return ResponseEntity.status(OK).build();
    }
}
