package com.mongodb.workshopmongo.resources.exception;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.mongodb.workshopmongo.services.exception.ObjectNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {
	
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandarError> objectNotFound(ObjectNotFoundException obj, HttpServletRequest e){
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandarError std = new StandarError(Instant.now(), status.value() , "Não encontrado!", obj.getMessage(), e.getRequestURI());
      return ResponseEntity.status(status.value()).body(std);
	}
}
