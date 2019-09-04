package com.yiling.pioneer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class PioneerApplication {

	public static void main(String[] args) {
		SpringApplication.run(PioneerApplication.class, args);
	}

}
