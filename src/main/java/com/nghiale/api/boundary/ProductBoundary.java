package com.nghiale.api.boundary;

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
import com.nghiale.api.dto.ImageDTO;
import com.nghiale.api.dto.ProductDTO;
import com.nghiale.api.mapper.Mapper;
import com.nghiale.api.model.Image;
import com.nghiale.api.model.Product;

@RestController
@RequestMapping("/products")
public class ProductBoundary {
	@Autowired
	private ProductControl control;
	@Autowired
	private Mapper<Product, ProductDTO> productMapper;
	@Autowired
	private Mapper<Image, ImageDTO> productImageMapper;

	@GetMapping
	public ResponseEntity<?> retrieveAllProducts() {
		List<Product> products = control.getAllProducts();
		return ResponseEntity.status(HttpStatus.OK).body(productMapper.convertToDTOList(products));
	}

	@GetMapping("/{pid}")
	public ResponseEntity<?> retrieveProduct(@PathVariable Long pid) {
		Product product = control.getProductDetails(pid);
		return ResponseEntity.status(HttpStatus.OK).body(productMapper.convertToDTO(product));
	}

	@PostMapping
	public ResponseEntity<?> createProduct(@RequestBody ProductDTO dto) {
		Product product = control.addProduct(productMapper.convertToBO(dto));
		return ResponseEntity.status(HttpStatus.CREATED).body(productMapper.convertToDTO(product));
	}

	@PutMapping("/{pid}")
	public ResponseEntity<?> updateProduct(@PathVariable Long pid, @RequestBody ProductDTO dto) {
		dto.setId(pid);
		Product bo = productMapper.convertToBO(dto);
		Product product = control.updateProductDetails(bo);
		return ResponseEntity.status(HttpStatus.OK).body(productMapper.convertToDTO(product));
	}

	@DeleteMapping("/{pid}")
	public ResponseEntity<?> deleteProduct(@PathVariable Long pid) {
		Product product = control.deleteProduct(pid);
		return ResponseEntity.status(HttpStatus.OK).body(productMapper.convertToDTO(product));
	}

	@GetMapping("/{pid}/images")
	public ResponseEntity<?> retrieveAllProductImages(@PathVariable Long pid) {
		List<Image> images = control.getAllProductImages(pid);
		return ResponseEntity.status(HttpStatus.OK).body(productImageMapper.convertToDTOList(images));
	}

//
//	@GetMapping("/{pid}/images/{iid}")
//	public void retrieveProductImage(@PathVariable Long pid, @PathVariable Long iid) {
//		
//	}
	@PostMapping("/{pid}/images")
	public ResponseEntity<?> createProductImage(@PathVariable Long pid, @RequestBody List<ImageDTO> imageDTOs) {
		List<Image> images = productImageMapper.convertToBOList(imageDTOs);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(productImageMapper.convertToDTOList(control.addProductImages(pid, images)));
	}

	@DeleteMapping("/{pid}/images/{iid}")
	public ResponseEntity<?> deleteProductImage(@PathVariable Long pid, @PathVariable Long iid) {
		List<Image> images = control.deleteProductImage(pid, iid);
		return ResponseEntity.status(HttpStatus.OK).body(productImageMapper.convertToDTOList(images));
	}

	@GetMapping("/{pid}/evaluates")
	public void retrieveAllProductEvaluates(@PathVariable Long pid) {
		control.getProductEvaluates(pid);
	}

	@PostMapping("/{pid}/evaluates")
	public void createProductEvaluate(@PathVariable Long pid) {

	}

	@DeleteMapping("/{pid}/evaluates/{eid}")
	public void deleteProductEvaluate(@PathVariable Long pid, @PathVariable Long eid) {

	}
}
