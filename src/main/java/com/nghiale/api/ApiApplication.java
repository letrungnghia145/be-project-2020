package com.nghiale.api;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@EntityScan(basePackages = "com.nghiale.api.model")
@Transactional
public class ApiApplication implements CommandLineRunner {
	@Autowired
	private EntityManager em;

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		Customer customer = em
//				.createQuery(QueryUtil.findSingle(Customer.class, paramName).createQuery(), Customer.class)
//				.setParameter(paramName, 1L).getSingleResult();
//		System.out.println(customer);
//		Customer customer = repository.retrieve(3L);
//		System.out.println(customer.getItems());
//		System.out.println(customer.getOrders());
//		System.out.println(customer.getRoles());
//		System.out.println(customer.getWishlist());
	}
}
