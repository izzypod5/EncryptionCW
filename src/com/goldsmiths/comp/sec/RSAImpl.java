package com.goldsmiths.comp.sec;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Random;

/***
 * This class acts as the RSA implementation class.
 * 
 * @author Mariano Pecorella , Adam Letch, Desmond Pitt
 *
 */
public class RSAImpl {

	// message used for encryption
	private BigInteger msg;

	BigInteger firstPrime = generateRandomPrime(128);
	BigInteger secondPrime = generateRandomPrime(128);
	BigInteger n = conputeN(firstPrime, secondPrime);
	BigInteger phi = computePhi(firstPrime, secondPrime); // p-1 * q-1
	BigInteger e = findE(phi);
	BigInteger d = findD(e, phi);

	BigInteger[] keyPublic = new BigInteger[] { e, n };
	BigInteger[] keyPrivate = new BigInteger[] { d, n };

	/**
	 * Class constructor for RSA encryption class
	 * 
	 * @param msg to be used by RSA implementation class
	 */
	public RSAImpl(BigInteger msg) {
		this.msg = msg;

		// debugging
		System.out.println("original message is: " + getMsg());
		System.out.println("First Prime: " + firstPrime);
		System.out.println("Second Prime: " + secondPrime);
		System.out.println("The product of two Prime number(N) is: " + n);
		System.out.println("The computation of E is: " + e);
		System.out.println("The value of D is: " + d);

		System.out.println("The value of public key is: " + keyPublic[0] + "  " + keyPublic[1]);
		System.out.println("The value of private key is: " + keyPrivate[0] + "  " + keyPrivate[1]);

	}

	/**
	 * Encryption method to be called when using the instance of the class
	 */
	public void encrypt() {
		BigInteger c = doRSA(getMsg(), e, n);
		// sets the msg field to the value of the cipher text
		setMsg(c);
		System.out.println("chipertext is: " + c);
	}

	/**
	 * Decryption method to be called when using the instance of the class
	 */
	public void decrypt() {
		BigInteger om = doRSA(getMsg(), d, n);
		System.out.println("decripted message is: " + om);
		// sets the msg field to the value of the decrypted text
		setMsg(om);
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

		return a.modPow(possPrime.subtract(BigInteger.ONE), possPrime).compareTo(BigInteger.ONE) == 0;
	}

	/**
	 * Used to perform encryption to get the cipher text or decryption to return the
	 * original message
	 * 
	 * @param msg message to be used for RSA
	 * @param exp exponent to be used
	 * @param mod modulus value
	 * @return the value returned after the computation of the: c = m^e mod n | m =
	 *         c^d mod n
	 */
	public static BigInteger doRSA(BigInteger msg, BigInteger exp, BigInteger mod) {

		return msg.modPow(exp, mod);

	}

	/**
	 * Computes the value of E
	 * 
	 * @param phi to be used for comparison when generating the value for e
	 * @return value of E
	 */
	public static BigInteger findE(BigInteger phi) {
		BigInteger e = randomBigInteger(BigInteger.valueOf(1000000000));
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
//	public static BigInteger generateRandomPrime() {
//	    BigInteger rand = new BigInteger();
//		BigInteger rand = BigInteger.valueOf(random.nextInt(5000) + 1000);
	
//		// TODO:adjusting the +100 regulate the rangecd
//		if (!isPrimeNumber(rand)) {
//			return generateRandomPrime();
//		} else {
//			return rand;
//		}
//	}
//	public static BigInteger generateRandomPrime(BigInteger bigInteger) {
//		BigInteger rnd = randomBigInteger(BigInteger.valueOf(10000000000000000000));
//		//Random rnd = new Random();
//	    BigInteger prime = new BigInteger("4");
//	    while(!isPrimeNumber(prime)) prime = randomBigInteger(BigInteger.valueOf(10000000000000000000));
//	    return prime;
//	}
	public static BigInteger generateRandomPrime(int lenght) {
		
		// create a random object
	      Random rnd = new Random();

	      // assign probablePrime result to bi using bitLength and rnd
	      // static method is called using class name
	      BigInteger random = BigInteger.probablePrime(lenght, rnd);
	      return random;
		
	}
	/**
	 * Generate a random big integer with range up to n
	 * @param n
	 * @return
	 */
	public static BigInteger randomBigInteger(BigInteger n) {
	    Random randomNumber = new Random();
	    BigInteger result = new BigInteger(n.bitLength(), randomNumber);
	    while( result.compareTo(n) >= 0 ) {
	        result = new BigInteger(n.bitLength(), randomNumber);
	    }
	    return result;
	}
	
	
	
//	public static BigInteger randomBigInteger(BigInteger n) {
//	    Random randomNumber = new Random();
//	    BigInteger result = new BigInteger(n.bitLength(), randomNumber);
//	    while( result.compareTo(n) >= 0 ) {
//	        result = new BigInteger(n.bitLength(), randomNumber);
//	    }
//	    return result;
//	}
	/**
	 * Computes the value of N being the multiplication of the two primes
	 * 
	 * @param q1 first prime
	 * @param p1 second prime
	 * @return p1 * q1
	 */
	public static BigInteger conputeN(BigInteger q1, BigInteger p1) {
		return p1.multiply(q1);
	}

	/**
	 * Computes the value of phi being (p-1) * (q-1)
	 * 
	 * @param p first prime
	 * @param q second prime 
	 * @return (p-1) * (q-1) aka phi
	 */
	public static BigInteger computePhi(BigInteger p, BigInteger q) {
		return p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
	}

	/**
	 * Getter for the msg field
	 * 
	 * @return the value of the msg field
	 */
	public BigInteger getMsg() {
		return msg;
	}

	/**
	 * Setter for the msg field
	 * 
	 * @param msg value to be set for msg field
	 */
	public void setMsg(BigInteger msg) {
		this.msg = msg;
	}

}
