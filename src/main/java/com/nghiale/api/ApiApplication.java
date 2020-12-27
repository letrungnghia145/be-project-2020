package com.nghiale.api;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.nghiale.api.control.ProductControl;
import com.nghiale.api.control.UserControl;
import com.nghiale.api.entity.ProductEntity;
import com.nghiale.api.entity.UserEntity;
import com.nghiale.api.model.User;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@EntityScan(basePackages = "com.nghiale.api.model")
@SuppressWarnings({ "unused" })
public class ApiApplication implements CommandLineRunner {
	@Autowired
	private ProductEntity productEntity;
	@Autowired
	private ProductControl control;
	@Autowired
	private UserEntity<User> userEntity;
	@Autowired
	private UserControl userControl;
	@Autowired
	private EntityManager em;

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}

	@Override
	@Transactional
	public void run(String... args) throws Exception {
	}
}
