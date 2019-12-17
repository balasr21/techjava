package com.techjava.marvelinfo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableCaching
@EnableScheduling
@EnableSwagger2
public class MarvelinfoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MarvelinfoApplication.class, args);
	}

}
