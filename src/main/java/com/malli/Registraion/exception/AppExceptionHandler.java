package com.malli.Registraion.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AppExceptionHandler {

	@ExceptionHandler(value=Exception.class)
	public ResponseEntity<AppError> handleException(Exception e) {
		AppError appError = new AppError();
		appError.setErrorCode("001");
		//appError.setErrorMsg(e.toString());
		appError.setErrorMsg(e.getLocalizedMessage());
		//appError.setErrorMsg(e.getMessage());
		ResponseEntity<AppError> entity = new ResponseEntity<AppError>(appError, HttpStatus.INTERNAL_SERVER_ERROR);
		return entity;
	}

}
