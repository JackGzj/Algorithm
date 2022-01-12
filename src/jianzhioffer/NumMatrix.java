package jianzhioffer;

public class NumMatrix {
    private int[][] sumMatrix;

    public static void main(StringTiMu[] args) {
        NumMatrix n = new NumMatrix(new int[][]{{3,0,1,4,2},{5,6,3,2,1},{1,2,0,1,5},{4,1,0,1,7},{1,0,3,0,5}});
        System.out.println(n.sumRegion(2, 1, 4, 3));
        System.out.println(n.sumRegion(1, 1, 2, 2));
        System.out.println(n.sumRegion(1, 2, 2, 4));
    }

    public NumMatrix(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return;
        }
        sumMatrix = new int[matrix.length + 1][matrix[0].length + 1];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                sumMatrix[i + 1][j + 1] = sumMatrix[i][j + 1] + sumMatrix[i + 1][j] - sumMatrix[i][j] + matrix[i][j];
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
//        System.out.println(sumMatrix[row2 + 1][col2 + 1]);
        return sumMatrix[row2 + 1][col2 + 1] - sumMatrix[row1][col2 + 1] - sumMatrix[row2 + 1][col1] + sumMatrix[row1][col1];
    }
}
