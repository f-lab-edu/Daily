package com.flab.daily.controller;

import com.flab.daily.dto.Member;
import com.flab.daily.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;


    @PostMapping("/members")
    public ResponseEntity<Void> signUp(final @RequestBody @Valid Member member) {
        memberService.signUp(member);
        return ResponseEntity.noContent().build();
    }


}
