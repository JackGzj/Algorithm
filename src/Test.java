import javafx.util.Pair;
import jianzhioffer.Array;
import jianzhioffer.linkedlist.ListNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public class Test {

    public static void main(String[] args) {
        System.out.println();
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

    /**
     *
     * @param triangle
     * @return
     */
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