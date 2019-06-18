package jinjie;

/*
题目描述：给定两个字符串str和match，长度分别是N和M。实现一个算法，如果字符串str中含有字串match，则返回match在str中的开始位置，否则返回-1.

解法：
可以分为两个步骤：获得match字符串的next数组过程+str和match数组比较的过程
说明一下最长前缀和最长后缀的要求：最长前缀必须是从0开始，最长后缀的最后一位吧必须是当前位的前一位
next[]返回的值，不仅是最长前缀字串和后缀字串的匹配值，也是match数组从左往右匹配到哪了
 */
public class KMP {
    public static void main(String[] args) {
        String str = "abcabcababaccc";
        String match = "ababa";
        System.out.println(getIndexOf(str, match));
    }

    public static int getIndexOf(String s, String m){
        if(s==null||m==null||m.length()<1||s.length()<m.length()){
            return -1;
        }

        char[] ss=s.toCharArray();
        char[] ms=m.toCharArray();

        int i1=0;//索引
        int i2=0;

        int[] next=getNextArray(ms);//获得match数组的next数组

        while(i1<ss.length && i2<ms.length){
            if(ss[i1]==ms[i2]){
                i1++;
                i2++;
            }else {
                if (next[i2] == -1) {
                    i1++; //next[i2]的值为-1，说明match的第一个字符串就不相等
                } else {
                    i2 = next[i2];//往前跳到next[i2]的位置，再讲ss[[i1]和msi2]进行比较
                }
            }
        }
        return i2==ms.length?i1-i2:-1;
    }

    //获得字符串match的next数组
    public static int[] getNextArray(char[] ms) {
        if (ms.length == 1) {
            return new int[]{-1};//next[0]=-1
        }

        int[] next = new int[ms.length];//建立一个与ms等长的数组，每个里面存储最长前缀和后缀的匹配长度
        next[0] = -1;
        next[1] = 0;
        int pos = 2; //最长后缀字串的右边界
        int cn = 0; //最长前缀子串长度，也是索引
        while (pos < next.length) {
            if (ms[pos - 1] == ms[cn]) {
                next[pos++] = ++cn; //next[pos]=cn+1;pos=pos+1;cn=cn+1
            } else {//如果不相等，继续往前跳，两种情况，cn<=0,则说明不可以跳了，说明没有匹配到，即next[pos++]=0;否则继续往前跳
                if (cn > 0) {
                    cn = next[cn];//此时将next[cn]理解成位置，即前缀字串的下一位。cn跳到该位置，继续比较ms[pos - 1]和 ms[cn]是否相等
                } else {
                    next[pos++] = 0;
                }
            }
        }
        return next;
    }


}
