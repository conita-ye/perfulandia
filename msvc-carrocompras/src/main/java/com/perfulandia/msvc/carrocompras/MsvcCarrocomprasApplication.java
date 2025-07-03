package com.perfulandia.msvc.carrocompras;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication

public class MsvcCarrocomprasApplication {
	public static void main(String[] args) {
		SpringApplication.run(MsvcCarrocomprasApplication.class, args);
	}

}
