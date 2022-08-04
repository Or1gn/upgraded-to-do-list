package com.project.Batnik;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication
@SecurityScheme(name = "Bearer authentication",
		scheme = "bearer",
		bearerFormat = "JWT",
		type = SecuritySchemeType.HTTP,
		in = SecuritySchemeIn.HEADER)
@EnableScheduling
public class BatnikApplication {

	public static void main(String[] args) {
		SpringApplication.run(BatnikApplication.class, args);
	}

}
