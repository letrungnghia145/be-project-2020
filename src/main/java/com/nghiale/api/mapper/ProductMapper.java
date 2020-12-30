package com.nghiale.api.mapper;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nghiale.api.dto.ImageDTO;
import com.nghiale.api.dto.ProductDTO;
import com.nghiale.api.model.Image;
import com.nghiale.api.model.Product;
import com.nghiale.api.utils.Converter;

@Service
public class ProductMapper implements Mapper<Product, ProductDTO> {
	@Autowired
	private Mapper<Image, ImageDTO> productImageMapper;

	@Override
	public Product convertToBO(ProductDTO dto) {
		Product bo = new Product();
		Converter.convert(dto, bo);
		// Transfer category from DTO to BO
		bo.setCategory(dto.getCategory());
		// Transfer images from DTO to BO
		bo.setImages(dto.getImages());
		return bo;
	}

	@Override
	public ProductDTO convertToDTO(Product bo) {
		ProductDTO dto = new ProductDTO();
		Converter.convert(bo, dto);
		// Transfer category from BO to DTO
		dto.setCategory(bo.getCategory().getId());
		// Transfer images from BO to DTO
		List<ImageDTO> images = productImageMapper.convertToDTOList(List.copyOf(bo.getImages()));
		dto.setImages(images);
		return dto;
	}

}
