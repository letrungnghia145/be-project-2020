package com.nghiale.api.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public abstract class AbstractException extends Exception {
	private static final long serialVersionUID = 8489604681470575906L;
	protected String message;
}
