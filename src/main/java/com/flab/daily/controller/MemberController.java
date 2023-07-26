package com.flab.daily.controller;

import com.flab.daily.dto.request.MemberLoginDTO;
import com.flab.daily.dto.request.MemberRequestDTO;
import com.flab.daily.dto.response.JwtResponseDTO;
import com.flab.daily.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.OK;

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

    @PostMapping("/login")
    public ResponseEntity<JwtResponseDTO> login(@RequestBody @Valid MemberLoginDTO memberLoginDTO) {
        JwtResponseDTO jwtResponseDTO = memberService.login(memberLoginDTO);
        log.info("LOGIN 결과 : " + jwtResponseDTO.getResult());
        return ResponseEntity.status(OK).body(jwtResponseDTO);
    }
}
