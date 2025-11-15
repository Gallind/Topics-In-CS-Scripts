package CommonFunctions;

import java.math.BigInteger;

public class DuoTuple {
        BigInteger x;
        BigInteger y;
        public DuoTuple(){
            this.x = BigInteger.ZERO;
            this.y = BigInteger.ZERO;
        }
        public DuoTuple(BigInteger x, BigInteger y){
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

