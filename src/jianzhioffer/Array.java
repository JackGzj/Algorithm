package jianzhioffer;

import java.math.BigInteger;
import java.util.*;

public class Array {
    private Set<List<Integer>> result = new HashSet<>();

    public static void main(String[] args) {
        Array array = new Array();
        System.out.println(array.subarraySum(new int[]{1,2,3,4,5,6}, 6));
    }

    public List<List<Integer>> threeSum(int[] nums) {
        Set<List<Integer>> res = new HashSet<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            int target = -nums[i], tail = nums.length - 1;
            for (int head = i + 1; head < nums.length; head++) {
                boolean flag = false;
//                System.out.println(i + ", " + head + ", " + tail);
                while (head < tail) {
                    if (nums[head] + nums[tail] > target) {
                        tail--;
                    } else if (nums[head] + nums[tail] < target) {
                        head++;
                    } else {
                        flag = true;
                        break;
                    }
                }
                if (flag) {
                    res.add(Arrays.asList(nums[i], nums[head], nums[tail]));
                }
            }
        }
        return new ArrayList<>(res);
    }

    public int minSubArrayLen(int target, int[] nums) {
        int head = 0, tail = 0, sum = nums[0], res = Integer.MAX_VALUE;
        while (head < nums.length) {
            if (sum < target) {
                if (tail + 1 < nums.length) {
                    sum += nums[++tail];
                } else {
                    break;
                }
            } else {
                res = Math.min(res, tail - head + 1);
                sum -= nums[head++];
            }
        }
        return res == Integer.MAX_VALUE ? 0 : res;
    }

    public int numSubarrayProductLessThanKViolate(int[] nums, int k) {
        List<BigInteger> bigList = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            bigList.add(BigInteger.valueOf(nums[i]));
        }
        int res = 0;
        BigInteger bk = BigInteger.valueOf(k);
        for (int i = 1; i <= nums.length; i++) {
            for (int j = 0; j < nums.length; j++) {
                if (j + i > nums.length) {
                    break;
                }
                BigInteger multiply = BigInteger.ONE;
//                List<Integer> com = new ArrayList<>();
                for (int l = j; l < j + i; l++) {
//                    com.add(nums[l]);
                    multiply = multiply.multiply(bigList.get(l));
                }
//                System.out.println(i + ", " + j + ". " + com);
                if (multiply.compareTo(bk) < 0) {
//                    System.out.println(multiply + ", " + com);
                    res ++;
                }
            }
        }
        return res;
    }

    public int numSubarrayProductLessThanK(int[] nums, int k) {
        if (k <= 1) {
            return 0;
        }
        int res = 0, multiply = 1, left = 0;
        for (int right = 0; right < nums.length; right++) {
            multiply *= nums[right];
            while (left <= right && multiply >= k) {
                System.out.println(left + ", " + right + ", " + multiply);
                multiply /= nums[left++];
            }
            res += right - left + 1;
        }
        return res;
    }

    public int subarraySum(int[] nums, int k) {
        Map<Integer,Integer> map = new HashMap<>();
        map.put(0, 1);
        int sum = 0;
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            count += map.getOrDefault(sum-k, 0);
            map.put(sum, map.getOrDefault(sum, 0) + 1);
            System.out.println(i + ", " + sum + ", " + map);
        }
        return count;
    }

    public int pivotIndex(int[] nums) {
        Map<Integer, Integer> sumFromLeft = new HashMap<>(), sumFromRight = new HashMap<>();
        sumFromLeft.put(0, -1);
        sumFromRight.put(0, nums.length);
        int sum = 0;
        for (int i = 1; i <= nums.length; i++) {
            sum += nums[i - 1];
            sumFromLeft.putIfAbsent(i - 1, sum);
            int sumR = 0;
            for (int j = i + 1; j < nums.length; j++) {
                sumR += nums[j];
                sumFromRight.put(j, sumR);
            }
        }
        System.out.println(sumFromLeft);
        System.out.println(sumFromRight);
        return -1;
    }
}
