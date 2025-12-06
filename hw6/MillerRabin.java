package hw6;

import java.math.BigInteger;


import CommonFunctions.*;

public class MillerRabin {

    /**
     * 
     * @param N
     * @return (u, r) when N - 1 = (2^r)*u and u is odd
     */
    private static DuoTuple compute_UR(BigInteger N){
        if (N.mod(BigInteger.TWO) == BigInteger.ZERO) return new DuoTuple();
        N  = N.subtract(BigInteger.ONE);
        BigInteger r = BigInteger.ZERO;
        while (N.mod(BigInteger.TWO) == BigInteger.ZERO){
            r = r.add(BigInteger.ONE);
            N = N.shiftRight(1);
        }
        return new DuoTuple(N, r);
    }

    private static BigInteger modExp(BigInteger base, BigInteger exp, BigInteger N){
        base = base.mod(N);//making sure
        BigInteger result = BigInteger.ONE;

        while (exp.compareTo(BigInteger.ZERO) > 0) {
            if (exp.mod(BigInteger.TWO).equals(BigInteger.ONE))//Multiplying only by the 1 bits in exp
                result = hw2.ModularMult.modMult(result, base, N);
            base = hw2.ModularMult.modMult(base, base, N);//base^2 mod N
            exp = exp.shiftRight(1);
        }
        return result;
    }

    public static boolean primalityTest(BigInteger a, BigInteger u, BigInteger r, BigInteger N){
        //TODO
        return false;
    }


    public static void main(String[] args) {
        int bits = 8;
        BigInteger N = BigRandom.randomOddBitNumber(bits);
        BigInteger a = BigRandom.randomRange(N);
        System.out.println("N:\t\t" + N);
        System.out.println("N - 1 = (2^r)*u");
        DuoTuple ur = compute_UR(N);
        System.out.println("u = \t\t" + ur.getX() +"\nr = \t\t" + ur.getY());
        System.out.println(N.subtract(BigInteger.ONE) + " = (2^" + ur.getY() + ") * " + ur.getX());
        System.out.println(modExp(ur.getX(), ur.getY(), N));
    }
}
