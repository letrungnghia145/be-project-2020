package com.nghiale.api.mapper;

import java.lang.reflect.Constructor;

import org.springframework.stereotype.Service;

import com.nghiale.api.dto.UserDTO;
import com.nghiale.api.model.User;
import com.nghiale.api.utils.Converter;

import lombok.Setter;

@Service
public class UserMapper implements Mapper<User, UserDTO> {
	@Setter
	private Class<? extends User> UserClassType;

	@Override
	public User convertToBO(UserDTO dto) {
		Object to = null;
		try {
			Constructor<?> constructor = UserClassType.getConstructor();
			to = constructor.newInstance();
			Converter.convert(dto, to);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return (User) to;
	}

	@Override
	public UserDTO convertToDTO(User bo) {
		UserDTO dto = new UserDTO();
		Converter.convert(bo, dto);
		return dto;
	}

}
