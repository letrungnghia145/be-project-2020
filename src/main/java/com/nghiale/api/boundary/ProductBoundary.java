package com.nghiale.api.boundary;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nghiale.api.control.ProductControl;
import com.nghiale.api.model.Evaluate;
import com.nghiale.api.model.Image;
import com.nghiale.api.model.Product;

@RestController
@RequestMapping("/products")
public class ProductBoundary {
	@Autowired
	private ProductControl control;

	@GetMapping
	public List<Product> getAllProducts() {
		return control.getAllProducts();
	}

	@GetMapping("/{pid}")
	public Product getProduct(@PathVariable("pid") Long productID) {
		return control.getProduct(productID);
	}

	@PostMapping
	@PreAuthorize("hasAuthority('product:create')")
	public void addProduct(@RequestBody Product product) {
		control.addProduct(product);
	}

	@DeleteMapping("/{pid}")
	@PreAuthorize("hasAuthority('product:delete')")
	public void deleteProduct(@PathVariable("pid") Long productID) {
		control.deleteProduct(productID);
	}

	@PutMapping("/{pid}")
	@PreAuthorize("hasAuthority('product:update')")
	public void updateProduct(@PathVariable("pid") Long productID, @RequestBody Product product) {
		product.setId(productID);
		control.updateProduct(product);
	}

	@GetMapping("/{pid}/evaluates")
	public List<Evaluate> getAllEvaluates(@PathVariable("pid") Long productID) {
		return control.getAllEvaluates(productID);
	}

	@PostMapping("/{pid}/evaluates")
	public void addEvaluate(@PathVariable("pid") Long productID, @RequestBody Evaluate evaluate) {
		control.addEvaluate(productID, evaluate);
	}

	@GetMapping("/{pid}/images")
	public List<Image> getAllImages(@PathVariable("pid") Long productID) {
		return control.getAllImages(productID);
	}

	@DeleteMapping("/{pid}/images/{iid}")
	public void deleteImage(@PathVariable("pid") Long productID, @PathVariable("iid") Long imageID) {
		control.deleteImage(productID, imageID);
	}
}
