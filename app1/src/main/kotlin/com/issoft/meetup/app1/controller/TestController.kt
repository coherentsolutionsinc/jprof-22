package com.issoft.meetup.app1.controller

import com.issoft.meetup.app1.model.User
import com.issoft.meetup.app1.service.GRPCClientService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.core.scheduler.Schedulers
import java.security.Principal

@RestController
class TestController @Autowired constructor(
        val webClientBuilder: WebClient.Builder,
        val grpcClientService: GRPCClientService) {

    @RequestMapping("/test1/{id}")
    fun test1(@PathVariable id: String): Mono<String> {
        return Mono.just("app1 " + id + " " + System.currentTimeMillis())
    }

    /**
     * first option to read user data
     * to inject principal
     * refer to SecurityContextRepository
     */
    @RequestMapping("/test2")
    fun test2(principal: Mono<Principal>): Mono<String> {
        return principal.map { t -> String.format("Hello %s", t.name) }
    }

    /**
     * second option
     * to set security context
     * refer to AuthFilter
     */
    @RequestMapping("/test3")
    fun test3(): Mono<String> {
        return Mono.just(SecurityContextHolder.getContext().authentication.name)
    }

    @RequestMapping("/test4")
    fun test4(): Flux<User> {
        return webClientBuilder.build().get().uri("http://localhost:8092/user/get").retrieve().bodyToFlux(User::class.java)
    }

    @GetMapping("/test5", produces = [MediaType.TEXT_EVENT_STREAM_VALUE])
    fun test5(): Flux<User> {
        return webClientBuilder.build().get().uri("http://localhost:8092/user/stream").retrieve().bodyToFlux(User::class.java)
    }

    @GetMapping("/test6")
    fun test6(): Mono<String> {
        return webClientBuilder.build().get().uri("https://www.tut.by/").retrieve().bodyToMono(String::class.java)
    }

    @GetMapping("/test8")
    fun test8(@RequestParam value: String): Mono<String> {
        return grpcClientService.echo(value)
    }

    @GetMapping("/speed1")
    fun speed1(@RequestParam value: Int): Mono<Unit> {
        return Mono.fromCallable {
            val start = System.currentTimeMillis();

            Flux.range(0, value).map { it.toString() }.map { grpcClientService.echo(it).subscribe() }.collectList().block()

            println("speed1 " + (System.currentTimeMillis() - start))
        }.publishOn(Schedulers.elastic())
    }

    @GetMapping("/speed3")
    fun speed3(@RequestParam value: Int): Mono<Unit> {
        return Mono.fromCallable {
            val start = System.currentTimeMillis();

            Flux.range(0, value)
                    .map { it.toString() }
                    .map { webClientBuilder.build().get().uri("http://localhost:8092/echo?value=$it").retrieve().bodyToMono(String::class.java).subscribe() }
                    .collectList().block()

            println("speed3 " + (System.currentTimeMillis() - start))
        }.publishOn(Schedulers.elastic())
    }

    @GetMapping("/sp1")
    fun sp1(): Mono<String> {
        return grpcClientService.echo(System.currentTimeMillis().toString())
    }

    @GetMapping("/sp3")
    fun sp3(): Mono<String> {
        val a = System.currentTimeMillis().toString();
        return webClientBuilder.build().get().uri("http://localhost:8092/echo?value=$a").retrieve().bodyToMono(String::class.java)
    }
}