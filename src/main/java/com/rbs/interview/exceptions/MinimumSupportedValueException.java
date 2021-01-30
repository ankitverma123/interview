package com.rbs.interview.exceptions;

public class MinimumSupportedValueException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3400868341138524593L;

	protected MinimumSupportedValueException() {}

	public MinimumSupportedValueException(String message) {
		super(message);
	}
}
