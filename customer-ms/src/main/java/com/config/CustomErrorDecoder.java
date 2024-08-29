package com.config;

import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class CustomErrorDecoder implements ErrorDecoder {

	@Override
	public Exception decode(String methodKey, Response response) {

		HttpStatus status = HttpStatus.valueOf(response.status());

		switch (status) {
		case NOT_FOUND:
			return new ResponseStatusException(HttpStatus.NOT_FOUND, "location not found");
		case BAD_REQUEST:
			return new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid request");
		default:
			return new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An error occurred");
		}
	}
}
