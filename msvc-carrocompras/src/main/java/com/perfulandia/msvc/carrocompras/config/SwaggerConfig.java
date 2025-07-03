package com.perfulandia.msvc.carrocompras.config;


import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.management.openmbean.OpenMBeanParameterInfo;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI costomOpenApi(){
        return new OpenAPI()
                .components(new Components())
                .info(new Info()
                        .title("Api RestFull - MSVC - Carrocompras")
                        .description("Esta es la api dedicada al msvc de carrocompras")
                        .version("v1.0.0"));
    }
}
