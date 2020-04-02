package com.batch.scheduled;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;

@SpringBootApplication
public class ScheduledJobApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScheduledJobApplication.class, args);
	}

}
