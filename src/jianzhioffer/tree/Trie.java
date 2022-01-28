package jianzhioffer.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

public class Trie {
    List<String> words;
    Trie[] children;
    boolean isEnd;

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("apple");
        trie.search("apple");   // 返回 True
        trie.search("app");     // 返回 False
        trie.startsWith("app"); // 返回 True
        trie.insert("app");
        trie.search("app");     // 返回 True


    }

    /**
     * 实现前缀树
     * https://leetcode-cn.com/problems/QC3q1f
     */
    /** Initialize your data structure here. */
    public Trie() {
        children = new Trie[26];
        isEnd = false;
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        Trie node = this;
        for (int i = 0; i < word.length(); i++) {
            int index = word.charAt(i) - 'a';
            if (node.children[index] == null) {
                node.children[index] = new Trie();
            }
            node = node.children[index];
        }
        node.isEnd = true;
    }

    /** Inserts a word into the trie. */
    public void insertAndRemovePrefix(String word) {
        Trie node = this;
        for (int i = 0; i < word.length(); i++) {
            int index = word.charAt(i) - 'a';
            if (node.children[index] == null) {
                node.children[index] = new Trie();
            }
            node.isEnd = false;
            node = node.children[index];
        }
        node.isEnd = true;
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        Trie node = searchPrefix(word);
        return node != null && node.isEnd;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        return searchPrefix(prefix) != null;
    }

    /**
     * 给 《最短的单词编码》 使用的方法
     * 如果后缀树能匹配上prefix，返回0；否则返回1；
     * 如果后缀数是 prefix 的后缀，返回后缀树长度；
     * @param prefix
     * @return
     */
    public int startsWithOrContains(String prefix) {
        Trie node = this;
        int i = 0;
        boolean isEndFlag = false;
        while(node != null) {
            if (i == prefix.length()) {
                // prefix 被前缀树搜索到
                return 0;
            }
            int index = prefix.charAt(i++) - 'a';
            isEndFlag = node.isEnd;
            node = node.children[index];
        }
        return isEndFlag ? i - 1 : -1;
    }

    private Trie searchPrefix(String prefix) {
        Trie node = this;
        for (int i = 0; i < prefix.length(); i++) {
            int index = prefix.charAt(i) - 'a';
            if (node.children[index] == null) {
                return null;
            }
            node = node.children[index];
        }
        return node;
    }
}
