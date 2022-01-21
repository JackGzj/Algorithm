package jianzhioffer.tree;

import jianzhioffer.queue.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class Timu {
    int sum;

    public static void main(String[] args) {
        TreeNode node4 = new TreeNode(7, null, null);
        TreeNode node3 = new TreeNode(15, null, null);
        TreeNode node2 = new TreeNode(20, node3, node4);
        TreeNode root = new TreeNode(-10, null, node2);
        Timu timu = new Timu();
        TreeNode tn = timu.increasingBST(root);
        while (tn != null) {
            System.out.print(tn + " ");
            tn = tn .right;
        }
        System.out.println();
        System.out.println();
//        System.out.println(codec.pathSumT(root, 3));
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
