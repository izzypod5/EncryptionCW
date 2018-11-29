package com.goldsmiths.comp.sec.model;

import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

/**
 * This class acts as the trusted server which distributes public keys on behalf
 * of others.
 * 
 * @author Mariano Pekoenja , Adam Letch, Desmond Pitt
 */
public class Server {

	// public key known by all communications used for signing
	private Key publicKey;
	// ArrayList of users set to server, server has the role of setting public keys
	private ArrayList<User> users = new ArrayList<>();
	private Request currentRequest;

	/**
	 * Constructor for server
	 * 
	 * @param publicKey
	 *            used for signing keys
	 * @param users
	 *            are the users connected to server
	 */
	public Server(ArrayList<User> users, int serverKeySize) {
		this.users = users;
		try {
			this.publicKey = this.generateKey(serverKeySize);
			System.out.println("Server's public key: " + this.getPublicKey().getValue());
			// generation of users public keys
			for (int i = 0; i < users.size(); i++) {
				Key generatedKey = generateKey(serverKeySize);
				users.get(i).setPublicKey(generatedKey);
				System.out.println(users.get(i).getName() + "'s public key: " + users.get(i).getPublicKey().getValue());
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Generates RSA key
	 * 
	 * @param keySize
	 *            number of bits of the keypair
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public Key generateKey(int keySize) throws NoSuchAlgorithmException {
		KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
		keyGen.initialize(keySize);
		byte[] generatedKey = keyGen.genKeyPair().getPublic().getEncoded();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < generatedKey.length; ++i) {
			sb.append(Integer.toHexString(0x0100 + (generatedKey[i] & 0x00FF)).substring(1));
		}
		Key key = new Key(sb.toString());
		return key;
	}

	/**
	 * Signs a key
	 * 
	 * @param key
	 * @return
	 */
	public Key signKey(Key key) {
		// TODO: sign given key from user using the servers public key
		System.out.println("Signing key from: " + key.getOwner());
		return key;
	}

	/**
	 * Sends a response from the server to the user if given user is found by server
	 * 
	 * @param user
	 *            to be communicated to
	 * @return
	 */
	public Key sendResponse(User user) {
		// signing of requested users stored public key
		for (int i = 0; i < users.size(); i++) {
			User current = users.get(i);
			if (current.equals(user)) {
				return this.signKey(user.getPublicKey());
			}
			// TODO: add custom exception USER NOT FOUND
		}
		return null;
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

	/**
	 * Getter for users list
	 * 
	 * @return
	 */
	public ArrayList<User> getUsers() {
		return users;
	}

	/**
	 * Setter for users list
	 * 
	 * @param users
	 */
	public void setUsers(ArrayList<User> users) {
		this.users = users;
	}

	public Request getCurrentRequest() {
		return currentRequest;
	}

	public void setCurrentRequest(Request currentRequest) {
		this.currentRequest = currentRequest;
	}

}