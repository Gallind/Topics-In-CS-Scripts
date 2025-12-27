package hw8;

import java.math.BigInteger;

import CommonFunctions.BigRandom;
import CommonFunctions.DuoTuple;
import CommonFunctions.TriTuple;
import hw3.ModularInverse;
import hw6.MillerRabin;
import hw6.SamplePrime;

public class RSA {

    public static class RSAKeys{
        public BigInteger N;
        public BigInteger e;
        public BigInteger d;
        public BigInteger p;
        public BigInteger q;
        public RSAKeys(BigInteger N, BigInteger e, BigInteger d, BigInteger p, BigInteger q){
            this.N = N;
            this.e = e;
            this.d = d;
            this.p = p;
            this.q = q;
        }
    }
    
    /**
     * Generating keys for RSA
     * @param n number of bits on primes p, q
     * @param e the public key
     * @param k the error parameter
     * @return (N, e, d, p, q) where N = p*q, e - public key, d - private key, p&q - primes of @param n bits
     */
    public static RSAKeys generateKeys(int n, BigInteger e, int k){
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

        return new RSAKeys(N, e, d, p, q);
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

    public static BigInteger decryptCRT(BigInteger c, BigInteger d, BigInteger p, BigInteger q){
        BigInteger N = p.multiply(q);

        // d mod (p-1)     d mod (q-1)
        BigInteger dp = d.mod(p.subtract(BigInteger.ONE));
        BigInteger dq = d.mod(q.subtract(BigInteger.ONE));

        //qInv = q^-1 mod p      pInv = p^-1 mod q
        BigInteger qInv = ModularInverse.modInv(q, p);
        BigInteger pInv = ModularInverse.modInv(p, q);

        //mp = c^dp mod p          mq = c^dq mod q
        BigInteger mp = MillerRabin.modExp(c, dp, p);
        BigInteger mq = MillerRabin.modExp(c, dq, q);

        // mp * q * q^-1           mq * p * p^-1
        BigInteger term1 = mp.multiply(q).multiply(qInv);
        BigInteger term2 = mq.multiply(p).multiply(pInv);

        return term1.add(term2).mod(N);
    }




    public static void main(String[] args){
        int bits = 5;
        int k = 5;
        BigInteger e = new BigInteger("3");
        System.out.println("Generating RSA keys using " + bits + " bits primes,\n" +
                            "e = " + e);
        RSAKeys keys = generateKeys(bits, e, k);
        BigInteger N = keys.N;
        BigInteger pk = keys.e;
        BigInteger sk = keys.d;

        System.out.println("N:\t" + N);
        System.out.println("public key:\t" + pk);
        System.out.println("secret key:\t" + sk);

        BigInteger m = BigRandom.randomRange(N);
        System.out.println("The message is m:\t" + m);

        BigInteger c = encrypt(m, pk, N);
        System.out.println("The encrypted message is:\t" + c);
    }

}
