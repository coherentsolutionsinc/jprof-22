package com.issoft.meetup.app1.filter

import com.issoft.meetup.app1.helper.AuthenticationHelper
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import org.springframework.web.server.WebFilter
import org.springframework.web.server.WebFilterChain
import reactor.core.publisher.Mono

/**
 * second option to load user data from token into security context
 */
@Component
class AuthFilter : WebFilter {

    override fun filter(exchange: ServerWebExchange, chain: WebFilterChain): Mono<Void> {
        val tokenHeader = exchange.request.headers.getFirst("Authorization")
        SecurityContextHolder.getContext().authentication = AuthenticationHelper.authentication(tokenHeader)
        return chain.filter(exchange)
    }
}