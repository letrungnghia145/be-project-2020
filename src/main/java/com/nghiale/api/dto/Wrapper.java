package com.nghiale.api.dto;

import com.fasterxml.jackson.annotation.JsonCreator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Wrapper {
	private Object object;
	private String linkToObject;
	@JsonCreator
	public Wrapper(Object object) {
		super();
		this.object = object;
	}
	
}
