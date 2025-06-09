package com.example.capgeminiProject.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoPastEventException extends RuntimeException {
	public NoPastEventException(String message) {
		super(message);
	}
}