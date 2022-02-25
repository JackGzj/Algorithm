package jianzhioffer.search_sort;


import jianzhioffer.linkedlist.ListNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeSet;

public class SearchSortTimu {

    public static void main(String[] args) {
        SearchSortTimu searchSortTimu = new SearchSortTimu();
//        System.out.println(searchSortTimu.findKthLargest(new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6}, 4));
        ListNode node3 = new ListNode(3), node1 = new ListNode(1), node2 = new ListNode(2), node4 = new ListNode(4);
        node4.next = node2;
        node2.next = node1;
        node1.next = node3;
        searchSortTimu.sortList(node4);
    }

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) {
            return null;
        }
        // 暴力
//        ListNode head = lists[0];
//        for (int i = 1; i < lists.length; i++) {
//            head = merge(head, lists[i]);
//        }
        // 归并
        return splitAndMerge(lists, 0, lists.length - 1);
    }

    private ListNode splitAndMerge(ListNode[] lists, int start, int end) {
        if (start == end) {
            return lists[start];
        } else if (start < end) {
            int mid = (start + end) / 2;
            return merge(splitAndMerge(lists, start, mid), splitAndMerge(lists, mid + 1, end));
        } else {
            return null;
        }
    }

    /**
     * 链表排序
     * https://leetcode-cn.com/problems/7WHec2/
     * @param head
     * @return
     */
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode head1 = head, head2 = split(head);
        head1 = sortList(head1);
        head2 = sortList(head2);
        return merge(head1, head2);
    }

    private ListNode split(ListNode head) {
        ListNode fast = head.next, slow = head, mid;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }
        mid = slow.next;
        slow.next = null;
        return mid;
    }

    private ListNode merge(ListNode head1, ListNode head2) {
        ListNode mergeHead = new ListNode(0), ptr = mergeHead;
        while (head1 != null && head2 != null) {
            if (head1.val < head2.val) {
                ptr = ptr.next = new ListNode(head1.val);
                head1 = head1.next;
            } else {
                ptr = ptr.next = new ListNode(head2.val);
                head2 = head2.next;
            }
        }
        if (head1 != null) {
            ptr.next = head1;
        }
        if (head2 != null) {
            ptr.next = head2;
        }
        return mergeHead.next;
    }

    /**
     * 数组中的第 k 大的数字
     * https://leetcode-cn.com/problems/xx4gT2/
     *
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int i = 0; i < nums.length; i++) {
            if (queue.size() == k) {
                if (nums[i] > queue.peek()) {
                    queue.poll();
                    queue.add(nums[i]);
                }
            } else {
                queue.add(nums[i]);
            }
        }
        return queue.peek();
    }

    /**
     * 数组相对排序
     * https://leetcode-cn.com/problems/0H97ZC/
     *
     * @param arr1
     * @param arr2
     * @return
     */
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        Map<Integer, Integer> frequency = new HashMap<>();
        for (int i = 0; i < arr1.length; i++) {
            frequency.put(arr1[i], frequency.getOrDefault(arr1[i], 0) + 1);
        }
        System.out.println(frequency);
        int index = 0;
        int[] res = new int[arr1.length];
        for (int i = 0; i < arr2.length; i++) {
            for (int j = 0; j < frequency.get(arr2[i]); j++) {
                res[index++] = arr2[i];
            }
            frequency.remove(arr2[i]);
        }
        TreeSet<Integer> set = new TreeSet<>(frequency.keySet());
        for (int i : set) {
            for (int j = 0; j < frequency.get(i); j++) {
                res[index++] = i;
            }
        }
        for (int i = 0; i < res.length; i++) {
            System.out.print(res[i] + " ");
        }
        System.out.println();
        return res;
    }

    /**
     * 合并区间
     * https://leetcode-cn.com/problems/SsGoHC/
     *
     * @param intervals
     * @return
     */
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (o1, o2) -> o1[0] - o2[0]);
        int[] now;
        List<int[]> merge = new ArrayList<>();
        merge.add(intervals[0]);
        for (int i = 1; i < intervals.length; i++) {
            now = merge.get(merge.size() - 1);
            if (intervals[i][0] >= now[0] && intervals[i][0] <= now[1]) {
                // 左区间在当前区间中，可以并入
                now[1] = Math.max(intervals[i][1], now[1]);
            } else {
                merge.add(intervals[i]);
            }
        }
        for (int[] i : merge) {
            System.out.println(i[0] + ", " + i[1]);
        }
        return merge.toArray(new int[merge.size()][]);
    }

    /**
     * 查找插入位置
     * https://leetcode-cn.com/problems/N6YdxV
     *
     * @param nums
     * @param target
     * @return
     */
    public int searchInsert(int[] nums, int target) {
        int left = 0, right = nums.length - 1, mid;
        while (left <= right) {
            mid = (left + right) / 2;
            System.out.println(left + ", " + right);
            if (nums[mid] > target) {
                // search left
                right = mid - 1;
            } else if (nums[mid] < target) {
                // search rigth
                left = mid + 1;
            } else {
                return mid;
            }
        }
        return left;
    }

    /**
     * 狒狒吃香蕉
     * https://leetcode-cn.com/study-plan/lcof/?progress=2173wos
     *
     * @param piles
     * @param h
     * @return
     */
    public int minEatingSpeed(int[] piles, int h) {
        int min = 1, max = Arrays.stream(piles).max().getAsInt(), mid;
        if (h == piles.length) {
            return max;
        }
        System.out.println(min + ", " + max);
        while (min <= max) {
            mid = (min + max) / 2;
            if (countTimes(piles, mid) <= h) {
                max = mid - 1;
            } else {
                // 吃不完，速度要提升
                min = mid + 1;
            }
        }
        return min;
    }

    private int countTimes(int[] piles, int k) {
        int sum = 0;
        for (int i = 0; i < piles.length; i++) {
            sum += piles[i] / k + (piles[i] % k > 0 ? 1 : 0);
        }
        System.out.println(k + ": " + sum);
        return sum;
    }

    /**
     * https://leetcode-cn.com/problems/jJ0w9p/solution/
     *
     * @param x
     * @return
     */
    public int mySqrt(int x) {
        int left = 1, right = x, mid;
        while (left <= right) {
            mid = left + (right - left) / 2;
            System.out.println(mid);
            if (x / mid >= mid) {
                if (x / (mid + 1) < (mid + 1)) {
                    return mid;
                }
                left = mid + 1;
            } else {
                // 太大了，左移
                right = mid - 1;
            }
        }
        return 0;
    }
}
