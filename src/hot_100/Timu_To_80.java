package hot_100;

import java.util.*;

public class Timu_To_80 {
    public static void main(String[] args) {

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
     * 42. 接雨水
     * https://leetcode-cn.com/problems/trapping-rain-water/
     * @param height
     * @return
     */
    public int trap(int[] height) {
        int ans = 0, top, left;
        Deque<Integer> stack = new LinkedList<>();
        for (int i = 0; i < height.length; i++) {
            while (!stack.isEmpty() && height[stack.peek()] < height[i]) {
                top = stack.pop();
                if (stack.isEmpty()) {
                    break;
                }
                left = stack.peek();
                ans += (Math.min(height[left], height[i]) - height[top]) * (i - left - 1);
            }
            stack.push(i);
        }
        return ans;
    }
}
