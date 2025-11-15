package hw3;
import CommonFunctions.*;
import hw1.Karatsuba;
import hw2.*;
import hw2.Div.*;
import hw1.Karatsuba.*;

import java.math.BigInteger;
class   TriTuple{
        BigInteger d;
        BigInteger x;
        BigInteger y;
        public TriTuple(){
            this.d = BigInteger.ZERO;
            this.x = BigInteger.ZERO;
            this.y = BigInteger.ZERO;
        }
        public TriTuple(BigInteger x, BigInteger y){
            this.x = x;
            this.y = y;
        }
        public TriTuple(BigInteger d, BigInteger x, BigInteger y){
            this.d = d;
            this.x = x;
            this.y = y;
        }
        public BigInteger getX(){
            return this.x;
        }
        public BigInteger getY(){
            return this.y;
        }
        public BigInteger getD() {
            return this.d;
        }
        public void setX(BigInteger x){
            this.x = x;
        }
        public void setY(BigInteger y){
            this.y = y;
        }
        public void setD(BigInteger d) {
            this.d = d;
        }

}
public class EuclidianInverse {
    //a>=b>=0
    public static TriTuple extendedEuclid(BigInteger a, BigInteger b){
        return recursiveExtendedEuclid(a, b);
    }
    private static TriTuple recursiveExtendedEuclid(BigInteger a, BigInteger b){
        if (b.equals(BigInteger.ZERO)){
            return new TriTuple(a, BigInteger.ONE, BigInteger.ZERO);
        }
        DoTuple qr = hw2.Div.divFunc(a, b);
        TriTuple newDXY = recursiveExtendedEuclid(b, qr.getY());
        BigInteger qMulty = Karatsuba.karatsubaMult(qr.getX(), newDXY.getY());
        BigInteger yOut = newDXY.getX().subtract(qMulty);
        return new TriTuple(newDXY.getD(), newDXY.getY(), yOut);

    }
}
