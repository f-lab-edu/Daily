package com.flab.daily.controller;

import com.flab.daily.service.PongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PongController {

    @Autowired
    private PongService pongService;

    @GetMapping("/ping")
    @ResponseBody
    public String pong() {
        return pongService.pong();
    }
}
