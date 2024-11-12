package com.main.invenmbe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableJpaAuditing // JPA 감사 활성화
@EnableTransactionManagement
@EnableJpaRepositories
public class InvenmbeApplication {

	public static void main(String[] args) {
		SpringApplication.run(InvenmbeApplication.class, args);
	}

}
