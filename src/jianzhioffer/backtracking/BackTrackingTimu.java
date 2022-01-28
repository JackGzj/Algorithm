package jianzhioffer.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BackTrackingTimu {
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

    List<List<Integer>> res1;
    Set<List<Integer>> res2;

    public static void main(String[] args) {
        BackTrackingTimu timu = new BackTrackingTimu();
        System.out.println(timu.permuteUnique(new int[]{1,1,2,2}));
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
