package com.example.kotlin.account

import org.apache.commons.lang3.RandomStringUtils
import org.springframework.beans.factory.annotation.Autowire
import org.springframework.beans.factory.annotation.Configurable
import org.springframework.security.crypto.password.PasswordEncoder

import jakarta.persistence.*

@Configurable(autowire = Autowire.BY_TYPE)
@Entity
@Access(AccessType.FIELD)
class Account(@Id val id: String,
              var password: String) {

    @Transient
    lateinit var passwordEncoder: PasswordEncoder

    fun resetPassword() {
        val newPassword = RandomStringUtils.randomAlphanumeric(10)
        this.password = passwordEncoder.encode(newPassword)
    }
}
