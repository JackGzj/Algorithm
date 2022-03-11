package hot_100;

import java.util.*;

public class Timu_To_80 {
    public static void main(String[] args) {
        System.out.println(exist(new char[][]{{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}}, "CEF"));
    }

    static List<List<Integer>> ans = new ArrayList<>();

    static boolean findFlag = false;
    static int[][] directions = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    /**
     * 79. 单词搜索
     * https://leetcode-cn.com/problems/word-search/
     * @param board
     * @param word
     * @return
     */
    public static boolean exist(char[][] board, String word) {
        boolean[][] visited;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == word.charAt(0)) {
                    visited = new boolean[board.length][board[0].length];
                    visited[i][j] = true;
                    dfsBoard(board, visited, i, j, 1, word);
                }
            }
        }
        return findFlag;
    }

    public static void dfsBoard(char[][] board, boolean[][] visited, int row, int col, int index, String target) {
        if (findFlag || index == target.length()) {
            findFlag = true;
            return;
        }
        for (int[] dir : directions) {
            int newR = row + dir[0], newC = col + dir[1];
            if (newR >= 0 && newR < board.length && newC >= 0 && newC < board[0].length && !visited[newR][newC] &&
                    board[newR][newC] == target.charAt(index)) {
                visited[newR][newC] = true;
                dfsBoard(board, visited, newR, newC, index + 1, target);
                visited[newR][newC] = false;
            }
        }
    }

    /**
     * 76. 最小覆盖子串
     * https://leetcode-cn.com/problems/minimum-window-substring/
     * note: 经典字符串匹配 + 滑窗
     * @param s
     * @param t
     * @return
     */
    public static String minWindow(String s, String t) {
        Map<Character, Integer> sourceCount = new HashMap<>(), targetCount = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            targetCount.put(t.charAt(i), targetCount.getOrDefault(t.charAt(i), 0) + 1);
        }
        int left = 0, right;
        String res = "";
        for (right = 0; right < s.length(); right++) {
            System.out.println(left + ", " + right + ", " + res);
            sourceCount.put(s.charAt(right), sourceCount.getOrDefault(s.charAt(right), 0) + 1);
            while (match(sourceCount, targetCount)) {
                if (res.equals("") || res.length() > (right - left + 1)) {
                    res = s.substring(left, right + 1);
                }
                sourceCount.put(s.charAt(left), sourceCount.getOrDefault(s.charAt(left), 0) - 1);
                left ++;
            }
        }
        return res;
    }

    public static boolean match(Map<Character, Integer> sourceCount, Map<Character, Integer> targetCount) {
        for (Map.Entry<Character, Integer> entry : targetCount.entrySet()) {
            if (sourceCount.getOrDefault(entry.getKey(), 0) < entry.getValue()) {
                return false;
            }
        }
        return true;
    }

    /**
     * 75. 颜色分类
     * https://leetcode-cn.com/problems/sort-colors/
     * note：一个指针指向放 0 的位置，一个指向放 1 的位置
     * @param nums
     */
    public void sortColors(int[] nums) {
        int p0 = 0, p1 = 0 , temp;
        for (int i = 0; i < nums.length; i++) {
             if (nums[i] == 1) {
                temp = nums[i];
                nums[i] = nums[p1];
                nums[p1] = temp;
                p1++;
            } else if (nums[i] == 0) {
                temp = nums[i];
                nums[i] = nums[p0];
                nums[p0] = temp;
                if (p0 < p1) {
                    // 这时候把1换走了，要换回来
                    temp = nums[i];
                    nums[i] = nums[p1];
                    nums[p1] = temp;
                }
                p0++;
                p1++;
            }
        }
    }

    /**
     * 72. 编辑距离
     * https://leetcode-cn.com/problems/edit-distance/
     * note：编辑距离概念理解，状态转换（插入一个、删除一个、替换一个）
     * 不要单纯想字符串层面的问题，要往状态转换去想，即做每个操作对当前状态和目标状态的影响
     * @param word1
     * @param word2
     * @return
     */
    public static int minDistance(String word1, String word2) {
        int len1 = word1.length(), len2 = word2.length(), leftTop;
        int[][] dp = new int[len1 + 1][len2 + 1];
        for (int i = 0; i <= len1; i++) {
            dp[i][0] = i;
        }
        for (int i = 0; i <= len2; i++) {
            dp[0][i] = i;
        }
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                leftTop = dp[i - 1][j - 1] + (word1.charAt(i - 1) == word2.charAt(j - 1) ? 0 : 1);
                dp[i][j] = Math.min(dp[i - 1][j] + 1, Math.min(dp[i][j - 1] + 1, leftTop));
            }
        }
        return dp[len1][len2];
    }

    /**
     * 56. 合并区间
     * https://leetcode-cn.com/problems/merge-intervals/
     * note: 先排序再合并
     * @param intervals
     * @return
     */
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (o1, o2) -> o1[0] - o2[0]);
        int[] range = new int[2];
        List<int[]> ans = new ArrayList<>();
        for (int[] selective : intervals) {
            if (ans.isEmpty() || ans.get(ans.size())[1] < selective[0]) {
                ans.add(new int[]{selective[0], selective[1]});
            } else {
                ans.get(ans.size() - 1)[1] = Math.max(ans.get(ans.size() - 1)[1], selective[1]);
            }
        }
        return ans.toArray(new int[ans.size()][]);
    }

    /**
     * 55. 跳跃游戏
     * https://leetcode-cn.com/problems/jump-game/
     * note: 对于从某个格子出发的情况，能调到最远则近的肯定也能跳到，因此可以用贪心。注意判断当前点能不能跳到！不要无脑推进循环
     * 该解法比较蠢，标记了每个格子能不能跳到，可以参考下答案：标记最大能跳到哪个格子
     * 时间差很远！14% vs 94%
     * @param nums
     * @return
     */
    public static boolean canJump(int[] nums) {
//        boolean[] can = new boolean[nums.length];
//        can[0] = true;
//        for (int i = 0; i < nums.length; i++) {
//            for (int j = 1; j <= nums[i]; j++) {
//                if (can[i] && i + j < nums.length && !can[i + j]) {
//                    can[i + j] = true;
//                    if (i + j == nums.length - 1) {
//                        return true;
//                    }
//                }
//            }
//        }
//        return can[nums.length - 1];
        int rightMax = 0, n = nums.length;
        for (int i = 0; i < n; i++) {
            if (i <= rightMax) {
                rightMax = Math.max(i + nums[i], rightMax);
                if (rightMax >= n - 1) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 46. 全排列
     * https://leetcode-cn.com/problems/permutations/
     * @param nums
     * @return
     */
    public static List<List<Integer>> permute(int[] nums) {
        List<Integer> list = new ArrayList<>();
        for (int i : nums) {
            list.add(i);
        }
        dfsPermute(list, 0);
        return ans;
    }

    public static void dfsPermute(List<Integer> nums, int index) {
        if (index == nums.size()) {
            ans.add(nums);
            System.out.println(nums);
        }
        System.out.println(nums + ", " + index);
        for (int i = index; i < nums.size(); i++) {
            Collections.swap(nums, i, index);
            dfsPermute(nums, index + 1);
            Collections.swap(nums, i, index);
        }
    }

    /**
     * 42. 接雨水
     * https://leetcode-cn.com/problems/trapping-rain-water/
     * @param height
     * @return
     */
    public int trap(int[] height) {
        int ans = 0, top, left;
        Deque<Integer> stack = new LinkedList<>();
        for (int i = 0; i < height.length; i++) {
            while (!stack.isEmpty() && height[stack.peek()] < height[i]) {
                top = stack.pop();
                if (stack.isEmpty()) {
                    break;
                }
                left = stack.peek();
                ans += (Math.min(height[left], height[i]) - height[top]) * (i - left - 1);
            }
            stack.push(i);
        }
        return ans;
    }
}
