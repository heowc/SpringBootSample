package com.example.kotlin;

import com.example.onetomany.domain.Order;
import com.example.onetomany.domain.Product;
import com.example.onetomany.repository.OrderRepository;
import com.example.onetomany.repository.ProductRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class OneToManyTests {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private ProductRepository productRepository;

	private static final String PRODUCT_NAME = "java";

	@Test
	public void test_productFindByName() {
		// given
		insertBaseData();

		// when
		Product product = productRepository.findByName(PRODUCT_NAME);

		// then
		assertThat(product.getName()).isEqualTo(PRODUCT_NAME);
	}

	private void insertBaseData() {
		Product javaProduct = new Product("java", "java content");
		Product kotlinProduct = new Product("kotlin", "kotlin content");
		Product scalaProduct = new Product("scala", "scala content");

		productRepository.save(javaProduct);
		productRepository.save(kotlinProduct);
		productRepository.save(scalaProduct);

		javaProduct = productRepository.findByName(javaProduct.getName());
		kotlinProduct = productRepository.findByName(kotlinProduct.getName());
		scalaProduct = productRepository.findByName(scalaProduct.getName());

		List<Order> javaOrders = Arrays.asList(new Order(3, "", javaProduct), new Order(3, "", javaProduct));
		List<Order> kotlinOrders = Arrays.asList(new Order(2, "", kotlinProduct), new Order(2, "", kotlinProduct));
		List<Order> scalaOrders = Arrays.asList(new Order(1, "", scalaProduct));

		orderRepository.saveAll(javaOrders);
		orderRepository.saveAll(kotlinOrders);
		orderRepository.saveAll(scalaOrders);

		System.out.println("========================= Product =========================");
		productRepository.findAll().forEach(System.out::println);
		System.out.println("========================== Order ==========================");
		orderRepository.findAll().forEach(System.out::println);
	}
}
