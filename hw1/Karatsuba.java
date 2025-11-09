package hw1;
import java.math.BigInteger;
import java.util.Random;
import CommonFunctions.*;

public class Karatsuba {

    private static int simpleMult = 4096;
    public static BigInteger karatsubaMult(BigInteger x, BigInteger y){

        BigInteger result = recursiveKaraMult(x, y);
        return result;
    }
    private static BigInteger recursiveKaraMult(BigInteger x, BigInteger y) {
        int maxBitLen = Math.max(x.bitLength(), y.bitLength());
        if (maxBitLen <= simpleMult) return x.multiply(y);

        int halfBitLen = (maxBitLen + 1) / 2;
        BigInteger halfMaxNum = BigInteger.ONE.shiftLeft(halfBitLen);

        BigInteger[] xAB = x.divideAndRemainder(halfMaxNum);
        BigInteger[] yCD = y.divideAndRemainder(halfMaxNum);

        BigInteger a = xAB[0], b = xAB[1];
        BigInteger c = yCD[0], d = yCD[1];

        BigInteger A1 = recursiveKaraMult(a, c);
        BigInteger A2 = recursiveKaraMult(b, d);
        BigInteger A3 = recursiveKaraMult(a.add(b), c.add(d));//Change the add method

        BigInteger B1 = A1.shiftLeft(halfBitLen * 2);
        BigInteger B2 = (A3.subtract(A1).subtract(A2)).shiftLeft(halfBitLen * 2);
        BigInteger B3 = A2;

        BigInteger result = B1.add(B2).add(B3);
        return result;
    }

    public static void main(String[] args){
        int bits = 512;
        System.out.println("Multiplying 2 " + bits + "-bit numbers");
        BigInteger x = CommonFunctions.BigRandom.randomBitNumber(bits);
        System.out.println("Num1: " + x);
        BigInteger y = CommonFunctions.BigRandom.randomBitNumber(bits);
        System.out.println("Num2: " + y);

        BigInteger karaResult = karatsubaMult(x, y);
        System.out.println("The Karatsuba multiplication is:      " + karaResult);

        BigInteger NormalResult = x.multiply(y);
        System.out.println("The default java's multiplication is: " + NormalResult);

        boolean same = karaResult.equals(NormalResult);
        if (same) System.out.println("Success");
        else System.out.println("Fail");
    }
}