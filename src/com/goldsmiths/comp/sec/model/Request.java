package com.goldsmiths.comp.sec.model;
/**
 * Request object symbolising a communication attempt
 * 
 * @author Mariano Pekoenja , Adam Letch, Desmond Pitt
 *
 */
public class Request {
	//id of a request used to differentiate requests
	public long id;
	//user the request originated from
	public User fromUser;
	//user the request attempted communication with
	public User toUser;
	// TODO: establish time limit on request

	/**
	 * Constructor for request object
	 * 
	 * @param id unique identifier for the request
	 * @param fromUser user starting request
	 * @param toUser user to communicate to
	 */
	public Request(long id, User fromUser, User toUser) {
		this.id = id;
		this.fromUser = fromUser;
		this.toUser = toUser;
	}

	/**
	 * Getter for id field
	 * 
	 * @return identifier of request object
	 */
	public long getId() {
		return id;
	}

	/**
	 * Setter for id field
	 * 
	 * @param id unique identifier to be set as long value
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * Getter for the from user field
	 * 
	 * @return user who started request
	 */
	public User getFromUser() {
		return fromUser;
	}

	/**
	 * Setter for from user field
	 * 
	 * @param fromUser user requesting communication
	 */
	public void setFromUser(User fromUser) {
		this.fromUser = fromUser;
	}

	/**
	 * Getter for the toUser field
	 * 
	 * @return the user to request to
	 */
	public User getToUser() {
		return toUser;
	}

	/**
	 * Setter for the toUser field
	 * 
	 * @param toUser requested user for communication
	 */
	public void setToUser(User toUser) {
		this.toUser = toUser;
	}

}