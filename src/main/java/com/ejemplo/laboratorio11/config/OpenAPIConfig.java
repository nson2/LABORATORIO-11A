package com.ejemplo.laboratorio11.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
    info = @Info(
        title = "API de Productos UPN",
        version = "1.0",
        description = "API REST del proyecto de Soluciones Web y Aplicaciones Distribuidas"
    )
)
public class OpenAPIConfig {
}
