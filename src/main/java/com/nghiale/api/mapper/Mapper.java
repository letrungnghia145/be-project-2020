package com.nghiale.api.mapper;

import java.util.ArrayList;
import java.util.List;

public interface Mapper<BO, DTO> {

	public BO convertToBO(DTO dto);

	public DTO convertToDTO(BO bo);

	default List<BO> convertToBOList(List<DTO> dtos) {
		List<BO> bos = new ArrayList<>();
		dtos.forEach(dto -> bos.add(convertToBO(dto)));
		return bos;
	};

	default List<DTO> convertToDTOList(List<BO> bos) {
		List<DTO> dtos = new ArrayList<>();
		bos.forEach(bo -> dtos.add(convertToDTO(bo)));
		return dtos;
	};
}
