package hw7;

import java.math.BigInteger;
import CommonFunctions.BigRandom;
import CommonFunctions.DuoTuple;
import hw6.SamplePrime;
import hw6.MillerRabin;


public class DHGroupSetup {

    /**
     * Creating a prime p of the form p = 2q + 1
     * when q is a prime
     * @param bits number of bits of q
     * @param k the error parameter for sampling a prime number
     * @return (p, q) such that p=2q+1 is a big prime
     */
    public static DuoTuple createPrimeDH(int bits, int k){
        BigInteger p, q;
        while (true) {
            q = BigRandom.randomOddBitNumber(bits);

            //checking q against a list of small known primes first
            if (!SamplePrime.checkSmallPrimes(q)) continue;

            BigInteger qMod3 = q.mod(new BigInteger("3"));
            if (!qMod3.equals(BigInteger.TWO)) continue; //else q === 1 mod 3 --> p = 2q + 1 === 0 mod 3

            p = q.shiftLeft(1).add(BigInteger.ONE);
            if (!SamplePrime.checkSmallPrimes(p)) continue;

            if (isStrongPrime(q, p, k)) return new DuoTuple(p, q);
        }
    }
    /**
     * Checking if q and p are primes with error parameter k
     * @param q
     * @param p
     * @param k error parameter
     * @return true if both q, p are primes
     */
    private static boolean isStrongPrime(BigInteger q, BigInteger p, int k){
        //fast checking both numbers for faster results
        if (!SamplePrime.primality(q, 1)) return false;
        if (!SamplePrime.primality(p, 1)) return false;
        //if first step passed, checking with the big guns
        return SamplePrime.primality(q, k) && SamplePrime.primality(p, k);
    }

    /**
     * Finding the generator g of the subgroup G of quadratic residues of Z_p
     * the order of this subgroup is q ((p-1)/2) so all elements in this subgroups that aren't 1 are generators
     * the function eliminates numbers of certain orders (1, 2, 2q) so only q is left
     * @param p the big prime number of the cyclic group Z_p
     * @return a random generator of the subgroup of quadratic residues in which its order is q
     */
    public static BigInteger findGenerator(BigInteger p){
        // BigInteger q = p.subtract(BigInteger.ONE).shiftRight(1);
        BigInteger x, g;
        while (true) {
            //sampling a random x from [2, p-2]
            x = BigRandom.randomRange(p.subtract(new BigInteger("3"))).add(BigInteger.TWO);
            g = MillerRabin.modExp(x, BigInteger.TWO, p); //g=x^2 so g is in G
            //all elements in G except 1 are generators
            if (!g.equals(BigInteger.ONE)) return g;
        }

    }
}
