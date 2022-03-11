package hot_100;

import jianzhioffer.queue.TreeNode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Timu_To_140 {
    public static void main(String[] args) {
        TreeNode node5 = new TreeNode(2, null, null);
        TreeNode node4 = new TreeNode(2, null, null);
        TreeNode node3 = new TreeNode(2, node5, null);
        TreeNode node2 = new TreeNode(2, node4, null);
        TreeNode root = new TreeNode(1, node2, node3);
        System.out.println(isSymmetric(root));
    }

    /**
     * 139. 单词拆分
     * https://leetcode-cn.com/problems/word-break/
     * note: DP!!!!
     * @param s
     * @param wordDict
     * @return
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        Set<String> wordSet = new HashSet<>(wordDict);
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordSet.contains(s.substring(j, i))) {
                    dp[i] = true;
                }
            }
        }
        return dp[s.length()];
    }

    int maxValue = Integer.MIN_VALUE;

    /**
     * 124. 二叉树中的最大路径和
     * https://leetcode-cn.com/problems/binary-tree-maximum-path-sum/
     * note: 注意贡献值与最终答案的关系
     * @param root
     * @return
     */
    public int maxPathSum(TreeNode root) {
        maxPathDfs(root);
        return maxValue;
    }

    public int maxPathDfs(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int leftContribution = Math.max(maxPathDfs(node.left), 0);
        int rightContribution = Math.max(maxPathDfs(node.right), 0);
        maxValue = Math.max(leftContribution + rightContribution + node.val, maxValue);
        return node.val + Math.max(leftContribution, rightContribution);
    }


    HashMap<Integer, Integer> index = new HashMap<>();

    /**
     * 105. 从前序与中序遍历序列构造二叉树
     * https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
     * note: 前序遍历确定根节点，然后用中序遍历确定左右子树，递归建树
     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int len = inorder.length;
        for (int i = 0; i < len; i++) {
            index.put(inorder[i], i);
        }
        return buildTreeDfs(preorder, 0, len - 1, inorder, 0, len - 1);
    }

    public TreeNode buildTreeDfs(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd) {
        if (preStart > preEnd) {
            return null;
        }
        int preRoot = preStart, inRoot = index.get(preorder[preStart]);
        int leftSubTreeSize = inRoot - inStart;
        TreeNode root = new TreeNode(preorder[preRoot]);
        root.left = buildTreeDfs(preorder, preRoot + 1, preStart + leftSubTreeSize, inorder, inStart, inRoot - 1);
        root.right = buildTreeDfs(preorder, preStart + leftSubTreeSize + 1, preEnd, inorder, inRoot + 1, inEnd);
        return root;
    }

    /**
     * 102. 二叉树的层序遍历
     * https://leetcode-cn.com/problems/binary-tree-level-order-traversal/
     * note：不需要记录层数，因为下一层的节点只能由上一层的节点扩展得到！
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Deque<TreeNode> queue = new LinkedList<>();
        queue.addLast(root);
        List<Integer> level;
        TreeNode node;
        while(!queue.isEmpty()) {
            int tmpSize = queue.size();
            level = new ArrayList<>();
            for (int i = 0; i < tmpSize; i++) {
                node = queue.pollFirst();
                level.add(node.val);
                if (node.left != null) {
                    queue.addLast(node.left);
                }
                if (node.right != null) {
                    queue.addLast(node.right);
                }
            }
            res.add(level);
        }
        return res;
    }

    class TreeLevelNode {
        TreeNode node;
        int level;
        public TreeLevelNode(TreeNode node, int level) {
            this.node = node;
            this.level = level;
        }
    }

    /**
     * 101. 对称二叉树
     * https://leetcode-cn.com/problems/symmetric-tree/solution/
     * node: 直观看如果是一颗对称二叉树，那中序遍历必定是回文数组。但是但是！这是个必要不充分条件，
     * 中序遍历是回文数组的二叉树不一定是对称二叉树！
     * 所以分隔左右子树遍历，左子树和右子树移动方向相反。
     * @param root
     * @return
     */
    public static boolean isSymmetric(TreeNode root) {
        return isSymmetricDfs(root, root);
    }

    public static boolean isSymmetricDfs(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }
        if (left == null || right == null) {
            return false;
        }
        return isSymmetricDfs(left.left, right.right) && isSymmetricDfs(left.right, left.left);
    }

    static boolean flag = true;

    /**
     *
     * @param root
     * @return
     */
    public static boolean isValidBST(TreeNode root) {
        inorderDfsBST(root, new ArrayList<>(1000));
        return flag;
    }

    public static void inorderDfsBST(TreeNode node, List<Integer> arr) {
        if (node != null) {
            inorderDfsBST(node.left, arr);
            if (arr.isEmpty()) {
                arr.add(node.val);
            } else if (arr.get(arr.size() - 1) < node.val) {
                arr.add(node.val);
            } else {
//                System.out.println(arr + ", " + node.val);
                flag = false;
                return;
            }
            inorderDfsBST(node.right, arr);
        }
    }

    /**
     * 85. 最大矩形
     * https://leetcode-cn.com/problems/maximal-rectangle/
     * @param matrix
     * @return
     */
    public static int maximalRectangle(char[][] matrix) {
        int res = 0, row = matrix.length, col = matrix[0].length;
        int[][] arr = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                arr[i][j] = matrix[i][j] - '0';
                if (i > 0 && arr[i][j] > 0) {
                    arr[i][j] += arr[i - 1][j];
                }
            }
        }
        for (int i = 0; i < row; i++) {
            res = Math.max(res, largestRectangleArea(arr[i]));
        }
        return res;
    }

    /**
     * 84. 柱状图中最大的矩形
     * https://leetcode-cn.com/problems/largest-rectangle-in-histogram/
     * @param heights
     * @return
     */
    public static int largestRectangleArea(int[] heights) {
        int res = 0;
        Deque<Integer> stack = new LinkedList<>();
        stack.addLast(-1);
        for (int i = 0; i < heights.length; i++) {
            while (stack.peekLast() != -1 && heights[stack.peekLast()] >= heights[i]) {
//                System.out.println(i + ", " + stack.peekLast());
                res = Math.max(res, heights[stack.pollLast()] * (i- stack.peekLast() - 1));
            }
            stack.addLast(i);
        }
        while (stack.peekLast() != -1) {
//            System.out.println(heights.length + ", " + stack.peekLast());
            res = Math.max(res, heights[stack.pollLast()] * (heights.length- stack.peekLast() - 1));
//            System.out.println(res);
        }
        return res;
    }
}
