package com.flab.daily.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ControllerAdvice;

@Service
public class PongService {

    public String pong(){
        return "pong!";
    }
}
