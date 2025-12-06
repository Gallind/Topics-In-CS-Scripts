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
    /**
     * 
     * @param a
     * @param u
     * @param r Make sure to send the int version!!
     * @param N
     * @return True if N is probably a prime, False if N is for sure a composite
     */
    public static boolean primalityTest(BigInteger a, BigInteger u, int r, BigInteger N){
        //TODO
        BigInteger lastElement = modExp(a, N.subtract(BigInteger.ONE), N);
        if (!lastElement.equals(BigInteger.ONE)) return false;

        BigInteger x = modExp(a, u, N);
        if (x.equals(BigInteger.ONE) || x.equals(N.subtract(BigInteger.ONE))) //checking first MR element
            return true;
        
        for (int i = 0; i < r - 1; i++){
            x = hw2.ModularMult.modMult(x, x, N);
            if (x.equals(N.subtract(BigInteger.ONE))) return true;
            if (x.equals(BigInteger.ONE)) return false;
        }

        return false;
    }


    public static void main(String[] args) {
        int bits = 5;
        BigInteger N = BigRandom.randomOddBitNumber(bits);
        BigInteger a = BigRandom.randomRange(N);
        System.out.println("N:\t\t" + N + "\na:\t\t" + a);
        //System.out.println("N - 1 = (2^r)*u");
        DuoTuple ur = compute_UR(N);
        BigInteger u = ur.getX();
        BigInteger r = ur.getY();
        System.out.println("u = \t\t" + u +"\nr = \t\t" + r);
        System.out.println(N.subtract(BigInteger.ONE) + " = (2^" + r + ") * " + u);
        //System.out.println(modExp(ur.getX(), ur.getY(), N));

        System.out.println(N + " is a " + ((primalityTest(a, u, r.intValue(), N)) ? "prime" : "composite"));
    }
}
