//package com.goldsmiths.comp.sec;
import java.util.*;

public class GenerateRandPrime {

 public static void main(String[] args) {
   int a = 4;
   int b = 7;
   int x = 157;
    boolean isPrime1 =  isPrimeBruteForce(a);
    boolean isPrime2 =  isPrimeBruteForce(b);
    boolean isPrime3 =  isPrimeBruteForce(x);

    int firstPrime = GeneratePrimeRandon();
    int secondPrime = GeneratePrimeRandon();
    int sumOfPrime = firstPrime * secondPrime;

System.out.println("is "+ a +" prime? "+isPrime1);
System.out.println("is "+ b +" prime? "+isPrime2);
System.out.println("is "+ x +" prime? " +isPrime3);
System.out.println(firstPrime);
System.out.println(secondPrime);
System.out.println("The product of two Prime number is: " + sumOfPrime);

    }



public static boolean isPrimeBruteForce(int number) {
    for (int i = 2; i < number; i++) {
        if (number % i == 0) {
            return false;
        }
    }
    return true;
}


public static int GeneratePrimeRandon(){
    Random rand = new Random();
    int n = rand.nextInt(500)+100;
    //TODO:adjusting the +100 regulate the range
      if(isPrimeBruteForce(n) == false){
        int m = GeneratePrimeRandon();
        return m;
      }else{
  return n;
    }
  }



}
