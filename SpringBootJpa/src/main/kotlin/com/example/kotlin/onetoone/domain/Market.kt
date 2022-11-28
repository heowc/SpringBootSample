package com.example.kotlin.onetoone.domain

import jakarta.persistence.*

@Entity
@Table(name = "MARKET")
data class Market(
        @Id @GeneratedValue @Column(name = "MARKET_ID")
        val idx: Long? = null,
        @Column(name = "MARKET_NAME")
        var name: String? = null,
        @Column(name = "MARKET_LOCATION")
        var location: String? = null,
        @OneToOne @JoinColumn(name = "MARKET_ID")
        var owner: Owner? = null) {

    override fun toString(): String {
        return "Market(idx=$idx, name=$name, location=$location, owner=${owner?.name})"
    }
}
