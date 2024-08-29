package com.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class CustomException extends Exception {

	public CustomException(String message) {
		super(message);

	}

	public static class RoomsNotFoundException extends ResponseStatusException {
		public RoomsNotFoundException(String message) {
			super(HttpStatus.NOT_FOUND, message);
		}
	}

}
