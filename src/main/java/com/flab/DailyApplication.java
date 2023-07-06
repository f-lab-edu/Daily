package com.flab;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class DailyApplication {

	public static void main(String[] args) {
		SpringApplication.run(DailyApplication.class, args);
		log.info("Daily Project Start!"); /*프로젝트 실행을 알리는 수준으로 INFO를 사용함*/
	}
}





