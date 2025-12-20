package com.proyectogradle.msprestamos.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration // Marks this class as a source of bean definitions
public class RestTemplateConfig {

    @Bean // Provides a RestTemplate bean for injection
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}

