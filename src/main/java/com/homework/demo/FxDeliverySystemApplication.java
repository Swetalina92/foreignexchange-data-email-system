package com.homework.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class FxDeliverySystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(FxDeliverySystemApplication.class, args);
	}

}
