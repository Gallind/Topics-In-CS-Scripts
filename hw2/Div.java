package hw2;
import java.util.ArrayList;
import java.math.BigInteger;
import java.util.Random;

public class Div {

    private static BigInteger findLargestFib(BigInteger num){
        BigInteger a = BigInteger.ZERO, b = BigInteger.ONE;
        if (num.compareTo(BigInteger.ZERO) <= 0) return BigInteger.ZERO;

        while (!(a.add(b).compareTo(num) > 0)){
            BigInteger temp = b;
            b = a.add(b);
            a = temp;
        }
        return b;
    }

    private static ArrayList<BigInteger> zeckendorfRepresentation(BigInteger num){
        ArrayList<BigInteger> zeckRep = new ArrayList<>();
        if (num.compareTo(BigInteger.ZERO) <= 0) return zeckRep;

        while (num.compareTo(BigInteger.ZERO) > 0){
            BigInteger largestFib = findLargestFib(num);
            num = num.subtract(largestFib);
            zeckRep.add(largestFib);
        }
        return zeckRep;
    }

    private static BigInteger randomBitNumber(int bits){
        Random random = new Random();
        BigInteger msbOne = BigInteger.ONE.shiftLeft(bits - 1);
        BigInteger randPart = new BigInteger(bits - 1, random);
        return msbOne.add(randPart);
    }

    public static void main(String[] args) {
        int bits = 8;
        BigInteger randNum1 = randomBitNumber(bits);
        System.out.println("Num:                               " + randNum1);
        ArrayList<BigInteger> zeckRep3 = zeckendorfRepresentation(randNum1);
        System.out.println("Its Zeckendorf representation is: " + zeckRep3.toString());
    }
}
