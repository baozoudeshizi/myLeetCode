package novice.matrix;

import java.net.SocketPermission;
import java.sql.SQLSyntaxErrorException;

/**
 * 转圈打印矩阵
 * 【题目】 给定一个整型矩阵matrix，请按照转圈的方式打印它。 例如:1 2 3 45 6 7 89 10 11 12 13 14 15 16
 * 打印结果为:1，2，3，4，8，12，16，15，14，13，9， 5，6，7，11， 10
 * 要求】 额外空间复杂度为O(1)。
 *
 *
 * 思路：这种类型的题其实要有整体思维，有左上角和右下角就可以确定一个矩阵的形状，再沿着左上角和右下角的对角线往中间收缩。
 * 至于遍历矩阵的外壳可以自己想下。
 */
public class PrintMatrixSpiralOrder {

    public static void spiralOrderPrint(int[][] matrix){
        int tR = 0;
        int tC = 0;
        int dR = matrix.length-1;
        int dC = matrix[0].length-1;
        while(tR<=dR && tC<=dC){
            printEdge(matrix,tR++,tC++,dR--,dC--);
        }
    }

    public static void printEdge(int[][] m, int tR, int tC, int dR, int dC){
        if(tR==dR){
            for(int i=tC;i<=dC;i++){
                System.out.println(m[tR][i]+ " ");
            }
        }else if(tC==dC){
            for(int i=tR;i<=dR;i++){
                System.out.println(m[i][tC]+" ");
            }
        }else{
            int curC=tC;//当前列
            int curR=tR;//当前行
            while(curC != dC){
                System.out.println(m[tR][curC]+" ");
                curC++;
            }
            while(curR != dR){
                System.out.println(m[curR][dC]+" ");
                curR++;
            }
            while(curC!=tC){
                System.out.println(m[dR][curC]+" ");
                curC--;
            }
            while(curR!=dR){
                System.out.println(m[curR][tC]+" ");
                curR--;
            }
        }
    }


    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12},
                {13, 14, 15, 16}};
        spiralOrderPrint(matrix);
    }


}
