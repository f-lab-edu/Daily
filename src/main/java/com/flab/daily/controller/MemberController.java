package com.flab.daily.controller;

import com.flab.daily.dto.request.MemberRequestDTO;
import com.flab.daily.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/members")
    public ResponseEntity<Void> signUp(@RequestBody @Valid MemberRequestDTO memberRequestDTO){
        log.info("sign up");
        memberService.signUp(memberRequestDTO);
        log.info("success");
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
