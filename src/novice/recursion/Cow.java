package novice.recursion;

/*
* 题目描述：牛三年成熟，每年可以生一个，问若干年后，总共有多少个
*
* 思路：前三年，只有最开始的母牛能生，剩下的还未成熟，所以都是1； 三年还有，发现当年的牛f(n)，应该是去年的牛f(n-1)加上新生牛，也就是在去年成熟达到成熟的牛的个数，也就是f(n-3)
* */
public class Cow {

    public static int cowNumber1(int n){
        if(n<1){
            return 0;
        }
        if(n==1||n==2||n==3){
            return n;
        }
        return cowNumber1(n-1)+cowNumber1(n-3);
    }

    public static void main(String[] args){
        int n=20;
        System.out.println(cowNumber1(n));
    }
}
