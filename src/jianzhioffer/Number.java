package jianzhioffer;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
