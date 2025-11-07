import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.math.BigInteger;
import java.util.Random;

public class Zeckendorf {
    //public static void main(String args[]) {}


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

    public static void main(String[] args){
        String bigNum1 = "15999999999999999999999999999999999999999999999";
        BigInteger Num1 = new BigInteger(bigNum1);

        String bigNum2 = "400000000000000000000000000000";
        BigInteger Num2 = new BigInteger(bigNum2);

        int bits = 80;
        BigInteger randNum1 = randomBitNumber(bits);
        System.out.println("Num: " + randNum1);
        ArrayList<BigInteger> zeckRep3 = zeckendorfRepresentation(randNum1);
        System.out.println("It's Zeckendorf representation is: " + zeckRep3.toString());

//        System.out.println(findLargestFib(Num1));
//        ArrayList<BigInteger> zeckRep1 = zeckendorfRepresentation(Num1);
//        System.out.println(zeckRep1.toString());

//        System.out.println(findLargestFib(Num2));
//        ArrayList<BigInteger> zeckRep2 = zeckendorfRepresentation(Num2);
//        System.out.println(zeckRep2.toString());
    }
}
