package com.goldsmiths.comp.sec.model;

import java.math.BigInteger;
import java.util.Random;

/**
 * This class acts as the user that communicates with the server.
 * 
 * @author Mariano Pekoenja , Adam Letch, Desmond Pitt
 */
public class User {

	private String name;
	private Key publicKey;
	
	public User(String name) {
		this.name = name;
	}

	public void sendRequest(Server server, Request request) {
		System.out.println("Request sent from: " + request.getFromUser().getName() + " to " + request.getToUser().getName());
		server.setCurrentRequest(request);
	}
	
	public Request sendNonce(Request request) {
		BigInteger randomBigInt = randomBigInteger(BigInteger.valueOf(1000000));
		System.out.println("Random Big Int value: " + randomBigInt);
		request.setNonce(randomBigInt);
		return request;
	}
	
	/**
	 * Generate a random big integer with range up to n
	 * @param n
	 * @return
	 */
	public BigInteger randomBigInteger(BigInteger n) {
	    Random randomNumber = new Random();
	    BigInteger result = new BigInteger(n.bitLength(), randomNumber);
	    while( result.compareTo(n) >= 0 ) {
	        result = new BigInteger(n.bitLength(), randomNumber);
	    }
	    return result;
	}

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