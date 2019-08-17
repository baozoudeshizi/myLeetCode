package Sword;

public class ´ÕÇ® {
    public static void main(String[] args){
        int a=5;
        System.out.println( Count(a));

    }

    public static int Count(int num){
        if(num<1){
            return 0;
        }
        if(num==1)
            return 1;
        return Count(num-10)+Count(num-5)+Count(num-2)+Count(num-1);
    }

}
