package hw7;

import java.math.BigInteger;

import CommonFunctions.BigRandom;
import CommonFunctions.DuoTuple;
import hw6.MillerRabin;
import hw7.DHGroupSetup;

public class DHProtocol {
    /**
     * Choosing randomly a number x in the group of order q and computing g^x mod p 
     * @param p the order of the big group so we do mod p
     * @param q the order of the subgroup of quadratic residues G
     * @param g the generator of G
     * @return (x, exp) where @param x is the random element from G and @param exp is g^x mod p
     */
    public static DuoTuple chooseKeyParts(BigInteger p, BigInteger q, BigInteger g){
        //Sampling a random x from [2, q-1] because 1 is boring
        BigInteger x = BigRandom.randomRange(q.subtract(BigInteger.TWO)).add(BigInteger.TWO);
        BigInteger exp = MillerRabin.modExp(g, x, p);
        return new DuoTuple(x, exp);
    }
    /**
     * Computing the shared key of alice and bob. computing (g^a)^b mod p
     * @param exp = g^a for a generator g and a sampled number a
     * @param x the exponent b
     * @param p the order of the group
     * @return the shared key that is (g^a)^b mod p
     */
    public static BigInteger computeSharedKey(BigInteger exp, BigInteger x, BigInteger p){
        BigInteger sharedKey = MillerRabin.modExp(exp, x, p);
        return sharedKey;
    }

    public static void main(String[] args){
        int bits = 512;
        int k = 40;
        DuoTuple pq = DHGroupSetup.createPrimeDH(bits, k);
        BigInteger p = pq.getX();
        BigInteger q = pq.getY();
        BigInteger g = DHGroupSetup.findGenerator(p);
        System.out.println("p:\t" + p + "\nq:\t" + q + "\ng:\t" + g);

        DuoTuple aGa = chooseKeyParts(p, q, g);
        DuoTuple bGb = chooseKeyParts(p, q, g);
        BigInteger a, ga, b, gb;
        a = aGa.getX();
        ga = aGa.getY();
        b = bGb.getX();
        gb = bGb.getY();
        BigInteger aliceSK = computeSharedKey(gb, a, p);
        BigInteger bobSK = computeSharedKey(ga, b, p);
        System.out.println("\nAlice chooses a:\t" + a + "\ncomputes g^a:\t" + ga);
        System.out.println("\nBob chooses b:\t" + b + "\ncomputes g^b:\t" + gb);
        System.out.println("\nAlice computes (g^b)^a:\n\t" + aliceSK);
        System.out.println("\nBob computes (g^a)^b:\n\t" + bobSK);
        System.out.println("\n\nThe shared key is " + (aliceSK.equals(bobSK) ? "the same!" : "different!"));
    }
}
