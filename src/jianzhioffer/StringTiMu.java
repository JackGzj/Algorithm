package jianzhioffer;

import java.util.*;

public class StringTiMu {
    private List<String> paiLie = new ArrayList<>();

    public static void main(String[] args) {
        StringTiMu tiMu = new StringTiMu();
        System.out.println(tiMu.validPalindrome("abc"));
    }

    public boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length()) {
            return false;
        }
        int[] as1 = new int[26], as2 = new int[26];
        for (int i = 0; i < s1.length(); i++) {
            as1[s1.charAt(i) - 'a'] ++;
            as2[s2.charAt(i) - 'a'] ++;
        }
        if (Arrays.equals(as1, as2)) {
            return true;
        }
        for (int i = s1.length(); i < s2.length(); i++) {
            as2[s2.charAt(i) - 'a'] ++;
            as2[s2.charAt(i - s1.length()) - 'a'] --;
            if (Arrays.equals(as1, as2)) {
                return true;
            }
        }
        return false;
    }

    public List<Integer> findAnagrams(String s, String p) {
        Set<Integer> result = new HashSet<>();
        if (p.length() > s.length()) {
            return new ArrayList<>(result);
        }
        int[] as1 = new int[26], as2 = new int[26];
        for (int i = 0; i < p.length(); i++) {
            as1[p.charAt(i) - 'a'] ++;
            as2[s.charAt(i) - 'a'] ++;
        }
        if (Arrays.equals(as1, as2)) {
            result.add(0);
        }
        for (int i = p.length(); i < s.length(); i++) {
            as2[s.charAt(i) - 'a'] ++;
            as2[s.charAt(i - p.length()) - 'a'] --;
//            for (int j = 0; j < p.length(); j++) {
//                System.out.println(as1[j] + ", " + as2[j]);
//            }
//            System.out.println(i);
            if (Arrays.equals(as1, as2)) {
//                System.out.println("equals: " + i + ", " + (i - p.length()));
//                for (int j = 0; j < p.length(); j++) {
//                    System.out.println(as1[j] + ", " + as2[j]);
//                }
                result.add(Math.min(i + 1 - p.length(), i));
            }
        }
        return new ArrayList<>(result);
    }

    public int lengthOfLongestSubstring(String s) {
        int res = 0, left = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))) {
                left = Math.max(left, map.get(s.charAt(i)) + 1);
            }
            map.put(s.charAt(i), i);
            res = Math.max(i - left + 1, res);
        }
        return res;
    }

    public String minWindow(String s, String t) {
        Map<Character, Integer> indexMap = new HashMap<>(), targetsCount = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            targetsCount.put(t.charAt(i), targetsCount.getOrDefault(t.charAt(i), 0) + 1);
        }
        int l = 0, r;
        String res = "";
        for (r = 0; r < s.length(); r++) {
            indexMap.put(s.charAt(r), indexMap.getOrDefault(s.charAt(r), 0) + 1);
            while (match(indexMap, targetsCount)) {
                System.out.println(l + ", " + r + ", " + res);
                // 缩小窗口
                if (res.length() == 0 || (r - l) < res.length()) {
                    res = s.substring(l, r + 1);
                }
                indexMap.put(s.charAt(l), indexMap.getOrDefault(s.charAt(l), 0) - 1);
                l++;
            }
        }
        return res;
    }

    public boolean match(Map<Character, Integer> indexMap, Map<Character, Integer> targetsCount) {
        for (Map.Entry<Character, Integer> entry : targetsCount.entrySet()) {
            if (indexMap.getOrDefault(entry.getKey(), 0) < entry.getValue()) {
                return false;
            }
        }
        return true;
    }

    public int countSubstrings(String s) {
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            for (int j = 1; j < s .length() - i + 1; j++) {
                String a = s.substring(i, i + j);
//                System.out.println(i + ", " + j + ", " + a);
                if (judgeHuiWen(a)) {
                    res ++;
                    System.out.println(a);
                }
            }
        }
        return res;
    }

    public boolean isPalindrome(String s) {
        char c;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            c = s.charAt(i);
            if (('A' <= c && 'Z' >= c) || ('a' <= c && 'z' >= c) || ('0' <= c && '9' >= c)) {
                sb.append(c);
            }
        }
        return judgeHuiWen(sb.toString().toLowerCase());
    }

    public boolean validPalindrome(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (judgeHuiWen(s.substring(0, i) + s.substring(i + 1, s.length()))) {
                return true;
            }
        }
        return false;
    }

    public boolean judgeHuiWen(String s) {
        for (int i = 0; i < s.length() / 2; i++) {
            if (s.charAt(i) != s.charAt(s.length() - 1 - i)) {
                return false;
            }
        }
        return true;
    }

    public void pl(List<Character> s, List<Character> rs) {
        if (s.size() == 1) {
            rs.add(s.get(0));
            StringBuilder sb = new StringBuilder();
            for (Character c : rs) {
                sb.append(c);
            }
            paiLie.add(sb.toString());
            rs.remove(rs.size() - 1);
        } else {
            for (int i = 0; i < s.size(); i++) {
                rs.add(s.get(i));
                List<Character> tmp = new ArrayList<>();
                tmp.addAll(s);
                tmp.remove(i);
                pl(tmp, rs);
                rs.remove(rs.size() - 1);
            }
        }
    }
}
