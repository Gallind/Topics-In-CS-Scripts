package CommonFunctions;

import java.math.BigInteger;
import java.util.Random;

public class BigRandom {


    public static BigInteger randomBitNumber(int bits){
        Random random = new Random();
        BigInteger msbOne = BigInteger.ONE.shiftLeft(bits - 1);
        BigInteger randPart = new BigInteger(bits - 1, random);
        return msbOne.add(randPart);
    }
    public static BigInteger randomRange(BigInteger N){
        Random random = new Random();
        int bits = N.bitLength();
        BigInteger result = new BigInteger(bits, random);
        while (result.compareTo(N) >= 0){
            result = new BigInteger(bits, random);
        }
        return result;
    }
}
