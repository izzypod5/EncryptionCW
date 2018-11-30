package com.goldsmiths.comp.sec.exceptions;

/**
 * Custom exception made to recognise when an invalid user not associated with
 * the server is passed
 * 
 * @author Mariano Pekoenja , Adam Letch, Desmond Pitt
 *
 */
public class UserNotFoundException extends Exception {
	private static final long serialVersionUID = 1L;

	/**
	 * Exception constructor
	 * 
	 * @param errorMessage
	 *            message to be sent
	 */
	public UserNotFoundException(String errorMessage) {
		super(errorMessage);
	}
}