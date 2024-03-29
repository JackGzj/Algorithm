package jianzhioffer.tree;

import jianzhioffer.queue.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class TreeTimu {
    int sum;

    public static void main(String[] args) {
        TreeTimu treeTimu = new TreeTimu();
        System.out.println(treeTimu.minimumLengthEncoding(new String[]{"time", "atime", "btime"}));
    }

    /**
     * 最短的单词编码
     * https://leetcode-cn.com/problems/iSwD2y/
     * @param words
     * @return
     */
    public int minimumLengthEncoding(String[] words) {
        String reverse;
        Set<Trie> suffixSet = new HashSet<>();
        int res = 0, flag;
        for (String w : words) {
            boolean search = false;
            reverse = new StringBuffer(w).reverse().toString();
            for (Trie trie : suffixSet) {
                flag = trie.startsWithOrContains(reverse);
                if (flag == 0) {
                    search = true;
                } else if (flag > 0) {
                    System.out.println("meet contents: " + w + ", " + flag);
                    trie.insertAndRemovePrefix(reverse);
                    res += (w.length() - flag);
                    search = true;
                }
            }
            if (!search) {
                System.out.println("put: " + w);
                Trie t = new Trie();
                t.insert(reverse);
                suffixSet.add(t);
                res += w.length() + 1;
            }
        }
        return res;
    }

    /**
     * 二叉搜索树中两个节点之和
     * https://leetcode-cn.com/problems/opLdQZ/
     * @param root
     * @param k
     * @return
     */
    public boolean findTarget(TreeNode root, int k) {
        List<TreeNode> inorderArr = new ArrayList<>();
        inorder(root, inorderArr);
        // 不想改 inorder 函数的实现，因此做了转换
        Set<Integer> set = inorderArr.stream().map(n -> n.val).collect(Collectors.toSet());
        for (int num : set) {
            if (set.contains(k - num) && k - num != num) {
                return true;
            }
        }
        return false;
    }

    /**
     * 剑指 Offer II 054. 所有大于等于节点的值之和
     * https://leetcode-cn.com/problems/w6cpku/
     * @param root
     * @return
     */
    public TreeNode convertBST(TreeNode root) {
        if (root == null) {
            return null;
        }
        // 从右边开始遍历（逆向）
        convertBST(root.right);
        sum += root.val;
        root.val = sum;
        convertBST(root.left);
        return root;
    }

    /**
     * 剑指 Offer II 053. 二叉搜索树中的中序后继
     * https://leetcode-cn.com/problems/P5rCT8
     * @param root
     * @param p
     * @return
     */
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        TreeNode node = root, res = null;
        while (node != null) {
            if (node.val > p.val) {
                res = node;
                node = node.left;
            } else {
                node = node.right;
            }
        }
        return res;
    }

    /**
     * 剑指 Offer II 052. 展平二叉搜索树
     * https://leetcode-cn.com/problems/NYBBNL/
     * @param root
     * @return
     */
    public TreeNode increasingBST(TreeNode root) {
        List<TreeNode> res = new ArrayList<>();
        inorder(root, res);
        TreeNode node = res.get(0), tmp = node;
        for (int i = 1; i < res.size(); i++) {
            tmp.right = new TreeNode(res.get(i).val);
            tmp = tmp.right;
        }
        return node;
    }

    public void inorder(TreeNode root, List<TreeNode> res) {
        if (root == null) {
            return;
        }
        inorder(root.left, res);
        res.add(root);
        inorder(root.right, res);
    }

    /**
     * 从根节点到叶节点的路径数字之和
     * https://leetcode-cn.com/problems/3Etpl5/
     *
     * @param root
     * @return
     */
    public int sumNumbers(TreeNode root) {
        return dfsSumNum(root, 0);
    }

    public int dfsSumNum(TreeNode node, int preSum) {
        if (node == null) {
            return 0;
        }
        int sum = preSum * 10 + node.val;
        if (node.left == null && node.right == null) {
            return sum;
        } else {
            return dfsSumNum(node.left, sum) + dfsSumNum(node.right, sum);
        }
    }

    /**
     * 向下的路径节点之和
     * https://leetcode-cn.com/problems/6eUYwP/
     *
     * @param root
     * @param targetSum
     * @return
     */
    public int pathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return 0;
        }
        int res = rootPathSum(root, targetSum);
        res += pathSum(root.left, targetSum);
        res += pathSum(root.right, targetSum);
        return res;
    }

    public int rootPathSum(TreeNode node, int targetSum) {
        int res = 0;
        if (node == null) {
            return 0;
        }
        System.out.println(node + ", " + targetSum);
        if (targetSum == node.val) {
            System.out.println("equals!");
            res++;
        }
        res += rootPathSum(node.left, targetSum - node.val);
        res += rootPathSum(node.right, targetSum - node.val);
        return res;
    }
}
