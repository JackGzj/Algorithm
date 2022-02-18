package jianzhioffer.backtracking;

import com.sun.deploy.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

public class BackTrackingTimu {
    List<List<Integer>> res1;
    Set<List<Integer>> res2;
    List<String> res3;
    List<String[]> res4;

    public static void main(String[] args) {
        BackTrackingTimu timu = new BackTrackingTimu();
        System.out.println(timu.restoreIpAddresses("010010"));
    }

    /**
     * 复原 IP
     * https://leetcode-cn.com/problems/0on3uN/
     * @param s
     * @return
     */
    public List<String> restoreIpAddresses(String s) {
        res3 = new ArrayList<>();
        int[] arr = new int[s.length()];
//        System.out.println(getNum(arr, 0, 2));
        dfsIp(s, 0, new LinkedList<>());
        return res3;
    }

    private void dfsIp(String s, int start, Deque<Integer> ipNums) {
        if (ipNums.size() == 3) {
            int end = s.length() - 1;
            if (end - start > 3 || (end - start > 0 && s.charAt(start) == '0')) {
                // 大于3位数 或者 大于一位且以0开始
                return;
            }
            int sum = getNum(s, start, end);
            if (sum > 255) {
                return;
            }
            ipNums.addLast(sum);
//            System.out.println(sb.toString());
            res3.add(ipNums.stream().map(i -> Integer.toString(i)).collect(Collectors.joining(".")));
            ipNums.removeLast();
        }
        for (int i = start; i < Math.min(start + 3, s.length()); i++) {
            if (i > start && s.charAt(start) == '0')
                continue;
            int sum = getNum(s, start, i);
            if (sum < 256 && sum >= 0 && ipNums.size() < 3 && (i + 1) < s.length()) {
                ipNums.addLast(sum);
                dfsIp(s, i + 1, ipNums);
                ipNums.removeLast();
            }
        }
    }

    private int getNum(String s, int start, int end) {
        return Integer.parseInt(s.substring(start, end + 1));
    }

    /**
     *
     * @param s
     * @return
     */
    public String[][] partition(String s) {
        res4 = new ArrayList<>();
        dfsPartition(s, 0, new LinkedList<>());
        return res4.toArray(new String[0][]);
    }

    private void dfsPartition(String s, int now, Deque<String> strList) {
        if (now == s.length()) {
            System.out.println(strList);
            res4.add(strList.toArray(new String[0]));
            return;
        }
        for (int i = now; i < s.length(); i++) {
            if (isHuiwen(s, now, i)) {
                String s1 = s.substring(now, i + 1);
                strList.addLast(s1);
                dfsPartition(s, i + 1, strList);
                strList.removeLast();
            }
        }
    }

    private boolean isHuiwen(String s, int left, int right) {
        if (s.length() == 1) {
            return true;
        }
        while (left < right) {
            if (s.charAt(left++) != s.charAt(right--)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 生成匹配的括号
     * https://leetcode-cn.com/problems/IDBivT/
     * @param n
     * @return
     */
    public List<String> generateParenthesis(int n) {
        res3 = new ArrayList<>();
        dfsParenthesis(0, 0, n, new StringBuilder());
        return res3;
    }

    private void dfsParenthesis(int leftCount, int rightCount, int n, StringBuilder sb) {
        if (leftCount == n && rightCount == n) {
            // 结束
            res3.add(sb.toString());
        }
        System.out.println(leftCount + ", " + rightCount + ", " + sb.toString());
        // 可以添加左括号
        if (leftCount < n) {
            sb.append('(');
            dfsParenthesis(leftCount + 1, rightCount, n, sb);
            sb.deleteCharAt(sb.length() - 1);
        }
        // 添加右括号
        if (rightCount < n && rightCount < leftCount) {
            sb.append(')');
            dfsParenthesis(leftCount, rightCount + 1, n, sb);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    /**
     * 含有 k 个元素的组合
     * https://leetcode-cn.com/problems/uUsW3B/
     * @param n
     * @param k
     * @return
     */
    public List<List<Integer>> combine(int n, int k) {
        int num, count;
        List<Integer> indexes = new ArrayList<>(), numsList;
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < Math.pow(2, n); i++) {
            num = i;
            count = 0;
            while (num != 0) {
                count += num & 1;
                num >>>= 1;
            }
            if (count == k) {
                indexes.add(i);
            }
        }
        for (int i : indexes) {
            numsList = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) > 0) {
                    numsList.add(j + 1);
                }
            }
            res.add(numsList);
        }
        return res;
    }

    /**
     * 允许重复选择元素的组合
     * https://leetcode-cn.com/problems/Ygoe9J/
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        res1 = new ArrayList<>();
        dfsCombinationSum(candidates, 0, 0, target, new LinkedList<>());
        return res1;
    }

    private void dfsCombinationSum(int[] candidates, int index, int sum, int target, Deque<Integer> numsList) {
        if (sum == target) {
            res1.add(new ArrayList<>(numsList));
            return;
        }

        for (int i = index; i < candidates.length; i++) {
            int nowSum = sum + candidates[i];
            if (nowSum > target) {
                continue;
            }
            numsList.addLast(candidates[i]);
            // 不往下移动index
            dfsCombinationSum(candidates, i, nowSum, target, numsList);
            numsList.removeLast();
        }
    }


    /**
     * 含有重复元素集合的组合
     * https://leetcode-cn.com/problems/4sjJUc/
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        res1 = new ArrayList<>();
        dfsCombinationSum2(candidates, 0, target, 0, new LinkedList<>());
        return res1;
    }

    private void dfsCombinationSum2(int[] candidates, int index, int target, int sum, Deque<Integer> temp) {
        if (sum == target) {
            res1.add(new ArrayList<>(temp));
            return;
        }
        for (int i = index; i < candidates.length; i++) {
            int nowSum = sum + candidates[i];
            if (nowSum > target) {
                return;
            }
            temp.addLast(candidates[i]);
            dfsCombinationSum2(candidates, i + 1, target, nowSum, temp);
            temp.removeLast();
            //避免重复答案，下一个是相同元素就跳过，注意别数组越界
            while (i < candidates.length - 1 && candidates[i] == candidates[i + 1]) {
                i++;
            }
        }
    }

    /**
     * 含有重复元素集合的组合
     * https://leetcode-cn.com/problems/4sjJUc
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<Integer> numList;
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < Math.pow(2, nums.length); i++) {
            numList = new ArrayList<>();
            for (int j = 0; j < nums.length; j++) {
                if ((i & (1 << j)) > 0) {
                    numList.add(nums[j]);
                }
            }
            res.add(numList);
        }
        return res;
    }

    /**
     * 没有重复元素集合的全排列
     * https://leetcode-cn.com/problems/VvJkup
     * @param nums
     * @return
     */
    public List<List<Integer>> permute(int[] nums) {
        res1 = new ArrayList<>();
        make(nums, 0);
        return res1;
    }

    /**
     * 含有重复元素集合的全排列
     * https://leetcode-cn.com/problems/7p8L0Z/
     * @param nums
     * @return
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        res2 = new HashSet<>();
        make(nums, 0);
        return new ArrayList<>(res2);
    }

    // 对m位数的全排列函数(分治思想)
    private void make(int[] list, int current)
    {
        if (current == list.length - 1) // 等于m-1时，只剩下m-1位置上的那个数需要排列了，只有一种结果
        {
            // 此时拿到了一组经过排列的1-9的值，存放在list数组中
            List<Integer> nums = new ArrayList<>();
            for (int i = 0; i < list.length; i++)
                nums.add(list[i]);
            res2.add(nums);
        }
        // 从第current位到第m-1位上的数字进行全排列
        else
        {
            // 当前排列就是一组，直接向下对current+1位及之后的数字进行全全排列
            make(list, current + 1);
            // 否则把当前位和后面的各个位数逐一作交换，形成新的全排列，分治思想
            for (int i = current + 1; i < list.length; i++)
            {
                // 不重复判断逻辑
                if (list[current] != list[i]) {
                    // 交换list[i]和list[current]
                    int temp = list[current];
                    list[current] = list[i];
                    list[i] = temp;
                    // 对current+1位及之后的数字进行全全排列
                    make(list, current + 1);
                    // 还原
                    temp = list[current];
                    list[current] = list[i];
                    list[i] = temp;
                }
            }
        }
    }
}
