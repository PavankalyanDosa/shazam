package com.restful.exceptions;

public class DataNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5393141429310978843L;

	public DataNotFoundException(String message) {
		super(message);
	}

}
