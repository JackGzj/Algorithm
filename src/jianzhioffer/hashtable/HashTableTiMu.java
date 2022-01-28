package jianzhioffer.hashtable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

public class HashTableTiMu {

    public static void main(String[] args) {
        HashTableTiMu timu = new HashTableTiMu();
        System.out.println(timu.replaceWords(new ArrayList<>(Arrays.asList("a", "aa", "aaa", "aaaa")), "a aa a aaaa aaa aaa aaa aaaaaa bbb baba ababa"));
    }

    /**
     * 替换单词
     * https://leetcode-cn.com/problems/UhWRSj/
     * @param dictionary
     * @param sentence
     * @return
     */
    public String replaceWords(List<String> dictionary, String sentence) {
        TreeSet<String> prefixSet = new TreeSet<>(dictionary);
        String[] words = sentence.split(" ");
        for (int i = 0; i < words.length; i++) {
            for (String prefix : prefixSet) {
                if (words[i].startsWith(prefix)) {
                    words[i] = prefix;
                    // 找到最短前缀，break
                    break;
                }
            }
        }
        return String.join(" ", words);
    }

    public static boolean isAnagram(String s, String t) {
        if (s.length() != t.length() || s.equals(t)) {
            System.out.println("first");
            return false;
        }
        char c;
        Map<Character, Integer> indexMap = new HashMap<>(), timesMap = new HashMap<>(), timesMapT = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            c = s.charAt(i);
            indexMap.put(c, i);
            timesMap.put(c, timesMap.getOrDefault(c ,0) + 1);
        }
        Integer index;
        boolean sameIndex = true;
        for (int i = 0; i < t.length(); i++) {
            c = t.charAt(i);
            index = indexMap.get(c);
            timesMapT.put(c, timesMapT.getOrDefault(c, 0) + 1);
            if (index != null) {
                if (index != i) {
                    sameIndex = false;
                }
            } else {
                return false;
            }
        }
        if (sameIndex) {
            System.out.println("same index");
            return false;
        }
        for (Map.Entry<Character, Integer> entry : timesMap.entrySet()) {
            if (!entry.getValue().equals(timesMapT.get(entry.getKey()))) {
//                System.out.println("char num: " + entry.getKey() + ", " + entry.getValue() + ", " + timesMapT.get(entry.getKey()));
                return false;
            }
        }
        return true;
    }

    public static List<List<String>> groupAnagrams(String[] strs) {
        char[] ca;
        String k;
        List<String> sl;
        Map<String, List<String>> map = new HashMap<>();
        for (String s : strs) {
            ca = s.toCharArray();
            Arrays.sort(ca);
            k = new String(ca);
            sl = map.get(k);
            if (sl == null) {
                sl = new ArrayList<>();
            }
            sl.add(s);
            map.put(k, sl);
        }
        List<List<String>> res = new ArrayList<>();
        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            sl = new ArrayList<>();
            for (String s : entry.getValue()) {
                sl.add(s);
            }
            res.add(sl);
        }
        return res;
    }

    public static int findMinDifference(List<String> timePoints) {
        int minutes;
        String[] time;
        TreeSet<Integer> set = new TreeSet<>();
        for (String s : timePoints) {
            time = s.split(":");
            minutes = Integer.parseInt(time[0]) * 60 + Integer.parseInt(time[1]);
            if (set.contains(minutes)) {
                return 0;
            } else {
                set.add(minutes);
            }
        }
        int min = 1440, pre = -1;
        for (int i : set) {
            if (pre > -1) {
                min = Math.min(min, i - pre);
            }
            pre = i;
        }
        min = Math.min(min, 1440 - set.last() + set.first());
        System.out.println(set);
        return min;
    }

    public static boolean isAlienSorted(String[] words, String order) {
        if (words.length == 1) {
            return true;
        }
        Map<Character, Integer> charOrder = new HashMap<>();
        for (int i = 0; i < order.length(); i++) {
            charOrder.put(order.charAt(i), i);
        }
        int tmpCharIndex, nexCharIndex;
        String tmp, next;
        for (int i = 0; i < words.length - 1; i++) {
            tmp = words[i];
            next = words[i + 1];
            for (int j = 0; j < tmp.length(); j++) {
                if (j == next.length()) {
                    return false;
                }
                tmpCharIndex = charOrder.get(tmp.charAt(j));
                nexCharIndex = charOrder.get(next.charAt(j));
                if (tmpCharIndex < nexCharIndex) {
                    System.out.println(tmp + ", " + tmp.charAt(j) + ", " + next.charAt(j));
                    break;
                } else if (tmpCharIndex > nexCharIndex) {
                    System.out.println(tmp + ", " + tmp.charAt(j) + ", " + next.charAt(j) + ", false");
                    return false;
                }
            }
            System.out.println(tmp + ", end");
        }
        return true;
    }
}
