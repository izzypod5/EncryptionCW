//package com.goldsmiths.comp.sec;
import java.util.*;

public class GenerateRandPrime {

 public static void main(String[] args) {
   //int a = 4;
  // int b = 7;
  // int x = 157;
  //  boolean isPrime1 =  isPrimeBruteForce(a);
  //  boolean isPrime2 =  isPrimeBruteForce(b);
  //  boolean isPrime3 =  isPrimeBruteForce(x);

    int firstPrime = GeneratePrimeRandon();
    int secondPrime = GeneratePrimeRandon();
    int N = conputeN(firstPrime,secondPrime);
    int E = ComputeR(firstPrime,secondPrime);
    int D = (int)FindInverse(E, N);

    int keyPublic []= new int[2];
    keyPublic [0]=E;
    keyPublic [1]=N;
    int keyPrivate []= new int[2];
    keyPrivate [0]=D;
    keyPrivate [1]=N;



  //  int keyPrivate = new int[2];




//System.out.println("is "+ a +" prime? "+isPrime1);
//System.out.println("is "+ b +" prime? "+isPrime2);
//System.out.println("is "+ x +" prime? " +isPrime3);
int m = 123456;

System.out.println("original message is: " + m);

System.out.println(firstPrime);
System.out.println(secondPrime);
System.out.println("The product of two Prime number(N) is: " + N);
System.out.println("The computation of E is: " + E);
System.out.println("The value of D is: " + D );
System.out.println("The value of keyPublic is: "   );

//TODO: generate keys in a function
//generatePairKey(firstPrime , secondPrime);

//System.out.println("R is " + r);

//System.out.println("E is the value = " +e);
for (int i = 0; i<keyPublic.length;i++){
  System.out.println( keyPublic[i]);
}
System.out.println("The value of private key is: " + keyPrivate[0] + "  "+keyPrivate[1]  );
int c = doRSA(m,E,N);

int om = doRSA(c,D,N);



System.out.println("chipertext is: " + c);

System.out.println("decripted message is: " + om);


    }
    /*
public static long extendedmEuclidian(long a,long b){
  long x =0, y=1, lastx= 1, lasty = 0, temp;

  while(b != 0){

    long q = a/b;
    long r = a% b;

    a=b;
    b=r;

    temp = x;
    x = lastx- q * x;
    lastx = temp;

    temp = y;
    y = lasty - q * y;
    lasty = temp ;
      }
      return null;
}
*/
  public static int doRSA(int x ,int e , int n ){
      int y = 1;
      int u = x % n;
// n is e
// m is n
  System.out.println("e is : "+e);
      while(e!=0){
        //if odd number
        System.out.println("e mod 2 is : "+e %2);

        if(e%2 == 1){

          y *= u % n;
          System.out.println("y is : "+y);
        }
        //  divide e in 2
        e = e/2;
        System.out.println("e after div is : "+e);

        if(e!=1){
          u=u * u % n;
          System.out.println("u  is : "+ u);

        }

      }
      return y;


    }





public static int GeneratePrimeRandon(){
    Random random = new Random();
    int rand = random.nextInt(5000)+1000;
    //TODO:adjusting the +100 regulate the rangecd
      if(isPrimeBruteForce(rand) == false ){
        int m = GeneratePrimeRandon();
        return m;
      }else{
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
public static int conputeN(int q, int p){
  int p1 = p;

  int q1 = q;

  int n = p1*q1;

  return n;

}
  public static int ComputeR(int p,int q){
   int  p2 = p-1;
   int q2 = q-1;

    int r = p2*q2;
    return r;
  }
//trying to compute e in a random way....
  public static int computeE(int r){
    Random random = new Random();

    int e = random.nextInt(r)+10000;
    if (CheckFactor(e,r) ){

      computeE(r);
    }
    return e;
  }
/*experiment to find the multiplicative inverse of e...
  static int modInverse(int a, int m){
       a = a % m;
       for (int x = 1; x < m; x++){
          if ((a * x) % m == 1)
             return x;
           }

   }
   */
   public static long FindInverse(long a, long b){
     long store = a;
     long temp;
     long q;
     long sign = 1;
     long r = 1;
     long s = 0;
     while(b!=0){
       q = a/b;
       temp = r;
       r = temp*q+r;
       s = temp;
       temp = b;
       b = a - q*temp;
       a = temp;
       sign = -sign;

     }
     long answ = (r-(sign*s))%store;
     return answ;



   }







  public static boolean CheckFactor(int a,int b){
      boolean isFactor = true;
      int factor = b%a;
      if ( a>0 && factor!=0){
        isFactor=false;
      }
      return isFactor;
  }


/*
  public static int[] generatePairKey(int x , int y ){
    int erre = ComputeR(x,y);
    int E = computeE(erre);
    keys = new int[2];
    System.out.println("R is " + erre);
    System.out.println("E is the value = " +E);
    keys[0] = erre;
    keys[1] = E;
    return keys;
  }
*/




}
