package novice.recursion;

/*
* 题目描述：给定一个二维数组，二维数组中的每个数都是正数，要求从左上角走到右下角，每一步只能往右或者往下。
* 沿途数字加起来，返回最小路径和。
*
*
* 思路：
* */
public class MinPath {

    public static int minPath(int[][] matrix){
        return process(matrix,matrix.length-1,matrix[0].length-1);
    }

    public static  int process(int[][] matrix, int i, int j){
        int res = matrix[i][j];

        if(i==0 && j==0){
            return res;
        }
        if(i==0 && j!=0){
            return res+process(matrix,i,j-1);
        }
        if(i!=0 && j==0){
            return  res+process(matrix,i-1,j);
        }
        return res+Math.min(process(matrix,i,j-1),process(matrix,i-1,j));
    }


    // for test
    public static int[][] generateRandomMatrix(int rowSize, int colSize) {
        if (rowSize < 0 || colSize < 0) {
            return null;
        }
        int[][] result = new int[rowSize][colSize];
        for (int i = 0; i != result.length; i++) {
            for (int j = 0; j != result[0].length; j++) {
                result[i][j] = (int) (Math.random() * 10);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] m = { { 1, 3, 5, 9 }, { 8, 1, 3, 4 }, { 5, 0, 6, 1 }, { 8, 8, 4, 0 } };
        System.out.println(minPath(m));

        m = generateRandomMatrix(6, 7);
        System.out.println(minPath(m));
    }
}
