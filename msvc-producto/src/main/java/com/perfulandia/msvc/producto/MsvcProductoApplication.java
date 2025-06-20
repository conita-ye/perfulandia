package com.perfulandia.msvc.producto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class MsvcProductoApplication {
    public static void main(String[] args) { SpringApplication.run(MsvcProductoApplication.class, args); }
}
