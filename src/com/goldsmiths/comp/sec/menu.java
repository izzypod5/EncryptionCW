package com.goldsmiths.comp.sec;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;

import com.goldsmiths.comp.sec.exceptions.UserNotFoundException;
import com.goldsmiths.comp.sec.model.Request;
import com.goldsmiths.comp.sec.model.Server;
import com.goldsmiths.comp.sec.model.User;

/**
 * User interface class to display output via the console
 * 
 * @author Mariano Pecorella , Adam Letch, Desmond Pitt
 *
 */
public class menu {

	//menu title used as a constant to be initially displayed as like the index page
	private static final String TITLE = "\n IS53012A/B Computer Security coursework\n"
			+ " by Mariano-PEKOENJA, Adam-LETCH, Desmond-PITT\n\n"
			+ "\t********************************************************************************\n"
			+ "\t1. Question 1 \n" + "\t2. Question 2 \n" + "\t0. Exit \n"
			+ "\t********************************************************************************\n"
			+ "Please input a single digit (0-2):\n";

	/**
	 * Constructor for the menu class, initialises console actions
	 */
	public menu() {
		int selected = -1;
		System.out.println(TITLE);
		while (selected != 0) {
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			try {
				selected = Integer.parseInt(in.readLine());
				switch (selected) {
				case 1:
					part1(in);
					break;
				case 2:
					part2();
					break;
				}
				System.out.println(TITLE);
			} catch (Exception ex) {
				System.out.println("Invalid user input detected please input a number 0-2");
			}
		} // end while
		System.out.println("Bye!");
	}

	/**
	 * Represents the actions of the first part of the coursework
	 * 
	 * @param in takes in bufferreader to responds to console input from the user
	 * @throws IOException occurs when invalid input is used by the user when converting to BigInteger
	 */
	private void part1(BufferedReader in) throws IOException {
		System.out.println("In part1");
		System.out.println("Please enter the message you wish to be encrypted:");
		BigInteger input = new BigInteger(in.readLine());
		RSAImpl rsa = new RSAImpl(input);
		rsa.encrypt();
		rsa.decrypt();
	}

	/**
	 * Represents the actions of the second part of the coursework establishing authentication using a server to allow communication between alice and bob
	 */
	private void part2() {
		try {
			System.out.println("In part 2");

			// creation of users and server
			User alice = new User("Alice");
			User bob = new User("Bob");
			ArrayList<User> userList = new ArrayList<>();
			userList.add(alice);
			userList.add(bob);
			Server server = new Server(userList, 512);

			// initiate request to bob
			Request request = new Request(101, alice, bob);

			// alice sends request to server
			alice.sendRequest(server, request);

			// returns bobs signed public key returned from server response
			byte[] bobsSignedKey = server.sendResponse(bob);
			System.out.println(bobsSignedKey.toString());

			// alice sends the bob her nonce to be stored
			alice.sendNonces(bob);
			BigInteger bobsStoredAliceNonce = bob.getNonceMap().get(alice);
			System.out.println("Dear Bob, here is my nonce: " + bobsStoredAliceNonce
					+ " that only you can read. Yours sincerely Alice.");

			// initiate response request to alice
			Request responseRequest = new Request(102, bob, alice);

			// bob sends request to server
			bob.sendRequest(server, responseRequest);

			// signed key returned from server response
			byte[] alicesSignedKey = server.sendResponse(bob);
			System.out.println("Alice's signed key: " + alicesSignedKey.toString());

			// bob creates his nonce adding to his nonce storage and then sends both the
			// previously stored alice nonce as well as his own nonce to alice
			bob.sendNonces(alice);

			// proof of bob receiving alice's nonce
			BigInteger bobsStoredNonce = alice.getNonceMap().get(bob);
			BigInteger aliceStoredNonce = alice.getNonceMap().get(alice);
			System.out.println("Here is your nonce: " + bobsStoredNonce + " and my nonce: " + aliceStoredNonce
					+ " proving I decrypted it. Yours sincerely Bob.");
		} catch (UserNotFoundException e) {
			// user not found when server sends response
			System.out.println(e.getMessage());
		}

	}

	/**
	 * Main method which just calls the constructor
	 * 
	 * @param args any arguments to send main method of java program
	 */
	public static void main(String[] args) {
		new menu();
	}
}