package com.goldsmiths.comp.sec.model;

/**
 * This class acts as the user that communicates with the server.
 * 
 * @author Mariano Pekoenja , Adam Letch, Desmond Pitt
 */
public class User {
	
	/**
	 * Sends a request to the server to initiate communication to a given user
	 * 
	 * @param server The server instance
	 * @param user User to communicate with
	 */
	private void sendRequest(Server server, User user) {
		
	}
	
	/**
	 * 
	 * @param server The object receiving the public key in this instance
	 */
	private void shareKey(Server server, Key key) {
		server.setPublicKey(key);
	};

}