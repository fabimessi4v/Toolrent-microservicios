package com.proyectogradle.msprestamos.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;

@Configuration // Marks this class as a source of bean definitions
public class RestTemplateConfig {

    @Bean // Provides a RestTemplate bean for injection
    @LoadBalanced// Enables client-side load balancing
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}

