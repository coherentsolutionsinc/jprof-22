package com.issoft.meetup.app2.config

import com.issoft.meetup.app2.entity.User
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.core.ReactiveRedisTemplate
import org.springframework.data.redis.serializer.RedisSerializationContext
import org.springframework.data.redis.serializer.StringRedisSerializer
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory
import org.springframework.data.redis.core.ReactiveRedisOperations


@Configuration
class UserConfiguration {

    @Bean
    fun redisOperations(factory: ReactiveRedisConnectionFactory): ReactiveRedisOperations<String, User> {
        val serializer = Jackson2JsonRedisSerializer(User::class.java)

        val builder = RedisSerializationContext.newSerializationContext<String, User>(StringRedisSerializer())

        val context = builder.value(serializer).build()

        return ReactiveRedisTemplate(factory, context)
    }
}