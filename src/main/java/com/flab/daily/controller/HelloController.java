package com.flab.daily.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class HelloController {

    //Hello World Get API 메서드
    @GetMapping("/")
    @ResponseBody
    public String hello() {
        return "Hello World";
    }

}
