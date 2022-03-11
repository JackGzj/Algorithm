package jianzhioffer;

import jianzhioffer.queue.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class ByteDanceTimu {
    public static void main(String[] args) {
        List<List<Integer>> list = new ArrayList<>();
        list.add(Arrays.asList(4,10,15,24,26));
        list.add(Arrays.asList(0,9,12,20));
        list.add(Arrays.asList(5,18,22,30));
        System.out.println(smallestRange(list));
    }

    static List<String> allPaths = new ArrayList<>();

    /**
     * 632. 最小区间
     * https://leetcode-cn.com/problems/smallest-range-covering-elements-from-k-lists/
     * note: 解法超时了！考虑不排序直接滑窗！
     * @param nums
     * @return
     */
    public static int[] smallestRange(List<List<Integer>> nums) {
        // 排序
        List<NumGroup> numGroups = new ArrayList<>();
        HashMap<Integer, Integer> indexMap = new HashMap<>();
        for (int i = 0; i < nums.size(); i++) {
            indexMap.put(i, 0);
        }
        while (true) {
            int endCount = 0, min = Integer.MAX_VALUE, num;
            Map.Entry<Integer, Integer> minEntry = null;
            for (Map.Entry<Integer, Integer> entry : indexMap.entrySet()) {
                if (entry.getValue() == nums.get(entry.getKey()).size()) {
                    endCount++;
                    continue;
                }
                num = nums.get(entry.getKey()).get(entry.getValue());
                if (num < min) {
                    min = num;
                    minEntry = entry;
                }
            }
            if (endCount == nums.size()) {
                break;
            }
            numGroups.add(new NumGroup(min, minEntry.getKey()));
            indexMap.put(minEntry.getKey(), minEntry.getValue() + 1);
        }
        System.out.println(numGroups);
        // 滑窗
        indexMap.clear();
        int left = 0, right = 1;
        for (int i = 0; i < numGroups.size(); i++) {
            indexMap.put(numGroups.get(i).index, indexMap.getOrDefault(numGroups.get(i).index, 0) + 1);
            if (indexMap.size() == nums.size()) {
                right = i;
                break;
            }
        }
        NumGroup leftG = numGroups.get(left), rightG = numGroups.get(right);
        int range = rightG.num - leftG.num;
        int[] ans = new int[]{leftG.num, rightG.num};
        while (right < numGroups.size()) {
            leftG = numGroups.get(left);
            int leftIndexCount = indexMap.get(leftG.index);
            System.out.println(indexMap);
            while (leftIndexCount > 1) {
                indexMap.put(leftG.index, leftIndexCount - 1);
                left ++;
                leftG = numGroups.get(left);
                leftIndexCount = indexMap.get(leftG.index);
            }
            System.out.println(left + ", " + right);
            if (rightG.num - leftG.num < range) {
                range = rightG.num - leftG.num;
                ans[0] = leftG.num;
                ans[1] = rightG.num;
            }
            right ++;
            if (right == numGroups.size()) {
                break;
            }
            rightG = numGroups.get(right);
            indexMap.put(rightG.index, indexMap.getOrDefault(rightG.index, 0) + 1);
        }
        System.out.println(ans[0] + ", " + ans[1]);
        return ans;
    }

    /**
     * 400. 第 N 位数字
     * https://leetcode-cn.com/problems/nth-digit/
     * @param n
     * @return
     */
    public static int findNthDigit(int n) {
        // 最多9位
        int wei = 9;
        long start = 0, pre = 0;
        // 确定位数
        for (int i = 1; i < 10; i++) {
            pre = start;
            start += i * 9 * (long)Math.pow(10, i - 1);
            if (pre < n && start >= n) {
                wei = i;
                break;
            }
            System.out.println(i + ", " + start);
        }
        // 确定数字 num 的第 n 位
        n -= pre + 1;
        int num = (int)Math.pow(10, wei - 1) + (n / wei), i = 0;
        n %= wei;
        System.out.println(wei + ", " + num + ", " + n);
        // 拆分数字 num 位数
        int[] bits = new int[wei];
        while (num > 0) {
            bits[i++] = num % 10;
            num /= 10;
        }
        return bits[wei - 1 - n];
    }

    /**
     * 378. 有序矩阵中第 K 小的元素
     * https://leetcode-cn.com/problems/kth-smallest-element-in-a-sorted-matrix/
     * @param matrix
     * @param k
     * @return
     */
    public static int kthSmallest(int[][] matrix, int k) {
        k = matrix.length * matrix.length - k + 1;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                pq.add(matrix[i][j]);
                if (pq.size() > k) {
                    pq.poll();
                }
            }
        }
        return pq.poll();
    }

    /**
     * 226. 翻转二叉树
     * https://leetcode-cn.com/problems/invert-binary-tree/
     * @param root
     * @return
     */
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode left = invertTree(root.left), right = invertTree(root.right);
        root.left = right;
        root.right = left;
        return root;
    }

    /**
     * 剑指 Offer 63. 股票的最大利润
     * https://leetcode-cn.com/problems/gu-piao-de-zui-da-li-run-lcof/
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int minPrice = Integer.MAX_VALUE, maxProfit = 0;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < minPrice) {
                minPrice = prices[i];
            }
            maxProfit = Math.max(maxProfit, prices[i] - minPrice);
        }
        return maxProfit;
    }

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

    static class NumGroup {
        int num;
        int index;

        NumGroup(int num, int index) {
            this.num = num;
            this.index = index;
        }

        @Override
        public String toString() {
            return num + ": " + index;
        }
    }
}
