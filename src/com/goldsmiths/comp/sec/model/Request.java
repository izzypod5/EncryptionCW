package com.goldsmiths.comp.sec.model;

public class Request {

	public long id;
	public User fromUser;
	public User toUser;
	// TODO: establish time limit on request

	public Request(long id, User fromUser, User toUser) {
		this.id = id;
		this.fromUser = fromUser;
		this.toUser = toUser;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User getFromUser() {
		return fromUser;
	}

	public void setFromUser(User fromUser) {
		this.fromUser = fromUser;
	}

	public User getToUser() {
		return toUser;
	}

	public void setToUser(User toUser) {
		this.toUser = toUser;
	}

}