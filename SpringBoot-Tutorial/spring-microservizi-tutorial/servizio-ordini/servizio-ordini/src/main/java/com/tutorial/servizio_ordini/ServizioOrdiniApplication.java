package com.tutorial.servizio_ordini;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ServizioOrdiniApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServizioOrdiniApplication.class, args);
	}

}
