package com.converter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan
@Configuration
@SpringBootConfiguration
@SpringBootApplication
@EnableCaching
public class ExcelConvApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExcelConvApplication.class, args);
	}
}
