package com.example.biedronka_saver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan("com.example.biedronka_saver.config.properties")
public class BiedronkaSaverApplication {

	public static void main(String[] args) {
		SpringApplication.run(BiedronkaSaverApplication.class, args);
	}

}
