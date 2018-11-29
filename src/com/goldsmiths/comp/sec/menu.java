package com.goldsmiths.comp.sec;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;

import com.goldsmiths.comp.sec.model.Key;
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
	
	private static final String TITLE = "\n IS53012A/B 22Computer Security coursework\n"
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
		System.out.println("In part2");
		User alice = new User("Alice");
		User bob = new User("Bob");
		ArrayList<User> userList = new ArrayList<>();
		userList.add(alice);
		userList.add(bob);
		//create server
		//TODO: establish of request
		Server server = new Server(userList, 512);
		//initiate request to bob
		Request request = new Request(100, alice, bob);
		//alice sends request to server
		alice.sendRequest(server, request);
		//signed key returned from server response
		byte[] signedKey = server.sendResponse(alice); // returns signed Key
		System.out.println(signedKey.toString());
		
		//TODO: temp code resolve to custom exception handling if have time
/*		if (signedKey == null) {
			System.out.println("User not found by server!");
			return;
		}*/
		
		//alice sets nonce to request (random number identifying request to bob)
		alice.sendNonce(request); //uses bobs public key
		
		
		//bob.sendRequest(Server server, alice)
		//server.sendResponse(User bob)
		//bob.snedDoubleNonce(User alice);
		//alice.sendProofNonce(User bob) //proving decryption
	}

	public static void main(String[] args) {
		new menu();
	}
}