package com.perfulandia.msvc.boleta.config;


import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenApi(){
        return new OpenAPI()
                .components (new Components())
                .info(new Info()
                        .title("Api RestFull - MSVC -  Boletas")
                        .description("Esta es la api dedicada al msvc de productos")
                        .version("v1.0.0"));
    }
}
