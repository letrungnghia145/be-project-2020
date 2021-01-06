package com.nghiale.api;

import java.util.Collection;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nghiale.api.control.impl.UserDetailsImpl;
import com.nghiale.api.entity.ProductEntity;
import com.nghiale.api.enums.UserRole;
import com.nghiale.api.model.Role;
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
	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private PasswordEncoder passwordEncoder;

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
//		Role role = new Role(UserRole.ROLE_CUSTOMER);
//		em.persist(role);
//		User user = em.find(User.class, 2L);
//		user.setPassword(passwordEncoder.encode("172285633"));
//		user.addRole(new Role(1L));
//		user.setEmail("pyn_xd_2k@yahoo.com");
//		User user = em.find(User.class, 1L);
//		user.addRole(new Role(3L));
	}
}
