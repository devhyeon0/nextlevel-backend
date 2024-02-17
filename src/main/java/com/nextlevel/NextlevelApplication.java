package com.nextlevel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class NextlevelApplication {

	public static void main(String[] args) {
		SpringApplication.run(NextlevelApplication.class, args);
	}

}
