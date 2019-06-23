package novice.matrix;

/**
 * 给定一个矩阵matRix，按照“之”字形的方式打印这 个矩阵，
 * 例如:1，2，3，4; 5，6，7，8; 9，10，11，12
 * “之”字形打印的结果为:1，2，5，9，6，3，4，7，10，11， 8，12
 *
 * 思路：不能局限在局部，假设有两个指针A，B。A一直往右走，直到最右边，转向下；B一直往下走，到最下后往右。
 *      连接A，B，发现要打印的路径就是AB的连线，但是还要设置一个bool值，确定是往下还是往上。
 */
public class ZigZagPrintMatrix {
    public static void printMatrixZigZag(int[][] matrix){
        int aR = 0; //A，即右上角的行号和列号
        int aC = 0;
        int bR = 0; //B，即左下角的行号和列号
        int bC = 0;

        int endR = matrix.length-1;
        int endC = matrix[0].length-1;
        boolean fromUp = false;

        //如果A点没有到达最后行，其实这里隐藏一个判断，A到最后一行，必然之前到了最后一列
        //也可以写成B来到最后一列
        while (aR != endR+1){
            printLevel(matrix,aR,aC,bR,bC,fromUp);
            aR = aC == endC ? aR+1 : aR; //如果A点来到最后一列，A的行数+1，否则还在第一行
            aC = aC == endC ? aC : aC+1;
            bC = bR == endR ? bC + 1 : bC;//如果B点来带最后一行，B的行数+1，否则还在第一列
            bR = bR == endR ? bR : bR + 1;
            fromUp = !fromUp;
        }
        System.out.println();
    }

    //这个函数负责打印判断方向，并打印AB还是BA连线
    public static void printLevel(int[][] m, int aR, int aC, int bR, int bC, boolean f){
        if(f){//从右上角到左下角
            while (aR<=bR){//aR!=bR+1
                System.out.println(m[aR++][aC--]+" ");
            }
        }else{//从左上角到右下角
            while(bR>=aR){//bR!=aR-1
                System.out.println(m[bR--][bC++]+" ");
            }
        }
    }
    public static void main(String[] args) {
        int[][] matrix = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 } };
        printMatrixZigZag(matrix);

    }
}
