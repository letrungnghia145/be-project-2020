package com.nghiale.api;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nghiale.api.entity.ProductEntity;
import com.nghiale.api.model.Customer;
import com.nghiale.api.model.Product;
import com.nghiale.api.model.User;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@EntityScan(basePackages = "com.nghiale.api.model")
@SuppressWarnings({ "unused" })
public class ApiApplication implements CommandLineRunner {
	@Autowired
	private EntityManager em;
	@Autowired
	private ObjectMapper mapper;
	@Autowired
	private ProductEntity entity;

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}

	@Override
	@Transactional
	public void run(String... args) throws Exception {
//		Customer customer = em.find(Customer.class, 1L);
//		customer.addCartItem(em.find(Product.class, 2L), 1L);
//		Customer customer = new Customer("Le Trung Nghia", "0868880758", "Tay Ninh", "nghia1k45@gmail.com", "172285633");
//		em.persist(customer);
	}
}
