package com.goldsmiths.comp.sec;

import java.math.BigInteger;
import java.util.*;

import com.sun.org.apache.xpath.internal.operations.Equals;

/***
 * 
 * 
 * @author Mariano Pekoenja , Adam Letch, Desmond Pitt
 *
 */
public class GenerateRandPrime {

	public static void main(String[] args) {

		//System.out.println("Result of primality test: " + isPrimeNumber(new BigInteger("37")));
		BigInteger firstPrime = BigInteger.valueOf(generateRandomPrime());
		BigInteger secondPrime = BigInteger.valueOf(generateRandomPrime());
		BigInteger N = conputeN(firstPrime, secondPrime);
		BigInteger phi = computePhi(firstPrime, secondPrime); //p-1 * q-1
		BigInteger E = findE(phi);
		BigInteger D = findD(E, phi);
		
		//BigInteger D = findInverse(E, N);

		BigInteger[] keyPublic = new BigInteger[] {E, N};
		BigInteger[] keyPrivate = new BigInteger[] {D, N};

		BigInteger m = new BigInteger("123456");

		System.out.println("original message is: " + m);

		System.out.println("First Prime: " + firstPrime);
		System.out.println("Second Prime: " + secondPrime);
		System.out.println("Is firstprime actually prime? : " + isPrimeNumber(firstPrime));
		System.out.println("Is secondPrime actually prime? : " + isPrimeNumber(secondPrime));
		System.out.println("The product of two Prime number(N) is: " + N);
		System.out.println("The computation of E is: " + E);
		System.out.println("The value of D is: " + D);
		System.out.println("The value of keyPublic is: ");

		for (int i = 0; i < keyPublic.length; i++) {
			System.out.println(keyPublic[i]);
		}
		System.out.println("The value of private key is: " + keyPrivate[0] + "  " + keyPrivate[1]);
		BigInteger c = m.modPow(E, N);
		BigInteger om = c.modPow(D, N);
/*		BigInteger c = doRSA(m, E, N);

		BigInteger om = doRSA(c, D, N);*/

		System.out.println("chipertext is: " + c);

		System.out.println("decripted message is: " + om);

	}
	
	private static BigInteger findD(BigInteger e, BigInteger phi) {
		return e.modInverse(phi);
	}

	/**
	 * Returns the result of primality test using Fermat's Little Theorum
	 * 
	 * @param possPrime testing input for primality
	 * @return boolean value for primality
	 */
	public static boolean isPrimeNumber(BigInteger possPrime) {
		// check if >= 1
		if (possPrime.compareTo(BigInteger.ONE) <= 0) {
			throw new IllegalArgumentException("Given prime must be greater than 1");
		}
		
		int a = 2; //must be an integer between 1 and p-1
		
		//check for equality to 2
		if (possPrime.compareTo(BigInteger.valueOf(2)) == 0) {
			a = 1;
		}
		
		//System.out.println(BigInteger.valueOf(a).modPow(possPrime.subtract(BigInteger.valueOf(1)), possPrime).compareTo(BigInteger.valueOf(1)) == 0);

		return BigInteger.valueOf(a).modPow(possPrime.subtract(BigInteger.valueOf(1)), possPrime).compareTo(BigInteger.valueOf(1)) == 0;
	}

	/**
	 * Used to perform encryption to get the cipher text or decryption to return the original message
	 * 
	 * @param x
	 * @param e
	 * @param n
	 * @return the value returned after the computation of the: c = m^e mod n | m = c^d mod n
	 */
	public static BigInteger doRSA(BigInteger x, BigInteger e, BigInteger n) {
		BigInteger y = new BigInteger("1");
		BigInteger u = x.mod(n);
		// n is e
		// m is n
		System.out.println("e is : " + e);
		while (e.compareTo(BigInteger.valueOf(0)) != 0) {
			// if odd number
			System.out.println("e mod 2 is : " + e.mod(BigInteger.valueOf(2)));

			if (e.mod(BigInteger.valueOf(2)).compareTo(BigInteger.valueOf(1)) == 0) {

				y = y.multiply(u.mod(n));
				System.out.println("y is : " + y);
			}
			// divide e in 2
			e = e.divide(BigInteger.valueOf(2));
			System.out.println("e after div is : " + e);

			if (e.compareTo(BigInteger.valueOf(1)) != 0) {
				u = u.multiply(u.mod(n));
				System.out.println("u  is : " + u);

			}

		}
		return y;

	}
	
	public static BigInteger findE(BigInteger phi) {
/*		BigInteger e = BigInteger.valueOf(generateRandomPrime());
		do {
			e = BigInteger.valueOf(generateRandomPrime());
			// while e is bigger than phi
			while(e.compareTo(phi) == 1) {
				e = BigInteger.valueOf(generateRandomPrime());
			}
		} while(!e.gcd(phi).equals(BigInteger.ONE));
		return e;*/
		
		BigInteger e = BigInteger.valueOf(generateRandomPrime());
		// while phi gcd with e is greater than 1 and e is less than phi - add 1 to E
        while (phi.gcd(e).compareTo(BigInteger.ONE) > 0 && e.compareTo(phi) < 0)
        {
            e.add(BigInteger.ONE);
        }
        return e;
	}

	public static int generateRandomPrime() {
		Random random = new Random();
		int rand = random.nextInt(5000) + 1000;
		// TODO:adjusting the +100 regulate the rangecd
		if (!isPrimeNumber(BigInteger.valueOf(rand))) {
			return generateRandomPrime();
		} else {
			return rand;
		}
	}

	public static boolean isPrimeBruteForce(int number) {
		for (int i = 2; i < number; i++) {
			if (number % i == 0) {
				return false;
			}
		}
		return true;
	}
	/**
	 * Computes the value of N being the multiplication of the two primes
	 * 
	 * @param q1
	 * @param p1
	 * @return p1 * q1
	 */
	public static BigInteger conputeN(BigInteger q1, BigInteger p1) {
		return p1.multiply(q1);
	}

	/**
	 * Computes the value of phi being (p-1) * (q-1)
	 * 
	 * @param p
	 * @param q
	 * @return (p-1) * (q-1)
	 */
	public static BigInteger computePhi(BigInteger p, BigInteger q) {
		return p.subtract(BigInteger.valueOf(1)).multiply(q.subtract(BigInteger.valueOf(1)));
	}

	// trying to compute e in a random way....
/*	public static int computeE(int r) {
		Random random = new Random();

		int e = random.nextInt(r) + 10000;
		if (checkFactor(e, r)) {
			computeE(r);
		}
		return e;
	}*/

	//e * d mod r = 1
	public static BigInteger findInverse(BigInteger e, BigInteger n) {
		BigInteger store = e;
		BigInteger temp;
		BigInteger q;
		BigInteger sign = new BigInteger("1");
		BigInteger r = new BigInteger("1");
		BigInteger s = new BigInteger("0");
		while (n.compareTo(BigInteger.valueOf(0)) != 0) {
			q = e.divide(n);
			temp = r;
			r = temp.multiply(q).add(r);
			s = temp;
			temp = n;
			n = e.subtract(q.multiply(temp));
			e = temp;
			sign = sign.negate();

		}
		return r.subtract(sign.multiply(s)).mod(store);

	}

	public static boolean checkFactor(int a, int b) {
		boolean isFactor = true;
		int factor = b % a;
		if (a > 0 && factor != 0) {
			isFactor = false;
		}
		return isFactor;
	}
	
}
