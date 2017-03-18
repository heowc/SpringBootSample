package com.example;

import com.example.onetomany.domain.Order;
import com.example.onetomany.repository.OrderRepository;
import com.example.onetomany.domain.Product;
import com.example.onetomany.repository.ProductRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional
public class OneToManyTests {

    @Autowired TestEntityManager testEntityManager;
    @Autowired OrderRepository orderRepository;
    @Autowired ProductRepository productRepository;

    @Before
    public void before_init() {
        Product javaProduct = new Product("java", "java content");
        Product kotlinProduct = new Product("kotlin", "kotlin content");
        Product scalaProduct = new Product("scala", "scala content");

        productRepository.save(javaProduct);
        productRepository.save(kotlinProduct);
        productRepository.save(scalaProduct);

        Order order1 = orderRepository.save(new Order(3, ""));
        Order order2 = orderRepository.save(new Order(2, ""));
        Order order3 = orderRepository.save(new Order(1, ""));
        Order order4 = orderRepository.save(new Order(3, ""));
        Order order5 = orderRepository.save(new Order(2, ""));

        javaProduct.getOrders().add(order1);
        kotlinProduct.getOrders().add(order2);
        scalaProduct.getOrders().add(order3);
        javaProduct.getOrders().add(order4);
        kotlinProduct.getOrders().add(order5);

        testEntityManager.flush();
        testEntityManager.clear();
    }

    @Test
    public void test_productFindOne() {
        System.out.println(productRepository.findOne(1L));
    }

    @Test
    public void test_orderFindOne() {
        System.out.println(orderRepository.findOne(1L));
    }
}
