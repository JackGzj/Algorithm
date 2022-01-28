package jianzhioffer.hashtable;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 神奇的字典
 * https://leetcode-cn.com/problems/US1pGT/
 */
public class MagicDictionary {
    Map<Integer, Set<String>> dict;

    public static void main(String[] args) {
        MagicDictionary magicDictionary = new MagicDictionary();
        magicDictionary.buildDict(new String[]{"hello","hallo","leetcode","judge"});
//        System.out.println(magicDictionary.search("hello")); // 返回 False
//        System.out.println(magicDictionary.search("hallo")); // 将第二个 'h' 替换为 'e' 可以匹配 "hello" ，所以返回 True
//        System.out.println(magicDictionary.search("hell")); // 返回 False
        System.out.println(magicDictionary.search("juage")); // 返回 False
    }

    /** Initialize your data structure here. */
    public MagicDictionary() {
        dict = new HashMap<>();
    }

    public void buildDict(String[] dictionary) {
        Set<String> set;
        for (String s : dictionary) {
            set = dict.get(s.length());
            if (set == null) {
                set = new HashSet<>();
            }
            set.add(s);
            dict.put(s.length(), set);
        }
    }

    public boolean search(String searchWord) {
        Set<String> set = dict.get(searchWord.length());
        if (set == null) {
            return false;
        }

        System.out.println(set);
        for (String source : set) {
            int wrongCount = 0;
            for (int i = 0; i < searchWord.length(); i++) {
                if (searchWord.charAt(i) != source.charAt(i)) {
                    wrongCount ++;
                }
            }
            if (wrongCount == 1) {
                return true;
            }
        }
        return false;
    }
}
