package jianzhioffer;

import jianzhioffer.queue.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class ByteDanceTimu {
    public static void main(String[] args) {
        TreeNode node5 = new TreeNode(5, null, null);
        TreeNode node3 = new TreeNode(3, null, null);
        TreeNode node2 = new TreeNode(2, null, node5);
        TreeNode root = new TreeNode(1, node2, node3);
        System.out.println(binaryTreePaths(root));
    }

    static List<String> allPaths = new ArrayList<>();

    /**
     * 62. 不同路径
     * https://leetcode-cn.com/problems/unique-paths/
     * @param m
     * @param n
     * @return
     */
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        for (int i = 0; i < n; i++) {
            dp[0][i] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }

    /**
     * 257. 二叉树的所有路径
     * https://leetcode-cn.com/problems/binary-tree-paths/
     * @param root
     * @return
     */
    public static List<String> binaryTreePaths(TreeNode root) {
        List<String> tmpPath = new ArrayList<>();
        tmpPath.add(Integer.toString(root.val));
        dfsTreePaths(root, tmpPath);
        return allPaths;
    }

    public static void dfsTreePaths(TreeNode node, List<String> tmpPath) {
        if (node.left == null && node.right == null) {
            allPaths.add(String.join("->", tmpPath));
        }
        if (node.left != null) {
            tmpPath.add(Integer.toString(node.left.val));
            dfsTreePaths(node.left, tmpPath);
            tmpPath.remove(tmpPath.size() - 1);
        }
        if (node.right != null) {
            tmpPath.add(Integer.toString(node.right.val));
            dfsTreePaths(node.right, tmpPath);
            tmpPath.remove(tmpPath.size() - 1);
        }
    }

    /**
     * 64. 最小路径和
     * @param grid
     * @return
     */
    public static int minPathSum(int[][] grid) {
        int row = grid.length, col = grid[0].length;
        for (int i = 1; i < row; i++) {
            grid[i][0] += grid[i - 1][0];
        }
        for (int i = 1; i < col; i++) {
            grid[0][i] += grid[0][i - 1];
        }
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                grid[i][j] += Math.min(grid[i - 1][j], grid[i][j - 1]);
            }
        }
        return grid[row - 1][col - 1];
    }

    /**
     * 689. 三个无重叠子数组的最大和
     * https://leetcode-cn.com/problems/maximum-sum-of-3-non-overlapping-subarrays/
     * @param nums
     * @param k
     * @return
     */
    public static int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        int sum1 = 0, max1 = 0, index1 = 0;
        int sum2 = 0, max12 = 0, index12 = 0, index2 = 0;
        int sum3 = 0, maxTotal = 0;
        int[] ans = new int[3];
        for (int i = 2 * k; i < nums.length; i++) {
            sum1 += nums[i - 2 * k];
            sum2 += nums[i - k];
            sum3 += nums[i];
            if (i >= 3 * k - 1) {
                if (sum1 > max1) {
                    max1 = sum1;
                    index1 = i - 3 * k + 1;
                }
                if (max1 + sum2 > max12) {
                    max12 = max1 + sum2;
                    index12 = index1;
                    index2 = i - 2 * k + 1;
                }
                if (max12 + sum3 > maxTotal) {
                    maxTotal = max12 + sum3;
                    ans[0] = index12;
                    ans[1] = index2;
                    ans[2] = i - k + 1;
                }
                sum1 -= nums[i - 3 * k + 1];
                sum2 -= nums[i - 2 * k + 1];
                sum3 -= nums[i - k + 1];
            }

        }
        System.out.println(max1 + ", " + max12 + ", " + maxTotal);
        System.out.println(ans[0] + ", " + ans[1] + ", " + ans[2]);
        return ans;
    }
}
