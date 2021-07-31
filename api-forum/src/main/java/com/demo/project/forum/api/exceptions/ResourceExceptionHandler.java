package com.demo.project.forum.api.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.auth0.jwt.exceptions.TokenExpiredException;

@RestControllerAdvice
public class ResourceExceptionHandler {
	
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<Error> objectNotFoundExceptionHandler(ObjectNotFoundException e, HttpServletRequest request) {
		
		Error error = ExceptionUtil.buildError(HttpStatus.NOT_FOUND.value(), e, request);
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
	@ExceptionHandler(TokenExpiredException.class)
	public ResponseEntity<Error> tokenExpiredExceptionHandler(TokenExpiredException e, HttpServletRequest request) {
		
		Error error = ExceptionUtil.buildError(HttpStatus.FORBIDDEN.value(), e, request);
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error);
		
	}
	
}
