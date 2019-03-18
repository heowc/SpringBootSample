package com.example.kotlin

import com.example.kotlin.onetomany.domain.Order
import com.example.kotlin.onetomany.domain.Product
import com.example.kotlin.onetomany.repository.OrderRepository
import com.example.kotlin.onetomany.repository.ProductRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.junit4.SpringRunner
import java.util.*
import java.util.function.Consumer

@RunWith(SpringRunner::class)
@DataJpaTest
open class OneToManyTests {

    @Autowired
    lateinit var orderRepository: OrderRepository

    @Autowired
    lateinit var productRepository: ProductRepository

    @Test
    fun test_productFindByName() {
        // given
        insertBaseData()

        // when
        val product = productRepository.findByName(PRODUCT_NAME)

        // then
        assertThat(product.name).isEqualTo(PRODUCT_NAME)
    }

    private fun insertBaseData() {
        val javaProduct = productRepository.save(Product(name = "java", content = "java content"))
        val kotlinProduct = productRepository.save(Product(name = "kotlin", content = "kotlin content"))
        val scalaProduct = productRepository.save(Product(name = "scala", content = "scala content"))

        val javaOrders = Arrays.asList(Order(productCount = 3, product = javaProduct), Order(productCount = 3, product = javaProduct))
        val kotlinOrders = Arrays.asList(Order(productCount = 2, product = kotlinProduct), Order(productCount = 2, product = kotlinProduct))
        val scalaOrders = Arrays.asList(Order(productCount = 1, product = scalaProduct))

        orderRepository.saveAll(javaOrders)
        orderRepository.saveAll(kotlinOrders)
        orderRepository.saveAll(scalaOrders)

        println("========================= Product =========================")
        productRepository.findAll().forEach(Consumer<Product> { println(it) })
        println("========================== Order ==========================")
        orderRepository.findAll().forEach(Consumer<Order> { println(it) })
    }

    companion object {
        const val PRODUCT_NAME = "java"
    }
}
