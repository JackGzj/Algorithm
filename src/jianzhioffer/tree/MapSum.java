package jianzhioffer.tree;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class MapSum {
    TrieVal root;

    public static void main(String[] args) {
        MapSum mapSum = new MapSum();
        mapSum.insert("apple", 3);
        System.out.println(mapSum.sum("ap"));
        mapSum.insert("app", 2);
        System.out.println(mapSum.sum("ap"));
    }

    /** Initialize your data structure here. */
    public MapSum() {
        root = new TrieVal();
    }

    public void insert(String key, int val) {
        root.insert(key, val);
    }

    public int sum(String prefix) {
        return bfsTriePrefix(root.searchPrefix(prefix));
    }

    public int bfsTriePrefix(TrieVal node) {
        if (node == null) {
            return 0;
        }
        Queue<TrieVal> queue = new LinkedList<>();
        queue.add(node);
        int res = 0;
        TrieVal n;
        while (!queue.isEmpty()) {
            n = queue.poll();
            res += n.val;
            for (int i = 0; i < 26; i++) {
                if (n.children[i] != null) {
                    queue.add(n.children[i]);
                }
            }
        }
        return res;
    }

    class TrieVal {
        TrieVal[] children;
        boolean isEnd;
        int val;

        /**
         * 实现前缀树
         * https://leetcode-cn.com/problems/QC3q1f
         */
        /**
         * Initialize your data structure here.
         */
        public TrieVal() {
            children = new TrieVal[26];
            isEnd = false;
            val = 0;
        }

        /**
         * Inserts a word into the trie.
         */
        public void insert(String word, int val) {
            TrieVal node = this;
            for (int i = 0; i < word.length(); i++) {
                int index = word.charAt(i) - 'a';
                if (node.children[index] == null) {
                    node.children[index] = new TrieVal();
                }
                node = node.children[index];
            }
            node.isEnd = true;
            node.val = val;
        }

        private TrieVal searchPrefix(String prefix) {
            TrieVal node = this;
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
}
