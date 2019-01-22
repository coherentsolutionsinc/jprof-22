package com.issoft.meetup.authapp

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer

@SpringBootApplication
@EnableAuthorizationServer
class AuthAppApplication

fun main(args: Array<String>) {
    runApplication<AuthAppApplication>(*args)
}