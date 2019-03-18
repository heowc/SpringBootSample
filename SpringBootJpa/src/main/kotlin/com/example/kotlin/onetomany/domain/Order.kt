package com.example.kotlin.onetomany.domain

import javax.persistence.*

@Entity
@Table(name = "TB_ORDER")
data class Order(
        @Id @GeneratedValue @Column(name = "ORDER_IDX")
        var idx: Long? = null,
        @Column(name = "PRODUCT_COUNT")
        var productCount: Int? = null,
        @Column(name = "BIGO")
        var bigo: String? = null,
        @ManyToOne @JoinColumn(name = "PRODUCT_IDX")
        var product: Product? = null
) {

    constructor() : this(null, null, null, null)

    override fun toString(): String {
        return "Order{" +
                "idx=" + idx +
                ", productCount=" + productCount +
                ", bigo='" + bigo + '\''.toString() +
                ", product=" + product!!.name +
                '}'.toString()
    }
}
