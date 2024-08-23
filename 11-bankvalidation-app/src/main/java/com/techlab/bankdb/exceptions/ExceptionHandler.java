package com.techlab.bankdb.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.techlab.bankdb.error.ErrorRespone;




@ControllerAdvice
public class ExceptionHandler {
	
	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<ErrorRespone> handleCustomerNotFoundException(CustomerNotFoundException exception){
		
		
		ErrorRespone errorResponse =new ErrorRespone();
		errorResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
		errorResponse.setErrorMessage(exception.getMessage());
		errorResponse.setTimeStamp(System.currentTimeMillis());
		
		return new ResponseEntity<ErrorRespone>(errorResponse,HttpStatus.NOT_FOUND);
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<ErrorRespone> handleLoanNotFoundException(LoanNotFoundException exception){
		
		
		ErrorRespone errorResponse =new ErrorRespone();
		errorResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
		errorResponse.setErrorMessage(exception.getMessage());
		errorResponse.setTimeStamp(System.currentTimeMillis());
		
		return new ResponseEntity<ErrorRespone>(errorResponse,HttpStatus.NOT_FOUND);
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<ErrorRespone> handlePaymentNotFoundException(PaymentNotFoundException exception){
		
		
		ErrorRespone errorResponse =new ErrorRespone();
		errorResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
		errorResponse.setErrorMessage(exception.getMessage());
		errorResponse.setTimeStamp(System.currentTimeMillis());
		
		return new ResponseEntity<ErrorRespone>(errorResponse,HttpStatus.NOT_FOUND);
	}
	
	
	
	@org.springframework.web.bind.annotation.ExceptionHandler
	    public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException ex) {
	        Map<String, String> errors = new HashMap<>();
	        ex.getBindingResult().getFieldErrors().forEach((error) -> {
	           
	            errors.put(error.getField(), error.getDefaultMessage());
	        });
	        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	    }
	

	@org.springframework.web.bind.annotation.ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<String> handleInvalidDateFormat(HttpMessageNotReadableException ex) {
	    return new ResponseEntity<>("Invalid Input(from not redable)", HttpStatus.BAD_REQUEST);
	}
	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<ErrorRespone> handleStudentNotFoundException(MethodArgumentTypeMismatchException exception){
		
		
		ErrorRespone errorResponse =new ErrorRespone();
		errorResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
		errorResponse.setErrorMessage("In valid Input(From type mismatch)");
		errorResponse.setTimeStamp(System.currentTimeMillis());
		
		return new ResponseEntity<ErrorRespone>(errorResponse,HttpStatus.BAD_REQUEST);
	}
}
