package com.nghiale.api.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import com.nghiale.api.dto.ProductDTO;
import com.nghiale.api.model.Category;
import com.nghiale.api.model.Image;
import com.nghiale.api.model.Product;

public class ProductMapperUtils {

	public static ProductDTO toDTO(Product product) {
		List<String> imageLinks = new ArrayList<>();
		product.getImages().forEach(image -> imageLinks.add(image.getLink()));
		;
		ProductDTO productDTO = new ProductDTO(product.getId(), product.getName(), product.getPrice(), product.getMfg(),
				product.getExp(), product.getStock(), product.getDescription(), product.getCategory().getId(),
				imageLinks);
		return productDTO;
	}

	public static Product toBO(ProductDTO productDTO) {
		Product product = new Product();
		// normal properties
		product.setDescription(productDTO.getDescription());
		product.setExp(productDTO.getExp());
		product.setMfg(productDTO.getMfg());
		product.setName(productDTO.getName());
		product.setPrice(productDTO.getPrice());
		product.setStock(productDTO.getStock());
		// transfer category
		Category category = new Category();
		category.setId(productDTO.getCategory());
		product.setCategory(category);
		// transfer images
		List<String> imageLinks = productDTO.getImageLinks();
		product.setImages(new HashSet<>());
		imageLinks.forEach(link -> {
			Image image = new Image();
			image.setLink(link);
			image.setDescription("Image of product" + product.getName());
			product.addImage(image);
		});
		return product;
	}
}
