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
        System.out.println(permute(new int[]{1,2,3}));
    }

    static List<List<Integer>> ans = new ArrayList<>();
    /**
     * 46. 全排列
     * https://leetcode-cn.com/problems/permutations/
     * @param nums
     * @return
     */
    public static List<List<Integer>> permute(int[] nums) {
        List<Integer> list = new ArrayList<>();
        for (int i : nums) {
            list.add(i);
        }
        dfsPermute(list, 0);
        return ans;
    }

    public static void dfsPermute(List<Integer> nums, int index) {
        if (index == nums.size()) {
            ans.add(nums);
            System.out.println(nums);
        }
        System.out.println(nums + ", " + index);
        for (int i = index; i < nums.size(); i++) {
            Collections.swap(nums, i, index);
            dfsPermute(nums, index + 1);
            Collections.swap(nums, i, index);
        }
    }

    /**
     * 39. 组合总和
     * https://leetcode-cn.com/problems/combination-sum/
     * @param candidates
     * @param target
     * @return
     */
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        dfsCombinationSum(candidates, 0, 0, target, new LinkedList<>());
        return ans;
    }

    public static void dfsCombinationSum(int[] candidates, int index, int sum, int target, Deque<Integer> stack) {
        if (sum == target) {
            ans.add(new ArrayList<>(stack));
            return;
        }
        for (int i = index; i < candidates.length; i++) {
            int nowSum = sum + candidates[i];
            if (nowSum > target) {
                return;
            }
            stack.addLast(candidates[i]);
            dfsCombinationSum(candidates, i, nowSum, target, stack);
            stack.pollLast();
        }
    }

    /**
     * 34. 在排序数组中查找元素的第一个和最后一个位置
     * https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array/
     * @param nums
     * @param target
     * @return
     */
    public static int[] searchRange(int[] nums, int target) {
        boolean flag = false;
        int left = 0, right = nums.length - 1, mid = 0;
        while (left <= right) {
            mid = (left + right) / 2;
            if (nums[mid] == target) {
                flag = true;
                break;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        if (!flag) {
            return new int[]{-1, -1};
        }
        // 向两边扩充寻找起始和结束位点
        left = mid;
        while (left >= 0 && nums[left] == target) {
            left--;
        }
        right = mid;
        while (right < nums.length && nums[right] == target) {
            right++;
        }
        int[] res = new int[]{left + 1, right - 1};
        return res;
    }

    /**
     * 54. 螺旋矩阵
     * https://leetcode-cn.com/problems/spiral-matrix/
     * note：顺时针是有顺序的，不必分4种情况判断方向！
     * @param matrix
     * @return
     */
    public static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> ans = new ArrayList<>();
        int[][] directions = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int row = matrix.length, col = matrix[0].length, total = row * col;
        int i = 0, j = 0, nextI, nextJ, directionIndex = 0;
        while (total-- > 0) {
            ans.add(matrix[i][j]);
            matrix[i][j] = Integer.MIN_VALUE;
            nextI = i + directions[directionIndex][0];
            nextJ = j + directions[directionIndex][1];
            System.out.print("temp: " + i + ", " + j + ", " + directionIndex);
            if (nextI == -1 || nextI == row || nextJ == -1 || nextJ == col ||
                    matrix[nextI][nextJ] == Integer.MIN_VALUE) {
                directionIndex = (directionIndex + 1) % 4;
            }
            // 指向下一个
            i += directions[directionIndex][0];
            j += directions[directionIndex][1];
            System.out.println(", next: " + i + ", " + j + ", " + directionIndex);
        }
        return ans;
    }

    /**
     * 32. 最长有效括号
     * https://leetcode-cn.com/problems/longest-valid-parentheses/
     * note：匹配一个弹一个的话，会把栈弹空，遇到 "()()" 这种情况没法正常计算，因此需要在栈底保留一个上次弹出的index
     * @param s
     * @return
     */
    public static int longestValidParentheses(String s) {
        if (s.length() == 0) {
            return 0;
        }
        int ans = 0;
        Deque<Integer> stack = new LinkedList<>();
        stack.push(-1);
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.addLast(i);
            } else {
                stack.pollLast();
                if (stack.isEmpty()) {
                    stack.addLast(i);
                } else {
//                    System.out.println(i + ", " + stack.peekLast());
                    ans = Math.max(i - stack.peekLast(), ans);
                }
            }
        }
        return ans;
    }

    /**
     * 31. 下一个排列
     * https://leetcode-cn.com/problems/next-permutation/
     * @param nums
     */
    public static void nextPermutation(int[] nums) {
        if (nums.length == 1) {
            return;
        }
        int left, right, i = nums.length - 2;
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }
        left = i;
        if (left >= 0) {
            i = nums.length - 1;
            while (i >= 0 && nums[i] <= nums[left]) {
                i--;
            }
            right = i;
            System.out.println(nums[left] + ", " + nums[right]);
            swap(nums, left, right);
            reverse(nums, left + 1);
        } else {
            reverse(nums, 0);
        }
    }

    public static void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public static void reverse(int[] nums, int start) {
        int left = start, right = nums.length - 1;
        while (left < right) {
            swap(nums, left, right);
            left++;
            right--;
        }
    }

    static List<String> res = new ArrayList<>();

    /**
     * 23. 合并K个升序链表
     * https://leetcode-cn.com/problems/merge-k-sorted-lists/
     * note：分治+合并两个有序链表
     * @param lists
     * @return
     */
    public ListNode mergeKLists(ListNode[] lists) {
        return splitAndMerge(lists, 0, lists.length - 1);
    }

    public ListNode splitAndMerge(ListNode[] lists, int start, int end) {
        if (start == end) {
            return lists[start];
        } else if (start < end) {
            int mid = (start + end) / 2;
            return merge(splitAndMerge(lists, start, mid), splitAndMerge(lists, mid + 1, end));
        } else {
            return null;
        }
    }

    public ListNode merge(ListNode list1, ListNode list2) {
        ListNode head = new ListNode(-1), ptr = head;
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                ptr.next = new ListNode(list1.val);
                list1 = list1.next;
            } else {
                ptr.next = new ListNode(list2.val);
                list2 = list2.next;
            }
            ptr = ptr.next;
        }
        while (list1 != null) {
            ptr.next = new ListNode(list1.val);
            ptr = ptr.next;
            list1 = list1.next;
        }
        while (list2 != null) {
            ptr.next = new ListNode(list2.val);
            ptr = ptr.next;
            list2 = list2.next;
        }
        return head.next;
    }

    /**
     * 22. 括号生成
     * https://leetcode-cn.com/problems/generate-parentheses/
     * @param n
     * @return
     */
    public static List<String> generateParenthesis(int n) {
        dfsParenthesis(n, n, new StringBuilder());
        return res;
    }

    public static void dfsParenthesis(int n, int rightCount, StringBuilder builder) {
        if (n == 0 && rightCount == 0) {
            res.add(builder.toString());
        }
        if (n > 0) {
            builder.append("(");
            dfsParenthesis(n - 1, rightCount, builder);
            builder.deleteCharAt(builder.length() - 1);
        }
        if (rightCount > 0 && rightCount > n) {
            builder.append(")");
            dfsParenthesis(n, rightCount - 1, builder);
            builder.deleteCharAt(builder.length() - 1);
        }
    }

    /**
     * 20. 有效的括号
     * https://leetcode-cn.com/problems/valid-parentheses/
     * note：自己的思路用了两个栈。题目解析可以只用一个栈，对s遍历并将左边符号入栈，遇到右边符合再判断
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
     * 64. 最小路径和
     * https://leetcode-cn.com/problems/minimum-path-sum/
     * note: DP，除了最上面一行和最左边一行外，一个格子的最小路径和要么从上面过来，要么从左边过来
     * @param grid
     * @return
     */
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