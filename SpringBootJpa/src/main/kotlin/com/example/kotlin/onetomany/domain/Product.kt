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
) {

    constructor() : this(null, null, null)

    override fun toString(): String {
        return "Product{" +
                "idx=" + idx +
                ", name='" + name + '\''.toString() +
                ", content='" + content + '\''.toString() +
                '}'.toString()
    }
}
