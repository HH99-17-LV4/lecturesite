package com.leeseo.lecturesite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class LecturesiteApplication {

	public static void main(String[] args) {
		SpringApplication.run(LecturesiteApplication.class, args);
	}

}
