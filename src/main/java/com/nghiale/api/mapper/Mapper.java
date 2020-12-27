package com.nghiale.api.mapper;

public interface Mapper<BO, DTO> {

	public BO convertToBO(DTO dto);

	public DTO convertToDTO(BO bo);
}
