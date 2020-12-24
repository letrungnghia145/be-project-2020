package com.nghiale.api.boundary;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductBoundary {
	@GetMapping
	public void retrieveAllProducts() {

	}

	@GetMapping("/{pid}")
	public void retrieveProduct(@PathVariable Long pid) {

	}

	@PostMapping
	public void createProduct() {

	}

	@PutMapping("/{pid}")
	public void updateProduct(@PathVariable Long pid) {

	}

	@DeleteMapping("/{pid}")
	public void deleteProduct(@PathVariable Long pid) {

	}

	@GetMapping("/{pid}/images")
	public void retrieveAllProductImages(@PathVariable Long pid) {

	}

	@GetMapping("/{pid}/images/{iid}")
	public void retrieveProductImage(@PathVariable Long pid, @PathVariable Long iid) {

	}

	@PostMapping("/{pid}/images")
	public void createProductImage(@PathVariable Long pid) {

	}

	@DeleteMapping("/{pid}/images/{iid}")
	public void deleteProductImage(@PathVariable Long pid, @PathVariable Long iid) {

	}

	@GetMapping("/{pid}/evaluates")
	public void retrieveAllProductEvaluates(@PathVariable Long pid) {

	}

	@PostMapping("/{pid}/evaluates")
	public void createProductEvaluate(@PathVariable Long pid) {

	}

	@DeleteMapping("/{pid}/evaluates/{eid}")
	public void deleteProductEvaluate(@PathVariable Long pid, @PathVariable Long eid) {

	}
}
