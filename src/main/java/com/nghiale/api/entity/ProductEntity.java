package com.nghiale.api.entity;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nghiale.api.model.Product;

public interface ProductEntity extends JpaRepository<Product, Long> {
	@Query("SELECT p FROM Product p WHERE p.id = ?1")
	@EntityGraph("Product.graph")
	public Optional<Product> findByIdWithImagesGraph(Long id);

	@Query("SELECT p FROM Product p WHERE p.id = ?1")
	@EntityGraph("Product.graph")
	public Optional<Product> findByIdWithEvaluatesGraph(Long id);

	@EntityGraph("Product.graph")
	public Optional<Product> findById(Long id);
	
	@EntityGraph("Product.graph")
	public List<Product> findAll();
}
