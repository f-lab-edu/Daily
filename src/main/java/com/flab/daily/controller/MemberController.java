package com.flab.daily.controller;

import com.flab.daily.dto.request.MemberLoginDTO;
import com.flab.daily.dto.request.MemberRequestDTO;
import com.flab.daily.dto.response.JwtResponseDTO;
import com.flab.daily.service.MemberService;
import com.flab.daily.utils.dto.ResponseDTO;
import com.flab.daily.utils.security.jwt.JwtProvider;
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
    private final JwtProvider jwtProvider;

    @PostMapping("/signup")
    public ResponseEntity<ResponseDTO> signUp(@RequestBody @Valid MemberRequestDTO memberRequestDTO){
        memberService.signUp(memberRequestDTO);
        return ResponseEntity.status(OK).body(ResponseDTO.builder().result("success").build());
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseDTO> login(@RequestBody @Valid MemberLoginDTO memberLoginDTO, HttpServletResponse response) {
        ResponseDTO responseDTO = memberService.login(memberLoginDTO);
        String accessToken = jwtProvider.generateAccessToken(memberLoginDTO.getEmail());
        response.setHeader("Authorization", accessToken);
        return ResponseEntity.status(OK).body(responseDTO);
    }

    @PatchMapping("/members")
    public ResponseEntity<ResponseDTO> updateMemberInfo(@RequestBody @Valid MemberRequestDTO memberRequestDTO) {
        memberService.updateMemberInfo(memberRequestDTO);
        return ResponseEntity.status(OK).body(ResponseDTO.builder().result("success").build());
    }
}
