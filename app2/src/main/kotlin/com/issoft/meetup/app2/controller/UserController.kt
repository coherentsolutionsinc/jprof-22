package com.issoft.meetup.app2.controller

import com.issoft.meetup.app2.entity.User
import com.issoft.meetup.app2.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory
import org.springframework.data.redis.core.ReactiveRedisOperations
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono
import reactor.core.publisher.Flux
import java.util.*

@RestController
@RequestMapping("/user")
class UserController
@Autowired constructor(val userRepository: UserRepository,
                       val factory: ReactiveRedisConnectionFactory,
                       val userOps: ReactiveRedisOperations<String, User>) {

    @GetMapping("put")
    fun put(@RequestParam("name") name: String): Mono<Boolean> {
        return factory.reactiveConnection.serverCommands().flushAll().then(
                Mono.just(name).map { value -> User(UUID.randomUUID().toString(), value) }
                        .flatMap { user -> userOps.opsForValue().set(user.id!!, user) })
    }

    @GetMapping("get")
    fun get(): Flux<User> {
        return userOps.keys("*").flatMap { key -> userOps.opsForValue().get(key) }
    }

    @PostMapping("add")
    fun create(@RequestBody user: User): Mono<User> {
        return userRepository.save(user)
    }

    @GetMapping("all")
    fun all(): Flux<User> {
        return userRepository.findAll()
    }

    @GetMapping("stream", produces = [MediaType.TEXT_EVENT_STREAM_VALUE])
    fun stream(): Flux<User> {
        return userRepository.findWithTailableCursorBy()
    }

}