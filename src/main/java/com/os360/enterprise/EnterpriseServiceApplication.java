package com.os360.enterprise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.os360.enterprise", "com.os360.hr"})
public class EnterpriseServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EnterpriseServiceApplication.class, args);
	}

}
