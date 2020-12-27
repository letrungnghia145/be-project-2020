package com.nghiale.api.boundary;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nghiale.api.control.ProductControl;
import com.nghiale.api.dto.ProductDTO;
import com.nghiale.api.mapper.Mapper;
import com.nghiale.api.model.Product;

@RestController
@RequestMapping("/products")
public class ProductBoundary {
	@Autowired
	private ProductControl control;
	@Autowired
	private Mapper<Product, ProductDTO> mapper;

	@GetMapping
	public ResponseEntity<?> retrieveAllProducts() {
		List<ProductDTO> dtos = new ArrayList<>();
		control.getAllProducts().forEach(product -> dtos.add(mapper.convertToDTO(product)));
		return ResponseEntity.status(HttpStatus.OK).body(dtos);
	}

	@GetMapping("/{pid}")
	public ResponseEntity<?> retrieveProduct(@PathVariable Long pid) {
		Product product = control.getProductDetails(pid);
		return ResponseEntity.status(HttpStatus.OK).body(mapper.convertToDTO(product));
	}

	@PostMapping
	public ResponseEntity<?> createProduct(@RequestBody ProductDTO dto) {
		Product product = control.addProduct(mapper.convertToBO(dto));
		dto.setId(product.getId());
		return ResponseEntity.status(HttpStatus.CREATED).body(dto);
	}

	@PutMapping("/{pid}")
	public ResponseEntity<?> updateProduct(@PathVariable Long pid, @RequestBody ProductDTO dto) {
		dto.setId(pid);
		Product bo = mapper.convertToBO(dto);
		Product product = control.updateProductDetails(bo);
		return ResponseEntity.status(HttpStatus.OK).body(mapper.convertToDTO(product));
	}

	@DeleteMapping("/{pid}")
	public ResponseEntity<?> deleteProduct(@PathVariable Long pid) {
		Product product = control.deleteProduct(pid);
		return ResponseEntity.status(HttpStatus.OK).body(mapper.convertToDTO(product));
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
