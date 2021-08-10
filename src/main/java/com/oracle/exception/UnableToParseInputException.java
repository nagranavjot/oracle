package com.oracle.exception;

/**
 * This is custom exception that will be thrown if the input cannot be parsed
 * for any reason.
 * 
 * @author navjot
 *
 */
public class UnableToParseInputException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1884040329258733763L;

	public UnableToParseInputException() {
		super();
	}

	public UnableToParseInputException(String message) {
		super(message);
	}

}
