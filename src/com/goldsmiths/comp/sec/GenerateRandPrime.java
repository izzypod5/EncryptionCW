package com.goldsmiths.comp.sec;

import java.math.BigInteger;
import java.util.Random;

/***
 * 
 * 
 * @author Mariano Pekoenja , Adam Letch, Desmond Pitt
 *
 */
public class GenerateRandPrime {

	public static void main(String[] args) {

		BigInteger firstPrime = generateRandomPrime();
		BigInteger secondPrime = generateRandomPrime();
		BigInteger n = conputeN(firstPrime, secondPrime);
		BigInteger phi = computePhi(firstPrime, secondPrime); // p-1 * q-1
		BigInteger e = findE(phi);
		BigInteger d = findD(e, phi);

		BigInteger[] keyPublic = new BigInteger[] { e, n };
		BigInteger[] keyPrivate = new BigInteger[] { d, n };

		//original message passed
		BigInteger m = BigInteger.valueOf(123456);

		//debugging
		System.out.println("original message is: " + m);
		System.out.println("First Prime: " + firstPrime);
		System.out.println("Second Prime: " + secondPrime);
		System.out.println("Is firstprime actually prime? : " + isPrimeNumber(firstPrime));
		System.out.println("Is secondPrime actually prime? : " + isPrimeNumber(secondPrime));
		System.out.println("The product of two Prime number(N) is: " + n);
		System.out.println("The computation of E is: " + e);
		System.out.println("The value of D is: " + d);
		System.out.println("The value of keyPublic is: ");
		for (int i = 0; i < keyPublic.length; i++) {
			System.out.println(keyPublic[i]);
		}
		System.out.println("The value of private key is: " + keyPrivate[0] + "  " + keyPrivate[1]);

		BigInteger c = doRSA(m, e, n);
		System.out.println("chipertext is: " + c);

		BigInteger om = doRSA(c, d, n);
		System.out.println("decripted message is: " + om);

	}

	/**
	 * Computes the value of D
	 * 
	 * @param e
	 * @param phi
	 * @return value of D
	 */
	private static BigInteger findD(BigInteger e, BigInteger phi) {
		return e.modInverse(phi);
	}

	/**
	 * Returns the result of primality test using Fermat's Little Theorum
	 * 
	 * @param possPrime
	 *            testing input for primality
	 * @return boolean value for primality
	 */
	public static boolean isPrimeNumber(BigInteger possPrime) {
		// check if >= 1
		if (possPrime.compareTo(BigInteger.ONE) <= 0) {
			throw new IllegalArgumentException("Given prime must be greater than 1");
		}

		BigInteger a = BigInteger.valueOf(2); // must be an integer between 1 and p-1

		// check for equality to 2
		if (possPrime.compareTo(BigInteger.valueOf(2)) == 0) {
			a = BigInteger.valueOf(1);
		}

		return a.modPow(possPrime.subtract(BigInteger.ONE), possPrime)
				.compareTo(BigInteger.ONE) == 0;
	}

	/**
	 * Used to perform encryption to get the cipher text or decryption to return the
	 * original message
	 * 
	 * @param x
	 * @param e
	 * @param n
	 * @return the value returned after the computation of the: c = m^e mod n | m =
	 *         c^d mod n
	 */
	public static BigInteger doRSA(BigInteger msg, BigInteger exp, BigInteger mod) {

		return msg.modPow(exp, mod);

	}

	/**
	 * Computes the value of E
	 * 
	 * @param phi
	 * @return value of E
	 */
	public static BigInteger findE(BigInteger phi) {
		BigInteger e = generateRandomPrime();
		// while phi gcd with e is greater than 1 and e is less than phi - add 1 to E
		while (phi.gcd(e).compareTo(BigInteger.ONE) > 0 && e.compareTo(phi) < 0) {
			e = e.add(BigInteger.ONE);
		}
		return e;
	}

	/**
	 * Utility method for returning a random prime number
	 * 
	 * @return random prime bigint
	 */
	public static BigInteger generateRandomPrime() {
		Random random = new Random();
		BigInteger rand = BigInteger.valueOf(random.nextInt(5000) + 1000);
		// TODO:adjusting the +100 regulate the rangecd
		if (!isPrimeNumber(rand)) {
			return generateRandomPrime();
		} else {
			return rand;
		}
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
		return p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
	}

}
