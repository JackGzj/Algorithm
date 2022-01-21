package jianzhioffer.tree;

import jianzhioffer.Array;
import jianzhioffer.queue.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class BSTIterator {
    int iter;
    TreeNode root;
    ArrayList<TreeNode> inorderArr;

    public static void main(String[] args) {
        TreeNode node4 = new TreeNode(20, null, null);
        TreeNode node3 = new TreeNode(9, null, null);
        TreeNode node2 = new TreeNode(15, node3, node4);
        TreeNode root = new TreeNode(7, new TreeNode(3), node2);
        BSTIterator timu = new BSTIterator(root);
        System.out.println(timu.next());
        System.out.println(timu.next());
        System.out.println(timu.hasNext());
        System.out.println(timu.next());
        System.out.println(timu.hasNext());
        System.out.println(timu.next());
        System.out.println(timu.hasNext());
        System.out.println(timu.next());
        System.out.println(timu.hasNext());
    }

    public BSTIterator(TreeNode root) {
        this.iter = 0;
        this.root = root;
        this.inorderArr = new ArrayList<>();
        TreeNode node = root;
        inorder(node, inorderArr);
        System.out.println(inorderArr);
    }

    public int next() {
        return inorderArr.get(iter++).val;
    }

    public boolean hasNext() {
        return iter < inorderArr.size();
    }

    public void inorder(TreeNode root, List<TreeNode> res) {
        if (root == null) {
            return;
        }
        inorder(root.left, res);
        res.add(root);
        inorder(root.right, res);
    }
}
