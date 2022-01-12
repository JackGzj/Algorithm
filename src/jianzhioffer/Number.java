package jianzhioffer;

import java.math.BigInteger;
import java.util.*;

public class Number {
    public static void main(String[] args) {
        String[] a = new String[]{"bdcecbcadca","caafd","bcadc","eaedfcd","fcdecf","dee","bfedd","ffafd","eceaffa","caabe","fbdb","acafbccaa","cdc","ecfdebaafde","cddbabf","adc","cccce","cbbe","beedf","fafbfdcb","ceecfabedbd","aadbedeaf","cffdcfde","fbbdfdccce","ccada","fb","fa","ec","dddafded","accdda","acaad","ba","dabe","cdfcaa","caadfedd","dcdcab","fadbabace","edfdb","dbaaffdfa","efdffceeeb","aefdf","fbadcfcc","dcaeddd","baeb","beddeed","fbfdffa","eecacbbd","fcde","fcdb","eac","aceffea","ebabfffdaab","eedbd","fdeed","aeb","fbb","ad","bcafdabfbdc","cfcdf","deadfed","acdadbdcdb","fcbdbeeb","cbeb","acbcafca","abbcbcbaef","aadcafddf","bd","edcebadec","cdcbabbdacc","adabaea","dcebf","ffacdaeaeeb","afedfcadbb","aecccdfbaff","dfcfda","febb","bfffcaa","dffdbcbeacf","cfa","eedeadfafd","fcaa","addbcad","eeaaa","af","fafc","bedbbbdfae","adfecadcabe","efffdaa","bafbcbcbe","fcafabcc","ec","dbddd","edfaeabecee","fcbedad","abcddfbc","afdafb","afe","cdad","abdffbc","dbdbebdbb"};
        System.out.println(maxProduct(a));
    }

    public static int divide(int a, int b) {
        if (a == Integer.MIN_VALUE && b == -1) {
            return Integer.MAX_VALUE;
        }
        int flag = (a > 0) ^ (b > 0) ? -1 : 1;
        a = Math.abs(a);
        b = Math.abs(b);
        int res = 0;
        for (int i = 31; i >= 0; i--) {
            if ((a >>> i) - b >= 0) {
                a -= (b << i);
                res += (1 << i);
            }
        }
        return flag == 1 ? res : -res;
    }

    public static String addBinary(String a, String b) {
        if (a.length() < b.length()) {
            String c = a;
            a = b;
            b = c;
        }
        int lenDiff = a.length() - b.length();
        StringBuilder res = new StringBuilder();
        boolean jinwei = false;
        for (int i = b.length() - 1; i >= 0; i--) {
            int bit = (b.charAt(i) - '0') + (a.charAt(lenDiff + i) - '0') + (jinwei ? 1 : 0);
            if (bit >= 2) {
                res.append(bit - 2);
                jinwei = true;
            } else if (bit == 1) {
                res.append(1);
                jinwei = false;
            } else {
                res.append(0);
                jinwei = false;
            }
        }
        for (int i = lenDiff - 1; i >= 0; i--) {
            int bit = (a.charAt(i) - '0') + (jinwei ? 1 : 0);
            if (bit >= 2) {
                res.append(bit - 2);
                jinwei = true;
            } else if (bit == 1) {
                res.append(1);
                jinwei = false;
            } else {
                res.append(0);
                jinwei = false;
            }
        }
        if (jinwei) {
            res.append(1);
        }
        return res.reverse().toString();
    }

    public static int singleNumber(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i =  0; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 1) {
                return entry.getKey();
            }
        }
        return -1;
    }

    public static int[] countBits(int n) {
        int[] res = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            res[i] = Integer.bitCount(i);
        }
        return res;
    }

    public static int maxProduct(String[] words) {
        Map<Integer, Integer> bitMap = new HashMap<>();
        for (String word : words) {
            int mode = 0;
            for (int i = 0; i < word.length(); i ++) {
                mode |= (1 << word.charAt(i) - 'a');
            }
            if (word.length() > bitMap.getOrDefault(mode, 0)) {
                bitMap.put(mode, word.length());
            }
        }
        int result = 0;
        List<KeyValue> list = new ArrayList<>();
        bitMap.forEach((k, v) -> list.add(new KeyValue(k, v)));
        for (int i = 0; i < list.size(); i++) {
            for (int j = i + 1; j < list.size(); j++) {
                if ((list.get(i).key & list.get(j).key) == 0) {
                    result = Math.max(list.get(i).value * list.get(j).value, result);
                }
            }
        }
        return result;
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

    static class KeyValue {
        int key;
        int value;

        public KeyValue (int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    public static BigInteger binaryStrToInt(String a) {
        BigInteger ai = BigInteger.ZERO;
        for (int i = a.length() - 1; i >= 0; i--) {
            ai = ai.add(BigInteger.valueOf((long) ((a.charAt(i) - '0') * Math.pow(2, a.length() - 1 - i))));
        }
        return ai;
    }

    public static String bigIntToStr(BigInteger i) {
        BigInteger two = BigInteger.valueOf(2);
        List<Integer> integers = new ArrayList<>();
        while(i.compareTo(BigInteger.ZERO) > 0) {
            integers.add(i.mod(two).intValue());
            i = i.divide(two);
        }
        StringBuilder a = new StringBuilder();
        for (int j = integers.size() - 1; j >= 0; j--) {
            a.append(integers.get(j));
        }
        return a.toString();
    }
}
