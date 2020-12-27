package com.nghiale.api.entity;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;

import com.nghiale.api.model.Customer;

public interface CustomerEntity extends UserEntity<Customer> {
	@EntityGraph("Customer.orders")
	@Query("SELECT c FROM Customer c WHERE c.id = ?1")
	public Optional<Customer> findByIdWithOrdersGraph(Long customerID);
	@EntityGraph("Customer.items")
	@Query("SELECT c FROM Customer c WHERE c.id = ?1")
	public Optional<Customer> findByIdWithItemsGraph(Long customerID);
}
