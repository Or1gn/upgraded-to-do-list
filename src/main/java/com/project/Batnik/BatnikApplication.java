package com.project.Batnik;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication
public class BatnikApplication {

	public static void main(String[] args) {
		SpringApplication.run(BatnikApplication.class, args);
	}

}
