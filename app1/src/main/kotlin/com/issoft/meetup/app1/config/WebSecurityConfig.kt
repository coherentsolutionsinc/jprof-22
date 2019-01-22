package com.issoft.meetup.app1.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.ReactiveAuthenticationManager
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.web.server.SecurityWebFilterChain
import org.springframework.security.web.server.context.ServerSecurityContextRepository
import reactor.core.publisher.Mono

@Configuration
@EnableWebFluxSecurity
class WebSecurityConfig {

    @Autowired
    private val securityContextRepository: ServerSecurityContextRepository? = null

    @Bean
    fun springSecurityFilterChain(http: ServerHttpSecurity): SecurityWebFilterChain {
        // Disable default security.
        http.httpBasic().disable()
        http.formLogin().disable()
        http.csrf().disable()
        http.logout().disable()

        // Add custom security.
        http.securityContextRepository(securityContextRepository)

        http.authorizeExchange().anyExchange().authenticated()

        return http.build()
    }

    @Bean
    fun authenticationManager(): ReactiveAuthenticationManager {
        return ReactiveAuthenticationManager { authentication -> Mono.just(authentication) }
    }

}