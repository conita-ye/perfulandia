package com.perfulandia.msvc.cliente;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class MsvcClienteApplication {
    public static void main(String[] args) { SpringApplication.run(MsvcClienteApplication.class, args); }
}
