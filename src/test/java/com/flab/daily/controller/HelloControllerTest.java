package com.flab.daily.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = HelloController.class)
public class HelloControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @DisplayName("Hello World 확인")
    @Test
    void helloControllerTest() throws Exception {

        mockMvc.perform(get("/")) // MockMVC를 통해 /주소로 GET 요청
                .andExpect(status().isOk()) // 통신 상태가 200임을 확인
                .andExpect(content().string("Hello World")); // Hello World를 반환하는지 확인

    }
}
