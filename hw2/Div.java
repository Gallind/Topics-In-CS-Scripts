package hw2;
import java.util.ArrayList;
import java.math.BigInteger;
import java.util.Random;
import CommonFunctions.*;


public class Div {
    public static DuoTuple divFunc(BigInteger x, BigInteger y){
        if (x.compareTo(BigInteger.ZERO) >= 0) return recursiveDivFunc(x, y);

        DuoTuple qr = recursiveDivFunc(x.abs(), y);
        BigInteger q = qr.getX();
        BigInteger r = qr.getY();

        if (r.equals(BigInteger.ZERO)) return new DuoTuple(q.negate(), r);

        BigInteger newQ = q.add(BigInteger.ONE).negate();
        BigInteger newR = y.subtract(r);
        return new DuoTuple(newQ, newR);
    }
    private static DuoTuple recursiveDivFunc(BigInteger x, BigInteger y){
        if (x.equals(BigInteger.ZERO)) return new DuoTuple();
        BigInteger halfX = x.shiftRight(1);
        DuoTuple qr = recursiveDivFunc(halfX, y);
        BigInteger q = qr.getX();
        BigInteger r = qr.getY();
        q = q.shiftLeft(1);
        r = r.shiftLeft(1);
        if (x.mod(BigInteger.TWO).equals(BigInteger.ONE)) r = r.add(BigInteger.ONE);
        if (r.compareTo(y) >= 0){
            r = r.subtract(y);
            q = q.add(BigInteger.ONE);
        }
        qr.setX(q);
        qr.setY(r);
        return qr;
    }


    public static void main(String[] args) {
        int bits = 8;
        System.out.println("Testing Div of number with "+ bits + " bits:");
        BigInteger randNum1 = CommonFunctions.BigRandom.randomBitNumber(bits);
        System.out.println("X:                               " + randNum1);
        //BigInteger randNum2 = CommonFunctions.BigRandom.randomBitNumber(bits);
        BigInteger randNum2 = new BigInteger("23");
        System.out.println("Y:                               " + randNum2);
        System.out.println("Computing X=qY+r...");
        DuoTuple result = divFunc(randNum1, randNum2);
        System.out.println("q = " + result.getX());
        System.out.println("r = " + result.getY());
    }
}
