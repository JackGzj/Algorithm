package jianzhioffer.queue;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

public class CBTInserter {
    TreeNode root;
    Deque<TreeNode> deque;

    public static void main(String[] args) {
        TreeNode node9 = new TreeNode(1), node5 = new TreeNode(4), node3_1 = new TreeNode(5);
        TreeNode node3 = new TreeNode(3, node5, node3_1), node2 = new TreeNode(2, null, null);
        TreeNode root = new TreeNode(1, node2, node3);
        System.out.println(serialize(root));
        System.out.println(serializeT(root));
    }

    // Encodes a tree to a single string.
    public static String serialize(TreeNode root) {
        List<String> res = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        res.add(Integer.toString(root.val));
        TreeNode node;
        while (!queue.isEmpty()) {
            node = queue.poll();
            System.out.println(node);
            if (node.left != null) {
                queue.add(node.left);
                res.add(Integer.toString(node.left.val));
            } else {
                res.add("null");
            }
            if (node.right != null) {
                queue.add(node.right);
                res.add(Integer.toString(node.right.val));
            } else {
                res.add("null");
            }
        }
        return res.stream().collect(Collectors.joining(","));
    }

    public static String serializeT(TreeNode root) {
        if(root == null) {
            return null;
        }
        return serialize(root, new StringBuilder()).toString();
    }
    public static StringBuilder serialize(TreeNode root, StringBuilder sb) {
        if(root == null) {
            sb.append("null,");
            return sb;
        }
        sb.append(root.val + ",");
        sb = serialize(root.left, sb);
        sb = serialize(root.right, sb);
        return sb;
    }

    // Decodes your encoded data to tree.
//    public TreeNode deserialize(String data) {
//
//    }

    public static TreeNode pruneTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        root.left = pruneTree(root.left);
        root.right = pruneTree(root.right);
        if (root.val == 0 && root.left == null && root.right == null) {
            return null;
        }
        return root;
    }

    public static List<Integer> rightSideView(TreeNode root) {
        ArrayList<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        TreeNode node;
        int nowCount = 1, nextCount = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            node = queue.poll();
            if (node.left != null) {
                queue.add(node.left);
                nextCount ++;
            }
            if (node.right != null) {
                queue.add(node.right);
                nextCount ++;
            }
            nowCount --;
//            System.out.println("Node: " + node + ", " + nowCount + ", " + nextCount);
            if (nowCount == 0) {
                // 当前层遍历完
                // 下推
                nowCount = nextCount;
                nextCount = 0;
                res.add(node.val);
//                System.out.println("finish: " + node);
            }
        }
        return res;
    }

    /**
     * 剑指 Offer II 045. 二叉树最底层最左边的值
     * https://leetcode-cn.com/problems/LwUNpT
     * @param root
     * @return
     */
    public static int findBottomLeftValue(TreeNode root) {
        boolean startNext = false;
        TreeNode node, res = root;
        int nowCount = 1, nextCount = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            node = queue.poll();
            if (startNext) {
                res = node;
                startNext = false;
            }
            if (node.left != null) {
                queue.add(node.left);
                nextCount ++;
            }
            if (node.right != null) {
                queue.add(node.right);
                nextCount ++;
            }
            nowCount --;
//            System.out.println("Node: " + node + ", " + nowCount + ", " + nextCount);
            if (nowCount == 0) {
                // 当前层遍历完
                // 下推
                nowCount = nextCount;
                nextCount = 0;
                startNext = true;
//                System.out.println("finish: " + node);
            }
        }
        return res.val;
    }

    /**
     * 剑指 Offer II 044. 二叉树每层的最大值
     * https://leetcode-cn.com/problems/hPov7L
     * @param root
     * @return
     */
    public static List<Integer> largestValues(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        int nowCount = 1, nextCount = 0, max = Integer.MIN_VALUE;
        TreeNode node;
        Queue<TreeNode> queue = new LinkedList<>();
        List<Integer> res = new ArrayList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            node = queue.poll();
            max = Math.max(max, node.val);
            if (node.left != null) {
                queue.add(node.left);
                nextCount ++;
            }
            if (node.right != null) {
                queue.add(node.right);
                nextCount ++;
            }
            nowCount --;
//            System.out.println("Node: " + node + ", " + nowCount + ", " + nextCount);
            if (nowCount == 0) {
                // 当前层遍历完
                res.add(max);
                // 下推
                nowCount = nextCount;
                nextCount = 0;
                max = Integer.MIN_VALUE;
//                System.out.println("finish: " + node);
            }
        }
        return res;
    }

    /**
     * 剑指 Offer II 043. 往完全二叉树添加节点
     * https://leetcode-cn.com/problems/NaqhDT/
     * @param root
     */
    public CBTInserter(TreeNode root) {
        this.root = root;
        deque = new LinkedList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        TreeNode node;
        while (!queue.isEmpty()) {
            node = queue.poll();
            if (node.left == null || node.right == null) {
                // 还有空的子节点，放入deque
                deque.add(node);
            }
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }
    }

    public int insert(int v) {
        TreeNode node = deque.peek();
        TreeNode son = new TreeNode(v);
        deque.add(son);
        if (node.left == null) {
            node.left = son;
        } else {
            node.right = son;
            deque.pollFirst();
        }
        return node.val;
    }

    public TreeNode get_root() {
        return root;
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }

        @Override
        public String toString() {
            return "TreeNode{" + "val=" + val + '}';
        }
    }
}
