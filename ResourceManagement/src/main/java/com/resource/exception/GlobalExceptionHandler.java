package com.resource.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.resource.constant.Constant;
import com.resource.dto.ErrorDto;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler {

	@ExceptionHandler(EmployeeNotFoundException.class)
	public ResponseEntity<ErrorDto> userNotFoundException() {
		ErrorDto errorDto = new ErrorDto();
		errorDto.setErrorMessage(Constant.AUTHENTICATION_FAILED);
		errorDto.setErrorStatusCode(HttpStatus.NOT_FOUND.value());
		return ResponseEntity.status(HttpStatus.OK).body(errorDto);
	}

	@ExceptionHandler(value = InvalidAnalyticTypeException.class)
	public ResponseEntity<ErrorDto> handleException(InvalidAnalyticTypeException exception) {
		ErrorDto errorDto = new ErrorDto();
		errorDto.setErrorMessage(exception.getMessage());
		errorDto.setErrorStatusCode(HttpStatus.UNPROCESSABLE_ENTITY.value());
		return ResponseEntity.status(HttpStatus.OK).body(errorDto);
	}
}
