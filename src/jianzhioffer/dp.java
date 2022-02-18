package jianzhioffer;

import java.util.Arrays;
import java.util.Scanner;

public class dp {
    public static void main(String[] args) {Scanner sc = new Scanner(System.in);
    sc.close();
        dp dp = new dp();
        System.out.println(dp.numDistinct("babgbag", "bag"));
    }

    /**
     * 子序列的数目
     * https://leetcode-cn.com/problems/21dk04/
     * @param s
     * @param t
     * @return
     */
    public int numDistinct(String s, String t) {
        if (t.length() > s.length()) {
            return 0;
        }
        int[][] dp = new int[s.length() + 1][t.length() + 1];
        for (int i = 0; i <= s.length(); i++) {
            dp[i][t.length()] = 1;
        }
        for (int i = s.length() - 1; i >= 0; i--) {
            for (int j = t.length() - 1; j >= 0; j--) {
                if (s.charAt(i) == t.charAt(j)) {
                    dp[i][j] = dp[i + 1][j + 1] + dp[i + 1][j];
                } else {
                    dp[i][j] = dp[i + 1][j];
                }
            }
        }
        return dp[0][0];
    }

    /**
     * 最少的硬币数目
     * https://leetcode-cn.com/problems/gaM7Ch/
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (i >= coins[j]) {
                    dp[i] = Math.min(dp[i - coins[j]] + 1, dp[i]);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }

    /**
     * 最长公共子序列
     * https://leetcode-cn.com/problems/qJnOS7/
     * @param text1
     * @param text2
     * @return
     */
    public int longestCommonSubsequence(String text1, String text2) {
        int[][] dp = new int[text1.length() + 1][text2.length() + 1];
        for (int i = 1; i <= text1.length(); i++) {
            for (int j = 1; j <= text1.length(); j++) {
                dp[i][j] = text1.charAt(i - 1) == text2.charAt(j - 1) ? dp[i - 1][j - 1] + 1 :
                        Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }
        return dp[text1.length()][text2.length()];
    }

    /**
     * 翻转字符
     * https://leetcode-cn.com/problems/cyJERH/
     * @param s
     * @return
     */
    public int minFlipsMonoIncr(String s) {
        int one = 0, dp = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '0') {
                dp = Math.min(one, dp + 1);
            } else {
                one++;
            }
        }
        return dp;
    }

    /**
     * 爬楼梯的最少成本
     *
     * @param cost
     * @return
     */
    public int minCostClimbingStairs(int[] cost) {
        int pre = 0, curr = 0, next;
        for (int i = 2; i <= cost.length; i++) {
            next = Math.min(curr + cost[i - 1], pre + cost[i - 2]);
            pre = curr;
            curr = next;
        }
        return curr;
    }

    /**
     * 房屋偷盗
     *
     * @param nums
     * @return
     */
    public int rob(int[] nums) {
        int pre = 0, curr = 0, next;
        for (int i : nums) {
            next = Math.max(curr, pre + i);
            pre = curr;
            curr = next;
        }
        return curr;
    }

    /**
     * 房屋偷盗
     * @param nums
     * @return
     */
    public int robCircle(int[] nums) {
        if (nums.length < 3) {
            return Arrays.stream(nums).max().getAsInt();
        }
        int pre = 0, curr = 0, next;
        for (int i = 0; i < nums.length - 1; i++) {
            next = Math.max(curr, pre + nums[i]);
            pre = curr;
            curr = next;
        }
        int ans = curr;
        curr = 0; pre = 0;
        for (int i = 1; i < nums.length; i++) {
            next = Math.max(curr, pre + nums[i]);
            pre = curr;
            curr = next;
        }
        return Math.max(curr, ans);
    }

    /**
     * 粉刷房子
     * https://leetcode-cn.com/problems/JEj789/
     * @param costs
     * @return
     */
    public int minCost(int[][] costs) {
        int[][] dp = new int[costs.length][3];
        for (int i = 0; i < costs[0].length; i++) {
            dp[0][i] = costs[0][i];
        }
        for (int i = 1; i < costs.length; i++) {
            dp[i][0] = Math.min(dp[i - 1][1], dp[i - 1][2]) + costs[i][0];
            dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2]) + costs[i][1];
            dp[i][2] = Math.min(dp[i - 1][0], dp[i - 1][1]) + costs[i][2];
        }
        return Math.min(dp[costs.length - 1][0], Math.min(dp[costs.length - 1][1], dp[costs.length - 1][2]));
    }
}
