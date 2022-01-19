package jianzhioffer;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

public class StackTimu {
    public static void main(String[] args) {
        System.out.println(maximalRectangle(new String[]{"01","10"}));
    }

    /**
     * 剑指 Offer II 036. 后缀表达式
     * https://leetcode-cn.com/problems/8Zf90G/
     * @param tokens
     * @return
     */
    public static int evalRPN(String[] tokens) {
        int a, b;
        Deque<Integer> stack = new ArrayDeque<>();
        for (String s : tokens) {
            if (s.equals("+")) {
                a = stack.pop();
                b = stack.pop();
                stack.push(b + a);
            } else if (s.equals("-")) {
                a = stack.pop();
                b = stack.pop();
                stack.push(b - a);
            } else if (s.equals("*")) {
                a = stack.pop();
                b = stack.pop();
                stack.push(b * a);
            } else if (s.equals("/")) {
                a = stack.pop();
                b = stack.pop();
                stack.push(b / a);
            } else {
                stack.push(Integer.parseInt(s));
            }
            System.out.println(stack.getFirst());
        }
        return stack.pop();
    }

    /**
     * 剑指 Offer II 038. 每日温度
     * https://leetcode-cn.com/problems/iIQa4I/
     * @param temperatures
     * @return
     */
    public static int[] dailyTemperatures(int[] temperatures) {
        int[] res = new int[temperatures.length];
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < temperatures.length; i++) {
            if (stack.isEmpty() || temperatures[stack.getFirst()] >= temperatures[i]) {
                stack.push(i);
            } else if (!stack.isEmpty() && temperatures[stack.getFirst()] < temperatures[i]) {
                while(!stack.isEmpty() && temperatures[stack.getFirst()] < temperatures[i]) {
                    res[stack.peek()] = i - stack.pop();
                }
                stack.push(i);
            }
        }
        for (int i = 0; i < res.length; i++)
            System.out.print(res[i] + " ");
        System.out.println();
        return res;
    }

    /**
     * 剑指 Offer II 037. 小行星碰撞
     * https://leetcode-cn.com/problems/XagZNi/
     * @param asteroids
     * @return
     */
    public static int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> s = new Stack<>();
        int p = 0;
        while (p < asteroids.length) {
            if (s.empty() || s.peek() < 0 || asteroids[p] > 0) {
                s.push(asteroids[p]);
            } else if (s.peek() <= -asteroids[p]) {
                // 被当前星球撞过去了
                if (s.pop() < -asteroids[p]) {
                    continue;
                }
            }
            p++;
        }
        int[] ret = new int[s.size()];
        for (int i = ret.length - 1; i >= 0; i--) {
            ret[i] = s.pop();
        }
        return ret;
    }

    /**
     * 剑指 Offer II 039. 直方图最大矩形面积
     * https://leetcode-cn.com/problems/0ynMMM/
     * @param heights
     * @return
     */
    public static int largestRectangleArea(int[] heights) {
        int res = 0, p;
        // 栈存的是数组左侧位点（以长方形视角看数组）
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        for (int i = 0; i <= heights.length - 1; i++) {
            // 出栈
            while (stack.peek() != -1 && heights[i] <= heights[stack.peek()]) {
                p = stack.pop();
                // 计算面积
                res = Math.max(res, (i - stack.peek() - 1) * heights[p]);
            }
            stack.push(i);
        }
        // 栈里还有值的情况
        while (stack.peek() != -1) {
            p = stack.pop();
            // 计算面积
            res = Math.max(res, (heights.length - stack.peek() - 1) * heights[p]);
        }
        return res;
    }

    /**
     * 剑指 Offer II 040. 矩阵中最大的矩形
     * https://leetcode-cn.com/problems/PLYXKQ/
     * @param matrix
     * @return
     */
    public static int maximalRectangle(String[] matrix) {
        if (matrix.length == 0) {
            return 0;
        }
        int[][] arr = new int[matrix.length][matrix[0].length()];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length(); j++) {
                arr[i][j] = matrix[i].charAt(j) - '0';
                if (i > 0 && arr[i][j] > 0) {
                    arr[i][j] += arr[i - 1][j];
                }
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
        int res = 0;
        for (int i = 0; i < matrix.length; i++) {
            res = Math.max(res, largestRectangleArea(arr[i]));
        }
        return res;
    }
}
