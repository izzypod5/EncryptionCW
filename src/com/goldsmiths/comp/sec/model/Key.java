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
	// boolean check on whether server has signed key
	private boolean isSigned = false;

	/**
	 * Constructor for Key class
	 * 
	 * @param value
	 */
	public Key(KeyPair value) {
		this.value = value;
	}

	/**
	 * Constructor for Key class
	 * 
	 * @param value
	 */
	public Key(KeyPair value, User user) {
		this.value = value;
		this.owner = user;
	}
	
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
	 * @return
	 */
	public KeyPair getValue() {
		return value;
	}

	/**
	 * Value field setter
	 * 
	 * @param value
	 */
	public void setValue(KeyPair value) {
		this.value = value;
	}

	/**
	 * Boolean sign field getter
	 * 
	 * @return
	 */
	public boolean isSigned() {
		return isSigned;
	}

	/**
	 * Boolean sign field setter
	 * 
	 * @param isSigned
	 */
	public void setSigned(boolean isSigned) {
		this.isSigned = isSigned;
	}

	/**
	 * Getter for owner field
	 * 
	 * @return
	 */
	public User getOwner() {
		return owner;
	}

	/**
	 * Setter for owner field
	 * 
	 * @param owner
	 */
	public void setOwner(User owner) {
		this.owner = owner;
	}

}
