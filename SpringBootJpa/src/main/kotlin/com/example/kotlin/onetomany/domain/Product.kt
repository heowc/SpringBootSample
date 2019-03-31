package com.example.kotlin.onetomany.domain

import javax.persistence.*

@Entity
@Table(name = "TB_PRODUCT")
data class Product(
        @Id @GeneratedValue @Column(name = "PRODUCT_IDX")
        val idx: Long? = null,
        @Column(name = "NAME")
        var name: String? = null,
        @Column(name = "CONTENT")
        var content: String? = null
)
