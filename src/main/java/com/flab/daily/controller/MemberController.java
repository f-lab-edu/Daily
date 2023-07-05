package com.flab.daily.controller;

import com.flab.daily.dto.request.MemberRequestDTO;
import com.flab.daily.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/members")
    public ResponseEntity<Void> signUp(@RequestBody @Valid MemberRequestDTO memberRequestDTO){
        memberService.signUp(memberRequestDTO);
        return ResponseEntity.noContent().build();
    }
}
