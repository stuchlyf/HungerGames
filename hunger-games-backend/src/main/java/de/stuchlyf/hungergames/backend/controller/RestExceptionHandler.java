package de.stuchlyf.hungergames.backend.controller;

import de.stuchlyf.hungergames.backend.exception.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(value = {EntityNotFoundException.class})
	protected ResponseEntity<Void> handleEntityNotFoundException(EntityNotFoundException ex, WebRequest request) {
		return ResponseEntity.notFound().build();
	}
}
