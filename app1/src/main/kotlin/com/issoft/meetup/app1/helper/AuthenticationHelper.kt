package com.issoft.meetup.app1.helper

import com.fasterxml.jackson.databind.ObjectMapper
import com.issoft.meetup.app1.model.UserDetails
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.jwt.JwtHelper

class AuthenticationHelper {

    companion object {
        fun authentication(tokenHeader: String?): Authentication {
            if (tokenHeader == null) {
                return UsernamePasswordAuthenticationToken("default", "default", emptyList())
            }
            val jwt = JwtHelper.decode(tokenHeader.substring(7))
            val user = ObjectMapper().readValue(jwt.claims, UserDetails::class.java)

            return UsernamePasswordAuthenticationToken(user.user_name, "default",
                    user.authorities!!.map { role -> SimpleGrantedAuthority(role) })
        }
    }

}