package com.nghiale.api.boundary;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categories")
public class CategoryBoundary {
	@GetMapping
	public void retrieveAllCategories() {

	}

	@PostMapping
	public void createCategory() {

	}

	@PutMapping("/{cid}")
	public void updateCategory(@PathVariable Long cid) {

	}

	@DeleteMapping("/{cid}")
	public void deleteCategory(@PathVariable Long cid) {

	}

	@GetMapping("/{cid}/products")
	public void getAllCategoryProducts(@PathVariable Long cid) {

	}
}
