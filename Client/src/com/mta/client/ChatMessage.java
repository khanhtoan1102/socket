package com.mta.client;

import java.io.Serializable;

public class ChatMessage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 519357363009036638L;

	// The different types of message sent by the Client
	// WHOISIN to receive the list of the users connected
	// MESSAGE an ordinary message
	// LOGOUT to disconnect from the Server
	static final int WHOISIN = 0, MESSAGE = 1, LOGOUT = 2;

	private int type;

	private String message;

	// constructor

	ChatMessage(int type, String message) {

		this.type = type;

		this.message = message;

	}

	// getters
	int getType() {

		return type;

	}

	String getMessage() {

		return message;

	}

}
