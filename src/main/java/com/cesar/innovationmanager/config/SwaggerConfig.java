package com.cesar.innovationmanager.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Innovation Project Manager API")
                        .version("1.0")
                        .description("Documentação da API para o gerenciamento de projetos de inovação")
                        .contact(new Contact()
                                .name("Tary")
                                .email("tary.nascimento@recife.pe.gov.br")
                                .url("https://github.com/TN-Junior/")
                        ));
    }
}
