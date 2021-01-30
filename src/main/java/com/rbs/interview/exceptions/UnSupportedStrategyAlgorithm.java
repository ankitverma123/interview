package com.rbs.interview.exceptions;

public class UnSupportedStrategyAlgorithm extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3889324240229708031L;

	protected UnSupportedStrategyAlgorithm() {}

	public UnSupportedStrategyAlgorithm(String message) {
		super(message);
	}
}
