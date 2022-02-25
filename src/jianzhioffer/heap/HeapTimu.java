package jianzhioffer.heap;

import java.util.*;

public class HeapTimu {


    public static void main(String[] args) {
        HeapTimu heapTimu = new HeapTimu();
        System.out.println(heapTimu.kSmallestPairs(new int[]{1,7,11}, new int[]{2,4,6}, 3));
    }

    /**
     * 出现频率最高的 k 个数字
     * https://leetcode-cn.com/problems/g5c51o
     * @param nums
     * @param k
     * @return
     */
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> timesMap = new HashMap<>();
        PriorityQueue<Map.Entry<Integer, Integer>> timesQueue = new PriorityQueue<>(
                Comparator.comparingInt(Map.Entry::getValue));
        for (int n : nums) {
            timesMap.put(n, timesMap.getOrDefault(n, 0) + 1);
        }
        for (Map.Entry<Integer, Integer> entry : timesMap.entrySet()) {
            if (timesQueue.size() == k) {
                if (entry.getValue() > timesQueue.peek().getValue()) {
                    if (timesQueue.peek().getValue() < entry.getValue()) {
                        timesQueue.poll();
                        timesQueue.add(entry);
                    }
                }
            } else {
                timesQueue.add(entry);
            }
        }
        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = timesQueue.poll().getKey();
        }
        return res;
    }

    /**
     * 和最小的 k 个数对
     * https://leetcode-cn.com/problems/qn8gGX/
     */
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        PriorityQueue<Content> pq = new PriorityQueue<>((o1, o2) -> o1.sum - o2.sum);
        for (int i = 0; i < nums1.length; i++) {
            for (int j = 0; j < nums2.length; j++) {
                pq.add(new Content(nums1[i], nums2[j]));
            }
        }
        Content c;
        List<Integer> pair;
        List<List<Integer>> res = new ArrayList<>();
        int size = Math.min(k, pq.size());
        for (int i = 0; i < size; i++) {
            pair = new ArrayList<>();
            c = pq.poll();
            pair.add(c.num1);
            pair.add(c.num2);
            res.add(pair);
        }
        return res;
    }

    class Content {
        int sum, num1, num2;
        public Content(int num1, int num2) {
            this.num1 = num1;
            this.num2 = num2;
            this.sum = num1 + num2;
        }
    }
}
