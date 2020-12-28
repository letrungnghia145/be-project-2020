package com.nghiale.api.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.nghiale.api.dto.ImageDTO;
import com.nghiale.api.dto.ProductDTO;
import com.nghiale.api.model.Product;
import com.nghiale.api.utils.ConvertUtils;

@Service
public class ProductMapper implements Mapper<Product, ProductDTO> {

	@Override
	public Product convertToBO(ProductDTO dto) {
		Product bo = new Product();
		ConvertUtils.convert(dto, bo);
		// Transfer category from DTO to BO
		bo.setCategory(dto.getCategory());
		// Transfer images from DTO to BO
		bo.setImages(dto.getImages());
		return bo;
	}

	@Override
	public ProductDTO convertToDTO(Product bo) {
		ProductDTO dto = new ProductDTO();
		ConvertUtils.convert(bo, dto);
		// Transfer category from BO to DTO
		dto.setCategory(bo.getCategory().getId());
		// Transfer images from BO to DTO
		List<ImageDTO> images = new ArrayList<>();
		bo.getImages().forEach(image -> images.add(new ImageDTO(image.getDescription(), image.getLink())));
		dto.setImages(images);
		return dto;
	}

}
