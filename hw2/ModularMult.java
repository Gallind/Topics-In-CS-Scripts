package hw2;
import CommonFunctions.*;
import hw1.Karatsuba;
import hw1.Karatsuba.*;

import java.math.BigInteger;

public class ModularMult {


    public static BigInteger modMult(BigInteger x, BigInteger y, BigInteger N){
        BigInteger z = Karatsuba.karatsubaMult(x, y);
        DuoTuple qr = hw2.Div.divFunc(z, N);
        return qr.getY();
    }
    public static void main(String[] args) {
        int bits = 512;
        System.out.println("Testing ModMult of a number N with "+ bits + " bits:");
        BigInteger N = CommonFunctions.BigRandom.randomBitNumber(bits);
        System.out.println("N:                               " + N);
        //BigInteger randNum2 = CommonFunctions.BigRandom.randomBitNumber(bits);
        BigInteger x = BigRandom.randomRange(N);
        System.out.println("x:                               " + x);
        BigInteger y = BigRandom.randomRange(N);
        System.out.println("y:                               " + y);
        System.out.println("Computing z=qN+r...");
        BigInteger result = modMult(x, y, N);
        System.out.println("r = " + result);
    }
}
