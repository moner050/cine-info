package com.cineinfo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationPropertiesScan
@SpringBootApplication
public class CineinfoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CineinfoApplication.class, args);
	}

}
