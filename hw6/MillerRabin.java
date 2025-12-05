package hw6;

import java.math.BigInteger;


import CommonFunctions.*;

public class MillerRabin {

    /*
    Make sure that N is odd
    */
    private static DuoTuple compute_UR(BigInteger N){
        if (N.mod(BigInteger.TWO) == BigInteger.ZERO) return new DuoTuple();
        N  = N.subtract(BigInteger.ONE);
        BigInteger r = BigInteger.ZERO;
        while (N.mod(BigInteger.TWO) == BigInteger.ZERO){
            r = r.shiftLeft(1);
            N = N.shiftRight(1);
        }
        return new DuoTuple(N, r);
    }



    public static void main(String[] args) {
        BigInteger N = BigRandom.randomOddBitNumber(100);
        BigInteger a = BigRandom.randomRange(N);
        System.out.println(N);
    }
}
