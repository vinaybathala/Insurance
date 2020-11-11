package com.insurance.exception;

import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.insurance.dto.ErrorResponsedto;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = UserNotFoundException.class)
	public ResponseEntity<ErrorResponsedto> handleException(UserNotFoundException userException) {
		ErrorResponsedto response = new ErrorResponsedto();
		response.setStatusCode("404 non found");
		response.setStatusMessage(userException.getMessage());
		return new ResponseEntity<ErrorResponsedto>(response, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = InsuranceNotFoundException.class)
	public ResponseEntity<ErrorResponsedto> handleException(InsuranceNotFoundException insuranceException) {
		ErrorResponsedto response = new ErrorResponsedto();
		response.setStatusCode("205 not found ");
		response.setStatusMessage(insuranceException.getMessage());
		return new ResponseEntity<ErrorResponsedto>(response, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = RegistrationIdNotFoundException.class)
	public ResponseEntity<ErrorResponsedto> handleException(RegistrationIdNotFoundException registrationException) {
		ErrorResponsedto response = new ErrorResponsedto();
		response.setStatusCode("406 not found ");
		response.setStatusMessage(registrationException.getMessage());
		return new ResponseEntity<ErrorResponsedto>(response, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = InsuranceMaintenanceException.class)
	public ResponseEntity<ErrorResponsedto> handleException(InsuranceMaintenanceException insuranceException) {
		ErrorResponsedto response = new ErrorResponsedto();
		response.setStatusCode("412 not found ");
		response.setStatusMessage(insuranceException.getMessage());
		return new ResponseEntity<ErrorResponsedto>(response, HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException argInvalidException,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ErrorResponsedto response = new ErrorResponsedto();
		response.setStatusCode("505 not a valid Credentials");
		String allFieldErrors = argInvalidException.getBindingResult().getFieldErrors().stream()
				.map(e -> e.getDefaultMessage()).collect(Collectors.joining(", "));
		response.setStatusMessage(allFieldErrors);
		return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
	}
}
