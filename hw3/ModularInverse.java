package hw3;

import CommonFunctions.DuoTuple;
import CommonFunctions.TriTuple;
import hw2.Div;

import java.math.BigInteger;

public class ModularInverse {
    public static BigInteger modInv(BigInteger a, BigInteger N){
        TriTuple gcdDXY = EuclidianInverse.extendedEuclid(a.add(N), N);
        BigInteger gcd = gcdDXY.getD();
        if (!gcd.equals(BigInteger.ONE)) return BigInteger.ZERO.subtract(BigInteger.ONE);//return -1
        DuoTuple qr = Div.divFunc(gcdDXY.getX(), N);
        return qr.getY();
    }
    public static void main(String[] args) {
        int bits = 5;
        System.out.println("Testing Modular Inverse of a number in a field of max "+ bits + " bits:");
        //BigInteger N = CommonFunctions.BigRandom.randomBitNumber(bits);
        BigInteger N = new BigInteger("999331");
        System.out.println("N:                               " + N);
        //BigInteger a = CommonFunctions.BigRandom.randomRange(N);
        BigInteger a = new BigInteger("1234");
        System.out.println("a:                               " + a);
        System.out.println("Computing a^-1 in ZN...");
        BigInteger result = modInv(a, N);
        if (result.compareTo(BigInteger.ZERO) < 0){
            System.out.println("a doesn't have a modular inverse in Zn");
        }
        else {
            System.out.println("a^-1 = " + result);
        }
    }
}
