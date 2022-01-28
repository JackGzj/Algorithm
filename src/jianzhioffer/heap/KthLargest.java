package jianzhioffer.heap;

import java.util.PriorityQueue;

/**
 * 数据流的第 K 大数值
 * https://leetcode-cn.com/problems/jBjn9C/
 */
public class KthLargest {
    int size;
    PriorityQueue<Integer> queue;

    public static void main(String[] args) {
        KthLargest kthLargest = new KthLargest(3, new int[]{4, 5, 8, 2});
        System.out.println(kthLargest.add(3));
        System.out.println(kthLargest.add(5));
        System.out.println(kthLargest.add(10));
        System.out.println(kthLargest.add(9));
        System.out.println(kthLargest.add(4));
        System.out.println("===========");
        Test test = new Test(3, new int[]{4, 5, 8, 2});
        System.out.println(test.add(3));
        System.out.println(test.add(5));
        System.out.println(test.add(10));
        System.out.println(test.add(9));
        System.out.println(test.add(4));
    }

    public KthLargest(int k, int[] nums) {
        size = k;
        queue = new PriorityQueue<>();
        for (int n : nums) {
            add(n);
        }
    }

    public int add(int val) {
        queue.add(val);
        if (queue.size() > size) {
            queue.poll();
        }
        System.out.println(queue);
        return queue.peek();
    }

    static class Test {
        PriorityQueue<Integer> pq;
        int k;

        public Test(int k, int[] nums) {
            this.k = k;
            pq = new PriorityQueue<Integer>();
            for (int x : nums) {
                add(x);
            }
        }

        public int add(int val) {
            pq.offer(val);
            if (pq.size() > k) {
                pq.poll();
            }
            System.out.println(pq);
            return pq.peek();
        }
    }
}
