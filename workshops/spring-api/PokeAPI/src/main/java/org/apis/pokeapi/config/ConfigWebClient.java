package org.apis.pokeapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ConfigWebClient {

    /**
     * Configure and provide a WebClient preconfigured with the PokeAPI base URL.
     *
     * @return a WebClient instance whose base URL is "https://pokeapi.co/api/v2"
     */
    @Bean
    public WebClient webClient() {
        return WebClient.builder()
                .baseUrl("https://pokeapi.co/api/v2")
                .build();
    }
}