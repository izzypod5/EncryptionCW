package com.goldsmiths.comp.sec.model;

import java.security.KeyPair;

/**
 * Class representing a given key
 * 
 * @author Mariano Pekoenja , Adam Letch, Desmond Pitt
 *
 */
public class Key {

	// owner of the key
	private User owner;
	// value of key
	private KeyPair value;

	/**
	 * Constructor for Key class
	 * 
	 * @param value is the keypair generated
	 */
	public Key(KeyPair value) {
		this.value = value;
	}

	/**
	 * Constructor for Key class to be used when user is also known
	 * 
	 * @param value is the keypair generated
	 * @param owner is the owner of the key
	 */
	public Key(KeyPair value, User owner) {
		this.value = value;
		this.owner = owner;
	}
	
	/**
	 * Converts the public key into a string to be displayed as console output
	 * 
	 * @return a stringified version of the public key
	 */
	public String getStringValue() {
		byte[] generatedKey = getValue().getPublic().getEncoded();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < generatedKey.length; ++i) {
			sb.append(Integer.toHexString(0x0100 + (generatedKey[i] & 0x00FF)).substring(1));
		}
		return sb.toString();
	}

	/**
	 * Value field getter
	 * 
	 * @return generated keypair
	 */
	public KeyPair getValue() {
		return value;
	}

	/**
	 * Value field setter
	 * 
	 * @param value to set for generated keypair
	 */
	public void setValue(KeyPair value) {
		this.value = value;
	}

	/**
	 * Getter for owner field
	 * 
	 * @return user the key belongs to
	 */
	public User getOwner() {
		return owner;
	}

	/**
	 * Setter for owner field
	 * 
	 * @param owner to set who the key belongs to
	 */
	public void setOwner(User owner) {
		this.owner = owner;
	}

}
