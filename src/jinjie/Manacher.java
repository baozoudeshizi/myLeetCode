package jinjie;

/*
* 题目描述：给定一个字符串，返回str中最长回文字串的长度
* 进阶：给定一个字符串str，想通过添加字符的方式使得str整体都变成回文字符串，但要求只能在str的末尾添加字符，请返回str后面添加的最短字符串
* */
public class Manacher {


    //预处理字符串，如121变成#1#2#1#，主要是为了解决奇偶数的问题
    public char[] manacherString(String str){
        char[] charArr=str.toCharArray();
        char[] res=new char[str.length()*2+1];
        int index=0;
        for(int i=0;i!=res.length;i++){
            res[i]=(i&1)==0?'#':charArr[index++];//i&1 可以判断i是奇数还是偶数
        }
        return res;
    }

    public int maxLcpsLenth(String str){
        if(str==null||str.length()==0){
            return 0;
        }
        char[] charArr=manacherString(str);
        int[] pArr=new int[charArr.length];
        int index=-1;
        int pR=-1;
        int max=Integer.MIN_VALUE;
        for(int i=0;i!=charArr.length;i++){
            pArr[i] = pR>i ? Math.min(pArr[2*index-i],pR-i) : 1;
            while (i+pArr[i]<charArr.length&&i-pArr[i]>-1){

            }
        }
        return 0;
    }


}
