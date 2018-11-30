package com.goldsmiths.comp.sec.model;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Random;

/**
 * This class acts as the user that communicates with the server.
 * 
 * @author Mariano Pekoenja , Adam Letch, Desmond Pitt
 */
public class User {

	private String name;
	private Key publicKey;
	private HashMap<User, BigInteger> nonceMap = new HashMap<>();

	public User(String name) {
		this.name = name;
	}

	public void sendRequest(Server server, Request request) {
		System.out.println(
				"Request sent from: " + request.getFromUser().getName() + " to " + request.getToUser().getName());
		server.setCurrentRequest(request);
	}

	/**
	 * Sets a nonce value to the request
	 * 
	 * @param request
	 * @return
	 */
	public void sendNonces(User toUser) {
		BigInteger randomBigInt = randomBigInteger(BigInteger.valueOf(1000000));
		System.out.println("Nonce value returned " + randomBigInt + " for " + toUser.getName());
		HashMap<User, BigInteger> nonceMap = toUser.getNonceMap();
		// adds a nonce to the users map of nonces with their own nonce
		for (Entry<User, BigInteger> entry : this.nonceMap.entrySet()) {
		    System.out.println(entry.getKey().getName() + "/" + entry.getValue());
			nonceMap.put(entry.getKey(), entry.getValue());
		}
		nonceMap.put(this, randomBigInt);
		System.out.println(this.getName() + " has stored nonce");
		toUser.setNonceMap(nonceMap);
	}

	/**
	 * Generate a random big integer with range up to n
	 * 
	 * @param n
	 * @return
	 */
	public BigInteger randomBigInteger(BigInteger n) {
		Random randomNumber = new Random();
		BigInteger result = new BigInteger(n.bitLength(), randomNumber);
		while (result.compareTo(n) >= 0) {
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
	}

	public HashMap<User, BigInteger> getNonceMap() {
		return nonceMap;
	}

	public void setNonceMap(HashMap<User, BigInteger> nonceMap) {
		this.nonceMap = nonceMap;
	};

}