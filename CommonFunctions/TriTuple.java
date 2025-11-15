package CommonFunctions;

import java.math.BigInteger;

public class TriTuple {
        BigInteger d;
        BigInteger x;
        BigInteger y;
        public TriTuple(){
            this.d = BigInteger.ZERO;
            this.x = BigInteger.ZERO;
            this.y = BigInteger.ZERO;
        }
        public TriTuple(BigInteger x, BigInteger y){
            this.d = BigInteger.ZERO;
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
