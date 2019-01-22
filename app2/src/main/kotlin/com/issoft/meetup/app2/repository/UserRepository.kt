package com.issoft.meetup.app2.repository

import com.issoft.meetup.app2.entity.User
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Flux
import org.springframework.data.mongodb.repository.Tailable

interface UserRepository : ReactiveCrudRepository<User, String> {

    @Tailable // Use a tailable cursor
    fun findWithTailableCursorBy(): Flux<User>
}