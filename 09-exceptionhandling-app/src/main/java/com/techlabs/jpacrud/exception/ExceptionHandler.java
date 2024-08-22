package com.techlabs.jpacrud.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.techlabs.jpacrud.error.StudentErrorResponse;

@ControllerAdvice
public class ExceptionHandler {

	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<StudentErrorResponse> handleStudentNotFoundException(StudentNotFoundException exception)
	{
		StudentErrorResponse error = new StudentErrorResponse();
		error.setStatusCode(HttpStatus.NOT_FOUND.value());
		error.setErrorMessage(exception.getMessage());
		error.setTimeStamp(System.currentTimeMillis());
		
		return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<StudentErrorResponse> handleStudentException(MethodArgumentTypeMismatchException exception)
	{
		StudentErrorResponse error = new StudentErrorResponse();
		error.setStatusCode(HttpStatus.BAD_REQUEST.value());
		error.setErrorMessage("Roll number has to be integer");
		error.setTimeStamp(System.currentTimeMillis());
		
		return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
	}
}
