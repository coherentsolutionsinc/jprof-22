package com.issoft.meetup.app1.config

import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.context.annotation.Bean


@Configuration
class Configuration {

    @Bean
    fun loadBalancedWebClientBuilder(): WebClient.Builder {
        return WebClient.builder()
    }
}