package com.nghiale.api.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class UserDTO {
	private Long id;
	private String name;
	private String phone;
	private String address;
	private String email;
}
