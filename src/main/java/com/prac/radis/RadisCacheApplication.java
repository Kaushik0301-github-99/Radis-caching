package com.prac.radis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class RadisCacheApplication {

	public static void main(String[] args) {
		SpringApplication.run(RadisCacheApplication.class, args);
	}

}
