package com.goldsmiths.comp.sec;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

/***
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
		GenerateRandPrime rsa = new GenerateRandPrime(input);
		rsa.encrypt();
		rsa.decrypt();
	}

	private void part2() {
		System.out.println("In part2");
	}

	public static void main(String[] args) {
		new menu();
	}
}