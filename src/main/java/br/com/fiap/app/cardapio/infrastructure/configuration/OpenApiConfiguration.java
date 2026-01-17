package br.com.fiap.app.cardapio.infrastructure.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfiguration {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Registra Cardapio API")
                        .version("v1.0")
                        .description("API para registro e gerenciamento de cardapio do sistema de gestão de restuarantes"));
    }
}
