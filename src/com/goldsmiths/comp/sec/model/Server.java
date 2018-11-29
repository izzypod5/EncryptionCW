package com.goldsmiths.comp.sec.model;

/**
 * This class acts as the trusted server which distributes public keys on behalf
 * of others.
 * 
 * @author Mariano Pekoenja , Adam Letch, Desmond Pitt
 */
public class Server {

	// public key known by all communications used for signing
	private Key publicKey;

	/**
	 * Signs a key
	 * 
	 * @param key
	 */
	public void signKey(Key key) {
		// TODO: sign given key from user using the servers public key
		System.out.println("Signing key from: " + key.getOwner());
	}

	/**
	 * Sends a request to the server to initiate communication to a given user
	 * 
	 * @param from
	 * @param to
	 */
	public void executeRequest(User from, User to) {
		System.out.println("This is " + from.getName() + " and I would like to get " + to.getName()
				+ "'s public key. Yours sincerely " + from.getName());
		// signing of the demanded user's key
		this.signKey(to.getPublicKey());
		System.out.println("Here is " + to.getName() + "'s public key signed by me. Yours sincerely S.");
		System.out.println("This is " + from.getName() + " and I have sent you a nonce only you can read. Yours sincerely " + from.getName());
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
	 * @param key
	 *            value to be set for key
	 */
	public void setPublicKey(Key key) {
		this.publicKey = key;
	}

}