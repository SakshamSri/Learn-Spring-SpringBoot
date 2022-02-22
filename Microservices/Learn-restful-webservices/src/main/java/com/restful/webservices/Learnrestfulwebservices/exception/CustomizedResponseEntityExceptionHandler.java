package com.restful.webservices.Learnrestfulwebservices.exception;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	// for all exceptions, specific exceptions can be handled by making that
	// exception class and adding a function here so that structure remains same
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
		GlobalResponseException responseException = new GlobalResponseException(new Date(), ex.getMessage(),
				request.getDescription(false)); // the parameters needs to be fine tuned for each exceptions because you
												// dont want to leak any sensitive information
		return new ResponseEntity<Object>(responseException, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	// for user not found exception case
	@ExceptionHandler(UserNotFoundException.class)
	public final ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException ex, WebRequest request) {
		GlobalResponseException responseException = new GlobalResponseException(new Date(), ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<Object>(responseException, HttpStatus.NOT_FOUND);
	}

	// for user details not valid (null check) exception case
	@ExceptionHandler(UserDetailsNotValidException.class)
	public final ResponseEntity<Object> handleUserDetailsNotValidException(UserDetailsNotValidException ex,
			WebRequest request) {
		GlobalResponseException responseException = new GlobalResponseException(new Date(), ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<Object>(responseException, HttpStatus.BAD_REQUEST);
	}

	// for post details not valid (null check) exception case
	@ExceptionHandler(PostDetailsNotValidException.class)
	public final ResponseEntity<Object> handlePostDetailsNotValidException(PostDetailsNotValidException ex,
			WebRequest request) {
		GlobalResponseException responseException = new GlobalResponseException(new Date(), ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<Object>(responseException, HttpStatus.BAD_REQUEST);
	}

	// for post not found exception case
	@ExceptionHandler(PostNotFoundException.class)
	public final ResponseEntity<Object> handlePostNotFoundException(PostNotFoundException ex, WebRequest request) {
		GlobalResponseException responseException = new GlobalResponseException(new Date(), ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<Object>(responseException, HttpStatus.NOT_FOUND);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		GlobalResponseException responseException = new GlobalResponseException(new Date(), "Validation Failed",
				ex.getBindingResult().toString()); // customize the BindingResult to show your own custom message
													// details
		return new ResponseEntity<Object>(responseException, HttpStatus.BAD_REQUEST);
	}
}
