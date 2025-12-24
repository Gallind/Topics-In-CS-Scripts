package hw6;

import java.math.BigInteger;

import CommonFunctions.BigRandom;
import CommonFunctions.DuoTuple;

public class SamplePrime {

    private static final int[] SMALL_PRIMES = {3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53};

    /**
     * Running the MillerRabin algorithm @param k times on @param N
     * @return False if @param N is composite, True if it's probably a prime
     */
    public static boolean primality(BigInteger N, int k){
        if (N.equals(BigInteger.TWO)) return true;
        if (N.equals(BigInteger.ONE) || N.mod(BigInteger.TWO).equals(BigInteger.ZERO)) return false;
        if (!checkSmallPrimes(N)) return false;

        DuoTuple ur = MillerRabin.compute_UR(N);
        BigInteger u = ur.getX();
        BigInteger r = ur.getY();
        for (int i = 0; i < k; i++){
            BigInteger a = BigRandom.randomRange(N.subtract(new BigInteger("3"))).add(BigInteger.TWO);//2 <= a < N
            if (!MillerRabin.millerRabin(a, u, r.intValue(), N)) return false;
        }
        return true;
    }

    public static boolean checkSmallPrimes(BigInteger n){
        for (int prime : SMALL_PRIMES){
            if(n.mod(BigInteger.valueOf(prime)).equals(BigInteger.ZERO))
                return n.equals(BigInteger.valueOf(prime));
        }
        return true;
    }

    /**
     * Sampling a random prime number of n bits and an error parameter k
     * @param n number of bits that the prime number should have
     * @param k the error parameter (how many primality checks are needed)
     * @return (N, count) where @param N is probably a prime and @param count is the amount of attempts needed
     */
    public static DuoTuple samplePrime(int n, int k){
        BigInteger N = BigRandom.randomOddBitNumber(n);
        boolean isPrime = primality(N, k);
        BigInteger count = BigInteger.ONE;
        while (!isPrime) {
            N = BigRandom.randomOddBitNumber(n);
            isPrime = primality(N, k);
            count = count.add(BigInteger.ONE);
        }
        return new DuoTuple(N, count);
    }
    /**
     * Sampling @param amount prime numbers of @param n bits and an error parameter @param k
     * prints to stdout the result
     * @param n number of bits every prime number should have
     * @param k error parameter
     * @param amount number of prime numbers
     */
    public static void sampleNPrimes(int n, int k, int amount){
        int sumAttempts = 0;
        String msg = "";
        for (int i = 1; i <= amount; i++){
            msg += i + ".\t";
            DuoTuple primeAttempts = samplePrime(n, k);
            BigInteger prime = primeAttempts.getX();
            int attempts = primeAttempts.getY().intValue();
            sumAttempts += attempts;
            msg += prime + "\n";
            msg += "\tTook " + attempts + " attempts\n\n";
        }
        int average = sumAttempts / amount;
        msg = "On average it took " + average + " attempts to sample a prime number!\n\n" + msg;
        msg = ("Sampling " + amount + " primes of " + n + " bits and "
                             + "a error parameter of: " + k + "\n\n") + msg;
        System.out.println(msg);
    }

    public static void main(String[] args){
        int n = 512;
        int k = 40;
        int amount = 10;
        // DuoTuple NAttempts = samplePrime(bits, k);
        // System.out.println("The Number N is " + bits + " bits long and the error parameter is " + k);
        // System.out.println(NAttempts.getX() + " is probably a prime number!");
        sampleNPrimes(n, k, amount);
    }
}
