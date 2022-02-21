package jianzhioffer.backtracking;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class Tu {
    int sum = 0;

    public static void main(String[] args) {
        Tu tu = new Tu();
//        int[][] a = tu.updateMatrix(new int[][]{{0,0,0}, {0,1,0}, {1,1,1}});
//        for (int i = 0; i < a.length; i++) {
//            for (int j = 0; j < a[0].length; j++) {
//                System.out.print(a[i][j] + " ");
//            }
//            System.out.println();
//        }
    }

    /**
     * 岛屿的最大面积
     * https://leetcode-cn.com/problems/ZL6zAn/
     * @param grid
     * @return
     */
    public int maxAreaOfIsland(int[][] grid) {
        int res = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    dfsIsland(grid, i, j);
                    res = Math.max(res, sum);
                    sum = 0;
                }
            }
        }
        return res;
    }

    public void dfsIsland(int[][] grid, int row, int col) {
        if (row < 0 || col < 0 || row >= grid.length || col == grid[0].length || grid[row][col] != 1) {
            return;
        }
        grid[row][col] = -1;
        sum ++;
        dfsIsland(grid, row - 1, col);
        dfsIsland(grid, row + 1, col);
        dfsIsland(grid, row, col - 1);
        dfsIsland(grid, row, col + 1);
    }

    /**
     * 矩阵中的距离
     * https://leetcode-cn.com/problems/2bCMpM/
     * @param mat
     * @return
     */
//    public int[][] updateMatrix(int[][] mat) {
//        HashMap
//        int row = mat.length, col = mat[0].length;
//        boolean[][] visited = new boolean[row][col];
//        Queue<BfsNode> queue = new LinkedList<>();
//        for (int i = 0; i < mat.length; i++) {
//            for (int j = 0; j < mat[0].length; j++) {
//                if (mat[i][j] != 0) {
//                    dfsUpdateMatrix(mat, new boolean[row][col], i, j);
//                    mat[i][j] = sum;
//                    sum = 0;
//                    System.out.println(" ----------------- ");
//                }
//            }
//        }
//        return mat;
//    }

    class BfsNode {
        int r;
        int c;
        int dis;

        public void BfsNode(int r, int c, int dis) {
            this.r = r;
            this.c = c;
            this.dis = dis;
        }
    }
}
