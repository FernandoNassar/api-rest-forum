package com.demo.project.forum.api.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;


public interface ExceptionUtil {
	
	static Error buildError(Integer statusCode, RuntimeException e, HttpServletRequest request) {
	
		Error error = new Error();
		
		error.setTimeStamp(Instant.now());
		error.setStatus(statusCode);
		error.setError("not found");
		error.setMessage(e.getLocalizedMessage());
		error.setPath(request.getRequestURI());
		return error;
	}

	
}
