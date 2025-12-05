package CommonFunctions;

import java.math.BigInteger;
import java.util.Random;

public class BigRandom {

    /**
     * 
     * @param bits
     * @return a random BigInteger with @param bits bits
     */
    public static BigInteger randomBitNumber(int bits){
        Random random = new Random();
        BigInteger msbOne = BigInteger.ONE.shiftLeft(bits - 1);
        BigInteger randPart = new BigInteger(bits - 1, random);
        return msbOne.add(randPart);
    }
    /**
     * returns a BigInteger in range of 0...N-1
     * @param N
     * @return
     */
    public static BigInteger randomRange(BigInteger N){
        Random random = new Random();
        int bits = N.bitLength();
        BigInteger result = new BigInteger(bits, random);
        while (result.compareTo(N) >= 0){
            result = new BigInteger(bits, random);
        }
        return result;
    }
    /**
     * returns a random BigInteger with @param bits bits
     * @param bits
     * @return
     */
    public static BigInteger randomOddBitNumber(int bits){
        BigInteger num = randomBitNumber(bits);
        while (num.mod(BigInteger.TWO) == BigInteger.ZERO) {
            num = randomBitNumber(bits);
        }
        return num;
    }
}
