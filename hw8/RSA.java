package hw8;

import java.math.BigInteger;

import CommonFunctions.DuoTuple;
import CommonFunctions.TriTuple;
import hw3.ModularInverse;
import hw6.MillerRabin;
import hw6.SamplePrime;

public class RSA {
    
    /**
     * Generating keys for RSA
     * @param n number of bits on primes p, q
     * @param e the public key
     * @param k the error parameter
     * @return (N, e, d) where N = p*q, e - public key, d - private key
     */
    public static TriTuple generateKeys(int n, BigInteger e, int k){
        BigInteger p, q;
        
        while (true) {
            DuoTuple pDT = SamplePrime.samplePrime(n, k);
            p = pDT.getX();
            //checks if (p-1) % e == 0
            if (!p.subtract(BigInteger.ONE).mod(e).equals(BigInteger.ZERO)) break;
        }
        while (true) {
            DuoTuple qDT = SamplePrime.samplePrime(n, k);
            q = qDT.getX();
            //checks if p != q and (q-1) % e == 0
            if (!p.equals(q) && !q.subtract(BigInteger.ONE).mod(e).equals(BigInteger.ZERO)) break;
        }
        System.out.println("p:\t" + p + "\nq:\t" + q);

        BigInteger N = p.multiply(q);
        //phi = (p-1)*(q-1)
        BigInteger phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
        System.out.println("phi:\t" + phi);
        BigInteger d = ModularInverse.modInv(e, phi);
        if (d.compareTo(BigInteger.ZERO) < 0) d = d.add(phi);

        return new TriTuple(N, e, d);
    }

    /**
     * Encrypting the message m using the public key pk in the group N
     * @param m the original message
     * @param pk the public key
     * @param N the group where N = pq for some primes p, q
     * @return the encrypted message
     */
    public static BigInteger encrypt(BigInteger m, BigInteger pk, BigInteger N){
        return MillerRabin.modExp(m, pk, N);
    }

    public static BigInteger decryptCRT(BigInteger c, BigInteger d){

        return BigInteger.ZERO;
    }




    public static void main(String[] args){
        int bits = 6;
        int k = 5;
        BigInteger e = new BigInteger("3");

        System.out.println("Generating RSA keys using " + bits + " bits primes,\n" +
                            "e = " + e);
        TriTuple keys = generateKeys(bits, e, k);
        BigInteger N = keys.getD();
        BigInteger pk = keys.getX();
        BigInteger sk = keys.getY();

        System.out.println("N:\t" + N);
        System.out.println("public key:\t" + pk);
        System.out.println("secret key:\t" + sk);
    }

}
