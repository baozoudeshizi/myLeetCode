package novice.matrix;

/**
 * 旋转正方形矩阵
 * 给定一个整型正方形矩阵matrix，请把该矩阵调整成 顺时针旋转90度的样子。
 *【要求】 额外空间复杂度为O(1)。
 */

public class RotateMatrix {
    public static void rotate(int[][] matrix){
        int tR=0;
        int tC=0;
        int dR=matrix.length-1;
        int dC=matrix[0].length-1;
        while (tR<dR){//循环，其实也是从外围正方形到内围的正方形
            rotateEdge(matrix,tR++,tC++,dR--,dC--);
        }
    }

    //这个旋转的思路，其实我们搞清楚怎么旋转，找到一层中的一个点是怎么转的即可
    //四条边，每边的一个点都有另一条边的另一个点来代替他，所以类似于swap，先定义一个tmp，然后在弄是怎么旋转替换的
    //这是一层的旋转
    public static void rotateEdge(int[][] m, int tR, int tC, int dR, int dC){
        int times=dC-tC;
        int tmp=0;
        for(int i=0; i!=times; i++){
            //这是四个点的旋转
            tmp = m[tR][tC+i];
            m[tR][tC+i]=m[dR-i][tC];
            m[dR-i][tC]=m[dR][dC-i];
            m[dR][dC-i]=m[tR+i][dC];
            m[tR+i][dC]=tmp;
        }
    }

    public static void printMatrix(int[][] matrix) {
        for (int i = 0; i != matrix.length; i++) {
            for (int j = 0; j != matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] matrix = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 },
                { 13, 14, 15, 16 } };
        printMatrix(matrix);
        rotate(matrix);
        System.out.println("=========");
        printMatrix(matrix);

    }

}
