package com.nghiale.api.mapper;

import org.springframework.stereotype.Service;

import com.nghiale.api.dto.ImageDTO;
import com.nghiale.api.model.Image;

@Service
public class ProductImageMapper implements Mapper<Image, ImageDTO> {

	@Override
	public Image convertToBO(ImageDTO dto) {
		return new Image(dto.getLink(), dto.getDescription());
	}

	@Override
	public ImageDTO convertToDTO(Image bo) {
		return new ImageDTO(bo.getLink(), bo.getDescription());
	}

}
