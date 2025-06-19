package com.common.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {
// Products http://localhost:8080/swagger-ui/index.html#
// Orders http://localhost:8082/swagger-ui/index.html#/
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().info(
                new Info()
                        .title("BackendTP2 API")
                        .version("1.0")
                        .description("Documentación unificada para Products y Orders")
        );
    }
}