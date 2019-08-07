package jinjie;

import java.util.*;

public class test {
    public static void main(String[] args){
        String s1=new String("Hello");
        String s2="Hello";
        String s3=new String("Hello");
        String s4="Hello";
        System.out.println(s1==s3);
        System.out.println(s2==s4);
        System.out.println(s1==s2);
        System.out.println(s1.equals(s2));
        //System.out.println("aaa");
    }

}
