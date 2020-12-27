package com.nghiale.api.mapper;

import com.nghiale.api.dto.UserDTO;
import com.nghiale.api.model.User;

public class UserMapper implements Mapper<User, UserDTO> {

	@Override
	public User fromDTO(UserDTO dto) {
		return null;
	}

	@Override
	public UserDTO fromBO(User bo) {
		// TODO Auto-generated method stub
		return null;
	}
	public static void main(String[] args) {
		UserMapper mapper = new UserMapper();
		mapper.fromDTO(null);
	}
}
