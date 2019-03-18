package com.example.kotlin.onetoone.domain

import javax.persistence.*

@Entity
@Table(name = "OWNER")
data class Owner(
        @Id @GeneratedValue @Column(name = "OWNER_ID")
        val idx: Long? = null,
        @Column(name = "OWNER_NAME")
        var name: String? = null,
        @OneToOne @JoinColumn(name = "OWNER_ID")
        var market: Market? = null
) {
        constructor() : this(null, null, null)
}
