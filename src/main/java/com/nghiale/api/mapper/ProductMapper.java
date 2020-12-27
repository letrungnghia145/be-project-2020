package com.nghiale.api.mapper;

import java.util.HashSet;
import java.util.List;

import org.springframework.stereotype.Service;

import com.nghiale.api.dto.ProductDTO;
import com.nghiale.api.model.Category;
import com.nghiale.api.model.Image;
import com.nghiale.api.model.Product;

@Service
public class ProductMapper implements Mapper<Product, ProductDTO> {

	@Override
	public Product fromDTO(ProductDTO dto) {
		Product product = new Product();
		map(product, dto);
		// transfer to product category
		Category category = new Category();
		category.setId(dto.getCategory());
		product.setCategory(category);
		// transfer to product images
		List<String> imageLinks = dto.getImageLinks();
		HashSet<Image> images = new HashSet<>();
		imageLinks.forEach(link -> {
			Image image = new Image();
			image.setLink(link);
			image.setDescription("Image of product" + product.getName());
			image.setProduct(product);
			images.add(image);
		});
		product.setImages(images);
		return product;
	}

	@Override
	public ProductDTO fromBO(Product bo) {
		// TODO Auto-generated method stub
		return null;
	}

}
