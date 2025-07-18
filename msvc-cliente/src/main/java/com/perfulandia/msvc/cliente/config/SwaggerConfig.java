package com.perfulandia.msvc.cliente.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(new Info()
                        .title("Api RestFull - MSVC -  Clientes")
                        .description("\"Esta es la api dedicada al msvc de clientes")
                        .version("v1.0.0"));

    }
}
