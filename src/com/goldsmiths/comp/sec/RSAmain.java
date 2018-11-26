
import java.util.*;
import java.math.*;

public class RSAmain{


 public static void main(String[] args) {

BigInteger p = largePrime(20);
BigInteger q = largePrime(20);
BigInteger n = n(p, q);
BigInteger phi = computePhi(p,q);


System.out.println("p is : " + p );
System.out.println("q is : " + q );
System.out.println("n is : " + n );
System.out.println("the value of phi () is : " + phi); 




}


public static BigInteger largePrime(int bit) {
		Random random = new Random();
		BigInteger bigPrime = BigInteger.probablePrime(bit, random);
		return bigPrime;
	}

  public static BigInteger n (BigInteger p, BigInteger q) {
  		return p.multiply(q);

  	}

    public static BigInteger computePhi(BigInteger p,  BigInteger q){
      BigInteger phi = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));
        return phi;


    }



  }
