package com.flab.daily.controller;

import com.flab.daily.service.PongService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PongController {

    private final PongService pongService;

    @GetMapping("/ping")
    public String pong() {
        return pongService.pong();
    }
}
