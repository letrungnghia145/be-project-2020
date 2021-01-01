package com.nghiale.api.boundary;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.nghiale.api.control.ProductControl;
import com.nghiale.api.dto.EvaluateDTO;
import com.nghiale.api.dto.ImageDTO;
import com.nghiale.api.dto.ProductDTO;
import com.nghiale.api.mapper.Mapper;
import com.nghiale.api.model.Evaluate;
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
	@Autowired
	private Mapper<Evaluate, EvaluateDTO> evaluateMapper;

	@GetMapping
	public List<ProductDTO> retrieveAllProducts() {
		List<Product> products = control.getAllProducts();
		return productMapper.convertToDTOList(products);
	}

	@GetMapping("/{pid}")
	public ProductDTO retrieveProduct(@PathVariable Long pid) {
		Product product = control.getProductDetails(pid);
		return productMapper.convertToDTO(product);
	}

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public ProductDTO createProduct(@RequestBody ProductDTO dto) {
		Product product = control.addProduct(productMapper.convertToBO(dto));
		return productMapper.convertToDTO(product);
	}

	@PutMapping("/{pid}")
	public ProductDTO updateProduct(@PathVariable Long pid, @RequestBody ProductDTO dto) {
		dto.setId(pid);
		Product bo = productMapper.convertToBO(dto);
		Product product = control.updateProductDetails(bo);
		return productMapper.convertToDTO(product);
	}

	@DeleteMapping("/{pid}")
	public ProductDTO deleteProduct(@PathVariable Long pid) {
		Product product = control.deleteProduct(pid);
		return productMapper.convertToDTO(product);
	}

	@GetMapping("/{pid}/images")
	public List<ImageDTO> retrieveAllProductImages(@PathVariable Long pid) {
		List<Image> images = control.getAllProductImages(pid);
		return productImageMapper.convertToDTOList(images);
	}

//
//	@GetMapping("/{pid}/images/{iid}")
//	public void retrieveProductImage(@PathVariable Long pid, @PathVariable Long iid) {
//		
//	}
	@PostMapping("/{pid}/images")
	public List<ImageDTO> createProductImage(@PathVariable Long pid, @RequestBody List<ImageDTO> imageDTOs) {
		List<Image> images = productImageMapper.convertToBOList(imageDTOs);
		return productImageMapper.convertToDTOList(control.addProductImages(pid, images));
	}

	@DeleteMapping("/{pid}/images/{iid}")
	public List<ImageDTO> deleteProductImage(@PathVariable Long pid, @PathVariable Long iid) {
		List<Image> images = control.deleteProductImage(pid, iid);
		return productImageMapper.convertToDTOList(images);
	}

	@GetMapping("/{pid}/evaluates")
	public List<EvaluateDTO> retrieveAllProductEvaluates(@PathVariable Long pid) {
		List<Evaluate> evaluates = control.getAllProductEvaluates(pid);
		return evaluateMapper.convertToDTOList(evaluates);
	}

	@PostMapping("/{pid}/evaluates")
	public List<EvaluateDTO> createProductEvaluate(@PathVariable Long pid, @RequestBody EvaluateDTO evaluateDTO) {
		Evaluate convertToBO = evaluateMapper.convertToBO(evaluateDTO);
		List<Evaluate> evaluates = control.addProductEvaluate(pid, convertToBO);
		return evaluateMapper.convertToDTOList(evaluates);
	}

	@DeleteMapping("/{pid}/evaluates/{eid}")
	@Transactional
	public List<EvaluateDTO> deleteProductEvaluate(@PathVariable Long pid, @PathVariable Long eid) {
		List<EvaluateDTO> productDTOs = evaluateMapper.convertToDTOList(control.deleteProductEvaluate(pid, eid));
		return productDTOs;
	}
}
