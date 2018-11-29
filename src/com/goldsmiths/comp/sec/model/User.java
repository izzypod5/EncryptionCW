package com.goldsmiths.comp.sec.model;

/**
 * This class acts as the user that communicates with the server.
 * 
 * @author Mariano Pekoenja , Adam Letch, Desmond Pitt
 */
public class User {

	private String name;
	private Key publicKey;

	/**
	 * Getter for user name field
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * Setter for user name field
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Users public key getter
	 * 
	 * @return
	 */
	public Key getPublicKey() {
		return publicKey;
	}

	/**
	 * Users public key setter
	 * 
	 * @param publicKey
	 */
	public void setPublicKey(Key publicKey) {
		this.publicKey = publicKey;
	};

}