package com.nghiale.api.mapper;

public interface Mapper<BO, DTO> {
	public BO fromDTO(DTO dto);

	public DTO fromBO(BO bo);
}
