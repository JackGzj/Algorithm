import jianzhioffer.LinkedListTiMu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;


public class Test {

    public static void main(String[] args) {
        System.out.println(isValid("{()}[]([])"));
    }

    /**
     * 20. 有效的括号
     * https://leetcode-cn.com/problems/valid-parentheses/
     * @param s
     * @return
     */
    public static boolean isValid(String s) {
        if (s.length() % 2 == 1) {
            return false;
        }
        Deque<Character> stack = new LinkedList<>(), match = new LinkedList<>();
        for (int i = 0; i < s.length(); i++) {
            stack.add(s.charAt(i));
        }
        stack.forEach(c -> System.out.print(c + ", "));
        System.out.println();
        char c;
        while (!stack.isEmpty()) {
            c = stack.pollLast();
            if (stack.isEmpty()) {
                return false;
            }
            switch (c) {
                case '}':
                    if (stack.peekLast() == '{') {
                        stack.pollLast();
                    } else {
                        match.add(c);
                    }
                    break;
                case ']':
                    if (stack.peekLast() == '[') {
                        stack.pollLast();
                    } else {
                        match.add(c);
                    }
                    break;
                case ')':
                    if (stack.peekLast() == '(') {
                        stack.pollLast();
                    } else {
                        match.add(c);
                    }
                    break;
                case '{':
                    if (match.isEmpty() || match.peekLast() != '}') {
                        return false;
                    }
                    match.pollLast();
                    break;
                case '[':
                    if (match.isEmpty() || match.peekLast() != ']') {
                        return false;
                    }
                    match.pollLast();
                    break;
                case '(':
                    if (match.isEmpty() || match.peekLast() != ')') {
                        return false;
                    }
                    match.pollLast();
                    break;
            }
        }
        return match.isEmpty();
    }

    public int findMaxLength(int[] nums) {
        int sum = 0, ans = 0;
        Map<Integer, Integer> sumIndex = new HashMap<Integer, Integer>(){
            {
                put(0, -1);
            }
        };
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                sum += -1;
            } else {
                sum += 1;
            }
            System.out.println(sumIndex);
            if (sumIndex.containsKey(sum)) {
                System.out.println(sum + ", " + i + ", " + sumIndex.get(sum));
                ans = Math.max(ans, i - sumIndex.get(sum));
            } else {
                sumIndex.put(sum, i);
            }
        }
        return ans;
    }

    public boolean canPartition(int[] nums) {
        int sum = Arrays.stream(nums).sum();
        if (sum % 2 == 1) {
            return false;
        }
        sum /= 2;
        boolean[] dp = new boolean[sum + 1];
        dp[0] = true;
        for (int i = 0; i < nums.length; i++) {
            for (int j = sum; j >= nums[i]; j--) {
                dp[j] = dp[j] || dp[j - nums[i]];
            }
        }
        return dp[sum];
    }

    public int minimumTotal(List<List<Integer>> triangle) {
        int size = triangle.get(triangle.size() - 1).size(), now;
        int[][] dp = new int[size][size];
        dp[0][0] = triangle.get(0).get(0);
        for (int i = 1; i < size; i++) {
            for (int j = 0; j <= i; j++) {
                now = triangle.get(i).get(j);
                if (j == 0) {
                    dp[i][j] = dp[i - 1][j] + now;
                } else if (j == i) {
                    dp[i][j] = dp[i - 1][j - 1] + now;
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i - 1][j - 1]) + now;
                }
            }
        }
        now = Integer.MAX_VALUE;
        for (int i = 0; i < size; i++) {
            now = Math.min(now, dp[size - 1][i]);
        }
        return now;
    }

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

    public int minPathSum(int[][] grid) {
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

    public int dfsUniquePaths(int tempM, int tempN, int m, int n) {
        if (tempM == m || tempN == n) {
            return 1;
        }
        int ans = 0;
        if (tempM + 1 <= m) {
            ans += dfsUniquePaths(tempM + 1, tempN, m, n);
        }
        if (tempN + 1 <= n) {
            ans += dfsUniquePaths(tempM, tempN + 1, m, n);
        }
        return ans;
    }
}