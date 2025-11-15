package hw3;
import CommonFunctions.*;
import hw1.Karatsuba;
import hw2.*;
import hw2.Div.*;
import hw1.Karatsuba.*;

import java.math.BigInteger;

public class EuclidianInverse {
    //a>=b>=0
    public static TriTuple extendedEuclid(BigInteger a, BigInteger b){
        return recursiveExtendedEuclid(a, b);
    }
    private static TriTuple recursiveExtendedEuclid(BigInteger a, BigInteger b){
        if (b.equals(BigInteger.ZERO)){
            return new TriTuple(a, BigInteger.ONE, BigInteger.ZERO);
        }
        DuoTuple qr = hw2.Div.divFunc(a, b);
        TriTuple newDXY = recursiveExtendedEuclid(b, qr.getY());
        BigInteger qMultY = Karatsuba.karatsubaMult(qr.getX(), newDXY.getY());
        BigInteger yOut = newDXY.getX().subtract(qMultY);
        return new TriTuple(newDXY.getD(), newDXY.getY(), yOut);
    }
    public static void main(String[] args) {
        int bits = 256;
        System.out.println("Testing Extended Euclidian algorithm of 2 numbers with "+ bits + " bits:");
        BigInteger a = CommonFunctions.BigRandom.randomBitNumber(bits);
        System.out.println("a:                               " + a);
        BigInteger b = CommonFunctions.BigRandom.randomRange(a);
        System.out.println("b:                               " + b);
        System.out.println("Computing (d,x,y) such that d = gcd(a,b) = ax + by...");
        TriTuple result = extendedEuclid(a, b);
        System.out.println("d = " + result.getD());
        System.out.println("x = " + result.getX());
        System.out.println("y = " + result.getY());
    }
}
