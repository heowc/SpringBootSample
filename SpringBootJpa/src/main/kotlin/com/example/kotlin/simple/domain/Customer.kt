package com.example.kotlin.simple.domain

import jakarta.persistence.*

@Entity
@NamedQuery(name = "Custom.findByName", query = "SELECT c FROM Customer c WHERE c.name = :name ")
data class Customer(
        @Id @GeneratedValue val idx: Long? = null,
        @Column(length = 50) val name: String,
        @Column(length = 14) val tel: String,
        var bigo: String) {

    fun changeBigo(bigo: String) {
        this.bigo = bigo
    }
}
