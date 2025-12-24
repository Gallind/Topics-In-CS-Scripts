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
            q = SamplePrime.samplePrime(bits, k).getX();
            p = q.shiftLeft(1).add(BigInteger.ONE);
            if(SamplePrime.primality(p, k)){
                return new DuoTuple(p, q);
            }
        }
    }
    /**
     * Finding the generator g of the subgroup G of quadratic residues of Z_p
     * the order of this subgroup is q ((p-1)/2) so all elements in this subgroups that aren't 1 are generators
     * the function eliminates numbers of certain orders (1, 2, 2q) so only q is left
     * @param p the big prime number of the cyclic group Z_p
     * @return a random generator of the subgroup of quadratic residues in which its order is q
     */
    public static BigInteger findGenerator(BigInteger p){
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
