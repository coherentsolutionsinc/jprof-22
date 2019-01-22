package com.issoft.meetup.authapp.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.Collections.*

@Service
class UserDetailsServiceImpl @Autowired constructor(
        private val passwordEncoder: PasswordEncoder
) : UserDetailsService {

    override fun loadUserByUsername(login: String): UserDetails {
        if ("login" != login) {
            throw UsernameNotFoundException(login)
        }

        return User(login, passwordEncoder.encode("password"),
                singletonList(SimpleGrantedAuthority("TEST_ROLE")))
    }

}