package com.goldsmiths.comp.sec;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import com.goldsmiths.comp.sec.model.Request;
import com.goldsmiths.comp.sec.model.Server;
import com.goldsmiths.comp.sec.model.User;

/**
 * 
 * 
 * @author Mariano Pekoenja , Adam Letch, Desmond Pitt
 *
 */
class menu {

	private static final String TITLE = "\n IS53012A/B Computer Security coursework\n"
			+ " by Mariano-PEKOENJA, Adam-LETCH, Desmond-PITT\n\n"
			+ "\t********************************************************************************\n"
			+ "\t1. Question 1 \n" + "\t2. Question 2 \n" + "\t0. Exit \n"
			+ "\t********************************************************************************\n"
			+ "Please input a single digit (0-2):\n";

	menu() {
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
				ex.printStackTrace();
				System.out.println(ex.getMessage());
			}
		} // end while
		System.out.println("Bye!");
	}

	// Modify the types of the methods to suit your purposes...
	private void part1(BufferedReader in) throws IOException {
		System.out.println("In part1");
		System.out.println("Please enter the message you wish to be encrypted:");
		BigInteger input = new BigInteger(in.readLine());
		RSAImpl rsa = new RSAImpl(input);
		rsa.encrypt();
		rsa.decrypt();
	}

	private void part2() {
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

		// TODO: temp code resolve to custom exception handling if have time
		/*
		 * if (signedKey == null) { System.out.println("User not found by server!");
		 * return; }
		 */

		// alice sends the bob her nonce to be stored
		alice.sendNonces(bob);
		BigInteger bobsStoredNonce = bob.getNonceMap().get(bob);
		BigInteger aliceStoredNonce = bob.getNonceMap().get(alice);
		System.out.println("Here is Bob's nonce: " + bobsStoredNonce + " and Alice's nonce: " + aliceStoredNonce
				+ " proving I decrypted it. Yours sincerely Alice.");
		
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
		BigInteger bobsStoredNonce1 = alice.getNonceMap().get(bob);
		BigInteger aliceStoredNonce1 = alice.getNonceMap().get(alice);
		System.out.println("Here is your nonce: " + bobsStoredNonce1 + " and my nonce: " + aliceStoredNonce1
				+ " proving I decrypted it. Yours sincerely Bob.");

	}

	public static void main(String[] args) {
		new menu();
	}
}