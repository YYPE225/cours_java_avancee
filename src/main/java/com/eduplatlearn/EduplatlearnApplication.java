package com.eduplatlearn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.eduplatlearn") // on mets ici le vrai packages racine de tes entites
@EnableJpaRepositories("com.eduplatlearn") // ou le package de tes repositories
public class EduplatlearnApplication {

	public static void main(String[] args) {
		SpringApplication.run(EduplatlearnApplication.class, args);
	}

}
