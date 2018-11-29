package com.goldsmiths.comp.sec.model;

/**
 * This class acts as the trusted server which distributes public keys on behalf of others.
 * 
 * @author Mariano Pekoenja , Adam Letch, Desmond Pitt
 */
public class Server {

	// public key from given user to be stored
	private Key publicKey;
	
	/**
	 * Signs a key
	 * @param key
	 */
	private void signKey(Key key) {
		
	}

	/**
	 * Returns the value of the stored public key from the given user
	 * 
	 * @return an instance of the Key class
	 * @see Key
	 */
	public Key getPublicKey() {
		return publicKey;
	}

	/**
	 * Sets the public key of a given user
	 * 
	 * @param key value to be set for key
	 */
	public void setPublicKey(Key key) {
		this.publicKey = key;
	}
	
}