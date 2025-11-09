package hw2;
import java.util.ArrayList;
import java.math.BigInteger;
import java.util.Random;
import CommonFunctions.*;


class BigTuple{
    BigInteger x;
    BigInteger y;
    public BigTuple(){
        this.x = BigInteger.ZERO;
        this.y = BigInteger.ZERO;
    }
    public BigTuple(BigInteger x, BigInteger y){
        this.x = x;
        this.y = y;
    }
    public BigInteger getX(){
        return this.x;
    }
    public BigInteger getY(){
        return this.y;
    }
    public void setX(BigInteger x){
        this.x = x;
    }
    public void setY(BigInteger y){
        this.y = y;
    }
}


public class Div {
    public static BigTuple divFunc(BigInteger x, BigInteger y){
        return recursiveDivFunc(x, y);
    }
    private static BigTuple recursiveDivFunc(BigInteger x, BigInteger y){
        if (x.equals(BigInteger.ZERO)) return new BigTuple();
        BigInteger halfX = x.shiftRight(1);
        BigTuple qr = recursiveDivFunc(halfX, y);
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
        BigTuple result = divFunc(randNum1, randNum2);
        System.out.println("q = " + result.getX());
        System.out.println("r = " + result.getY());
    }
}
