package jinjie;

/*
* 题目描述：给定一个字符串，返回str中最长回文字串的长度
* 进阶：给定一个字符串str，想通过添加字符的方式使得str整体都变成回文字符串，但要求只能在str的末尾添加字符，请返回str后面添加的最短字符串
* */
public class Manacher {


    //预处理字符串，如121变成#1#2#1#，主要是为了解决奇偶数的问题
    public  static char[] manacherString(String str){
        char[] charArr=str.toCharArray();
        char[] res=new char[str.length()*2+1];
        int index=0;
        for(int i=0;i!=res.length;i++){
            res[i]=(i&1)==0?'#':charArr[index++];//i&1 可以判断i是奇数还是偶数
        }
        return res;
    }

    public static int maxLcpsLength(String str){
        if(str==null||str.length()==0){
            return 0;
        }
        char[] charArr=manacherString(str);
        int[] pArr=new int[charArr.length]; //用来存放每个位置的最长回文半径
        int index=-1; //回文中心，初始为-1
        int pR=-1;  //初始最右回文右边界为-1
        int max=Integer.MIN_VALUE;
        for(int i=0;i!=charArr.length;i++){
            //pR>i,说明i在回文边界内，则有最大回文半径的取值两个瓶颈，一个是右边界pR，一个是对称点i'的最大回文半径，即pArr[2*index-i]
            //否则，则pArr[i]=1，只扩展到自身
            pArr[i] = pR>i ? Math.min(pArr[2*index-i],pR-i) : 1;
            while (i+pArr[i]<charArr.length&&i-pArr[i]>-1){
                //有两种不用扩边界的，但是我们这里依然让其扩，不成立的话就直接break，主要是为了少些代码
                if (charArr[i + pArr[i]] == charArr[i - pArr[i]])
                    pArr[i]++;
				else {
                    break;
                }
            }
            if(i+pArr[i]>pR){ //更新pR的值
                pR=i+pArr[i];
                index=i;
            }
            max=Math.max(max,pArr[i]);
        }
        return max-1;
    }

    //在字符串最后添加最少字符，使得整个字符串都成为回文串。

    public static String shortestEnd(String str){
        if(str==null || str.length()==0){
            return null;
        }
        char[] charArr=manacherString(str);
        int[] pArr=new int[charArr.length];
        int index=-1;
        int pR=-1;
        int maxContainsEnd = -1;
        for (int i = 0; i != charArr.length; i++) {
            pArr[i] = pR > i ? Math.min(pArr[2 * index - i], pR - i) : 1;
            while (i + pArr[i] < charArr.length && i - pArr[i] > -1) {
                if (charArr[i + pArr[i]] == charArr[i - pArr[i]])
                    pArr[i]++;
                else {
                    break;
                }
            }
            if (i + pArr[i] > pR) {
                pR = i + pArr[i];
                index = i;
            }
            if (pR == charArr.length) {
                maxContainsEnd = pArr[i];
                break;
            }
        }
        char[] res = new char[str.length() - maxContainsEnd + 1];
        for (int i = 0; i < res.length; i++) {
            res[res.length - 1 - i] = charArr[i * 2 + 1];
        }
        return String.valueOf(res);
    }

    public static void main(String[] args) {
        String str1 = "abc1234321ab";
        System.out.println(maxLcpsLength(str1));

        String str2 = "abcd123321";
        System.out.println(shortestEnd(str2));
    }

}
