package com.issoft.meetup.app2.service

import org.springframework.stereotype.Component
import reactor.core.publisher.Flux

@Component
class EchoService {

    fun echo(input: Flux<String>): Flux<String> {
        return input.map { echo(it) }
    }

    fun echo(input: String): String {
        return "Echo: $input"
    }
}