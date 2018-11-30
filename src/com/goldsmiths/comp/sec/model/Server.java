package com.goldsmiths.comp.sec.model;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.Signature;
import java.util.ArrayList;

import com.goldsmiths.comp.sec.exceptions.UserNotFoundException;

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
	// sets the current request object to the server
	private Request currentRequest;

	/**
	 * Constructor for server class
	 * 
	 * @param users contains the users associated to the server
	 * @param serverKeySize sets the amount of bits of the RSA keys
	 */
	public Server(ArrayList<User> users, int serverKeySize) {
		this.users = users;
		try {
			this.publicKey = new Key(this.generateKey(serverKeySize));
			System.out.println("Server's public key: " + this.getPublicKey().getStringValue());
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
	 * Generates RSA keypair
	 * 
	 * @param keySize
	 *            number of bits of the keypair
	 * @return generated keypair
	 * @throws NoSuchAlgorithmException rsa exception
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
	 * @param desiredKey is the public key of the user that is to be signed
	 * @return signed key
	 */
	public byte[] signKey(Key desiredKey) {
		byte[] data = desiredKey.getStringValue().getBytes();
		// TODO: sign given key from user using the servers public key
		System.out.println("Signing " + desiredKey.getOwner().getName() + "'s public key");
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
	 * @return signed key given successful communication has been reached
	 * @throws UserNotFoundException custom exception thrown in the case that the user param is not recognised by the server
	 */
	public byte[] sendResponse(User user) throws UserNotFoundException {
		boolean userFound = false;
		byte[] result = null;
		// signing of requested users stored public key
		for (int i = 0; i < users.size(); i++) {
			User current = users.get(i);
			if (current.equals(user)) {
				userFound = true;
				result = this.signKey(user.getPublicKey());
				break;
			}
		}

		if (userFound) {
			return result;
		} else {
			throw new UserNotFoundException("No user with name: " + user.getName() + " connected to the server");
		}
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
	 * @return the users associated to the server
	 */
	public ArrayList<User> getUsers() {
		return users;
	}

	/**
	 * Setter for users list
	 * 
	 * @param users are the users to be set to the server
	 */
	public void setUsers(ArrayList<User> users) {
		this.users = users;
	}

	/**
	 * Getter for current request field
	 * 
	 * @return the current request the server holds, if any attempt has been made
	 */
	public Request getCurrentRequest() {
		return currentRequest;
	}

	/**
	 * Setter for current request field
	 * 
	 * @param currentRequest is the request object symbolising a communication request has been attempted to the server
	 */
	public void setCurrentRequest(Request currentRequest) {
		this.currentRequest = currentRequest;
	}

}