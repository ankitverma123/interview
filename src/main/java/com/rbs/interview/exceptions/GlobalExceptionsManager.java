package com.rbs.interview.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.rbs.interview.dto.ErrorReponse;

@ControllerAdvice
public class GlobalExceptionsManager {

	@ExceptionHandler(UnSupportedStrategyAlgorithm.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ResponseEntity<ErrorReponse> handleUnSupportedStrategyException(UnSupportedStrategyAlgorithm ex) {
		ErrorReponse er = new ErrorReponse(HttpStatus.BAD_REQUEST, ex.getMessage());
		return new ResponseEntity<>(er, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MinimumSupportedValueException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ResponseEntity<ErrorReponse> handleMinimumSupportedValueException(MinimumSupportedValueException ex) {
		ErrorReponse er = new ErrorReponse(HttpStatus.BAD_REQUEST, ex.getMessage());
		return new ResponseEntity<>(er, HttpStatus.BAD_REQUEST);
	}
	
	@ResponseBody
	@ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
	@ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
	public ResponseEntity<ErrorReponse> handleHttpMediaTypeNotAcceptableException() {
		ErrorReponse er = new ErrorReponse(HttpStatus.UNSUPPORTED_MEDIA_TYPE, String.format("Supported MIME types are: \"%s\" or \"%s\"", MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML));
		return new ResponseEntity<>(er, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
	}

}
