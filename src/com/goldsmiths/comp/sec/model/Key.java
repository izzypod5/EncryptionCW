package com.goldsmiths.comp.sec.model;

/**
 * Class representing a given key
 * 
 * @author Mariano Pekoenja , Adam Letch, Desmond Pitt
 *
 */
public class Key {

	//owner of the key
	private User owner;
	//value of key
	private String value;
	//boolean check on whether server has signed key
	private boolean isSigned = false;

	/**
	 * Constructor for Key class
	 * 
	 * @param value
	 */
	public Key(String value) {
		this.value = value;
	}
	
	/**
	 * Constructor for Key class
	 * 
	 * @param value
	 */
	public Key(String value, User user) {
		this.value = value;
		this.owner = user;
	}

	/**
	 * Value field getter
	 * 
	 * @return
	 */
	public String getValue() {
		return value;
	}

	/**
	 * Value field setter
	 * 
	 * @param value
	 */
	public void setValue(String value) {
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
