package novice.recursion;

/**
 * 汉诺塔问题，核心就是递归
 *
 */

public class Hanoi {
    public static void hanoi(int n){
        if(n>0){
            //func(n,n,"left","mid","right");
            process(n,"left","mid","right");
        }
    }

    //核心代码只有这么一段，高度抽象，将N-1作为一个整体
    public static void process(int N,String from,String to, String help){
        if(N==1){
            System.out.println("Move 1 from "+ from +" to "+to);
        }
        else{
            process(N-1,from, help, to);//表示将N-1的规模，从from已到help上
            System.out.println("Move "+N +" from "+from +" to "+to);//再将N从from挪到to上
            process(N-1, help, to, from);//最后将help上的N-1挪到to上
        }
    }

//    public static void func(int rest, int down, String from, String help, String to){
//        if(rest==1){
//            System.out.println("move "+ down+" from "+ from+ " to "+to);
//        }else{
//            func(rest-1,down-1,from,to,help);
//            func(1,down,from,help,to);
//            func(rest-1,down-1,help,from,to);
//        }
//    }

    public static void moveLeftToRight(int N){
        if(N==1){
            System.out.println("move 1 from left to right");
        }
        moveLeftToMid(N-1);
        System.out.println("move "+N+ "from left to right");
        moveMidToRight(N-1);
    }

    public static void moveRightToLeft(int N){

    }

    public static void moveLeftToMid(int N){
        if(N==1){
            System.out.println("move 1 from left to mid");
        }
        moveLeftToRight(N-1);
        System.out.println("move "+N+ "from left to right");
        moveRightToMid(N-1);
    }

    public static void moveMidToLeft(int N){

    }

    public static void moveRightToMid(int N){

    }

    public static void moveMidToRight(int N){
        if(N==1){
            System.out.println("move 1 from mid to right");
        }
        moveMidToLeft(N-1);
        System.out.println("move "+ N +"from mid to right");
        moveLeftToRight(N-1);
    }

    public static void main(String[] args){
        int n=5;
        hanoi(n);
    }



}
