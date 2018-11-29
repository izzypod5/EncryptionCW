package com.goldsmiths.comp.sec.model;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.Signature;
import java.util.ArrayList;

import sun.misc.BASE64Encoder;

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
			this.publicKey = new Key(this.generateKey(serverKeySize));
			System.out.println("Server's public key: " + this.getPublicKey().getValue());
			// generation of users public keys
			for (int i = 0; i < users.size(); i++) {
				Key generatedKey = new Key(generateKey(serverKeySize), users.get(i));
				users.get(i).setPublicKey(generatedKey);
				System.out.println(
						users.get(i).getName() + "'s public key: " + users.get(i).getPublicKey().getStringValue());
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
	public KeyPair generateKey(int keySize) throws NoSuchAlgorithmException {
		KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
		kpg.initialize(keySize);
		return kpg.genKeyPair();
	}

	/**
	 * Signs a key using the servers private key and can be decrypted using the
	 * known public key of the server
	 * 
	 * @param key
	 * @return
	 */
	public byte[] signKey(Key desiredKey) {
		byte[] data = desiredKey.getStringValue().getBytes();
		// TODO: sign given key from user using the servers public key
		System.out.println("Signing key from: " + desiredKey.getOwner().getName());
		try {
			// sign using the servers known public key using sha1 rsa
			Signature sig;
			sig = Signature.getInstance("SHA1WithRSA");

			sig.initSign(getPublicKey().getValue().getPrivate());
			sig.update(data);
			byte[] sigBytes = sig.sign();
			System.out.println("Signature:" + new BASE64Encoder().encode(sigBytes));
			// signing the key given to uswhich is the users public key
			sig.initVerify(getPublicKey().getValue().getPublic());
			sig.update(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}

	/**
	 * Sends a response from the server to the user if given user is found by server
	 * 
	 * @param user
	 *            to be communicated to
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public byte[] sendResponse(User user) {
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