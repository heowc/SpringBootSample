package com.example;

import com.example.domain.Order;
import com.example.domain.OrderRepository;
import com.example.domain.Product;
import com.example.domain.ProductRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OneToManyTests {

    @Autowired OrderRepository orderRepository;
    @Autowired ProductRepository productRepository;

    @Before
    public void before_init() {
        productRepository.save(new Product("java", "java content"));
        productRepository.save(new Product("kotlin", "kotlin content"));
        productRepository.save(new Product("scala", "scala content"));

        orderRepository.save(new Order(1L, 3, ""));
        orderRepository.save(new Order(2L, 2, ""));
        orderRepository.save(new Order(3L, 1, ""));
        orderRepository.save(new Order(1L, 3, ""));
        orderRepository.save(new Order(2L, 2, ""));
    }

    @Transactional
    @Test
    public void test_productSelect() {
        productRepository.findAll().forEach(System.out::println);
    }

    @Transactional
    @Test
    public void test_orderSelect() {
        orderRepository.findAll().forEach(System.out::println);
    }
}
