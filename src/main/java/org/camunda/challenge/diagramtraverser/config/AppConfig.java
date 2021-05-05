package org.camunda.challenge.diagramtraverser.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@AllArgsConstructor
class AppConfig {

    @Bean
    WebClient webClient() {
        return WebClient.builder().build();
    }
}
