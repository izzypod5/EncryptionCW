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

	//user's name
	private String name;
	//public key of the user to be stored
	private Key publicKey;
	//map of nonces the user holds
	private HashMap<User, BigInteger> nonceMap = new HashMap<>();

	/**
	 * Constructor for the user class
	 * 
	 * @param name the name to be associated to the user
	 */
	public User(String name) {
		this.name = name;
	}

	/**
	 * Method is more so to represent the communication has been made to the server
	 * 
	 * @param server Is trusted third party to its users
	 * @param request  Represents the communication request
	 */
	public void sendRequest(Server server, Request request) {
		System.out.println(
				"Request sent from: " + request.getFromUser().getName() + " to " + request.getToUser().getName());
		server.setCurrentRequest(request);
	}

/**
 * Sends the nonces to the given user
 * 
 * @param toUser is the user to receive nonces
 */
	public void sendNonces(User toUser) {
		BigInteger randomBigInt = randomBigInteger(BigInteger.valueOf(1000000));
		System.out.println("Nonce value returned " + randomBigInt + " for " + toUser.getName());
		HashMap<User, BigInteger> nonceMap = toUser.getNonceMap();
		// adds a nonce to the users map of nonces with their own nonce
		for (Entry<User, BigInteger> entry : this.nonceMap.entrySet()) {
			nonceMap.put(entry.getKey(), entry.getValue());
		}
		nonceMap.put(this, randomBigInt);
		System.out.println(this.getName() + " has stored nonce");
		toUser.setNonceMap(nonceMap);
	}

	/**
	 * Generate a random big integer with range up to n.bitlength
	 * 
	 * @param n is the number range using bitlength
	 * @return a random big integer to be used for nonces
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
	 * @return name field of user
	 */
	public String getName() {
		return name;
	}

	/**
	 * Setter for user name field
	 * 
	 * @param name to set for name field
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Users public key getter
	 * 
	 * @return the key object belonging to the user by the server
	 * @see Key
	 */
	public Key getPublicKey() {
		return publicKey;
	}

	/**
	 * Users public key setter
	 * 
	 * @param publicKey setting of the public key by the server
	 */
	public void setPublicKey(Key publicKey) {
		this.publicKey = publicKey;
	}

	/**
	 * Getter for the nonce map the user stores
	 * 
	 * @return map of nonces
	 */
	public HashMap<User, BigInteger> getNonceMap() {
		return nonceMap;
	}

	/**
	 * Setter for nonceMap
	 * 
	 * @param nonceMap contains a map of nonces to be set
	 */
	public void setNonceMap(HashMap<User, BigInteger> nonceMap) {
		this.nonceMap = nonceMap;
	};

}