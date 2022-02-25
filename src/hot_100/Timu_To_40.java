package hot_100;

import jianzhioffer.linkedlist.ListNode;

import java.util.*;
import java.util.stream.Collectors;

public class Timu_To_40 {
    public static void main(String[] args) {
        System.out.println(letterCombinations("2"));
    }

    static Map<Character, String> phoneIndex = new HashMap<Character, String>() {
        {
            put('2', "abc");
            put('3', "def");
            put('4', "ghi");
            put('5', "jkl");
            put('6', "mno");
            put('7', "pqrs");
            put('8', "tuv");
            put('9', "wxyz");
        }
    };
    static List<String> ans = new ArrayList<>();
    static List<List<Integer>> ans1 = new ArrayList<>();

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
        return ans1;
    }

    public static void dfsCombinationSum(int[] candidates, int index, int sum, int target, Deque<Integer> stack) {
        if (sum == target) {
            ans1.add(new ArrayList<>(stack));
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

    /**
     * 17. 电话号码的字母组合
     * https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number/
     * @param digits
     * @return
     */
    public static List<String> letterCombinations(String digits) {
        if (digits.length() == 0) {
            return new ArrayList<>();
        }
        dfsPhoneNumbers(digits, 0, new StringBuilder());
        return ans;
    }

    public static void dfsPhoneNumbers(String digits, int index, StringBuilder builder) {
        if (index == digits.length()) {
            ans.add(builder.toString());
            return;
        }
        String letters = phoneIndex.get(digits.charAt(index));
        for (int i = 0; i < letters.length(); i++) {
            builder.append(letters.charAt(i));
            dfsPhoneNumbers(digits, index + 1, builder);
            builder.deleteCharAt(index);
        }
    }

    /**
     * 11. 盛最多水的容器
     * https://leetcode-cn.com/problems/container-with-most-water/
     * @param height
     * @return
     */
    public static int maxArea(int[] height) {
        int ans = 0, left = 0, right = height.length - 1;
        while (left < right) {
            ans = Math.max(ans, Math.min(height[left], height[right]) * (right - left));
            if (height[left] < height[right]) {
                left ++;
            } else {
                right --;
            }
        }
        return ans;
    }

    /**
     * 5. 最长回文子串
     * https://leetcode-cn.com/problems/longest-palindromic-substring/
     * @param s
     * @return
     * 记录 maxLen 的 if 块可能进入两次，可以优化！
     */
    public static String longestPalindrome(String s) {
        int maxLen = 0;
        String ans = null;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandMiddlePtr(s, i, i);
            int len2 = expandMiddlePtr(s, i, i + 1);
            System.out.println(len1 + ", " + len2);
            if (len1 > maxLen) {
                // 奇数长度
                maxLen = len1;
                ans = s.substring(i - maxLen / 2 , i + maxLen / 2 + 1);
            }
            if (len2 > maxLen) {
                // 偶数长度
                maxLen = len2;
                System.out.println(i + ", " + maxLen);
                ans = s.substring(i - maxLen / 2 + 1, i + maxLen / 2 + 1);
            }
        }
        return ans;
    }

    private static int expandMiddlePtr(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left --;
            right ++;
        }
        return right - left - 1;
    }

    /**
     * 4. 寻找两个正序数组的中位数
     * https://leetcode-cn.com/problems/median-of-two-sorted-arrays/
     * @param nums1
     * @param nums2
     * @return
     */
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        PriorityQueue<Integer> small = new PriorityQueue<>(), large = new PriorityQueue<>(Comparator.reverseOrder());
        for (int i : nums1) {
            if (small.size() == large.size()) {
                small.add(i);
                large.add(small.poll());
            } else {
                large.add(i);
                small.add(large.poll());
            }
        }
        for (int i : nums2) {
            if (small.size() == large.size()) {
                small.add(i);
                large.add(small.poll());
            } else {
                large.add(i);
                small.add(large.poll());
            }
        }
        if (small.size() == large.size()) {
            return (large.poll() + small.poll()) / 2.0;
        } else {
            return large.poll() * 1.0;
        }
    }


    /**
     * 3. 无重复字符的最长子串
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring(String s) {
        if (s.length() == 0) {
            return 0;
        }
        char c;
        int ans = 0, left = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            c = s.charAt(i);
            if (map.containsKey(c)) {
                left = Math.max(left, map.get(c) + 1);
            }
            map.put(c, i);
            ans = Math.max(ans, i - left + 1);
        }
        return ans;
    }

    /**
     * 2. 两数相加
     * https://leetcode-cn.com/problems/add-two-numbers/
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int jinWei = 0, tmp;
        ListNode res = new ListNode(-1), head = res;
        while (l1 != null && l2 != null) {
            tmp = l1.val + l2.val + jinWei;
            res.next = new ListNode(tmp % 10);
            res = res.next;
            jinWei = tmp / 10;
            l1 = l1.next;
            l2 = l2.next;
        }
        ListNode rest = l1 == null ? l2 : l1;
        while (rest != null) {
            tmp = rest.val + jinWei;
            res.next = new ListNode(tmp % 10);
            res = res.next;
            jinWei = tmp / 10;
            rest = rest.next;
        }
        if (jinWei > 0) {
            res.next = new ListNode(jinWei);
        }
        return head.next;
    }

    /**
     * 1. 两数之和
     * https://leetcode-cn.com/problems/two-sum/
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        Integer find;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.putIfAbsent(nums[i], i);
            find = map.get(target - nums[i]);
            if (find != null && find != i) {
                return new int[]{find, i};
            }
        }
        return new int[]{0, 0};
    }
}
