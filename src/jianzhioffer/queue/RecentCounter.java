package jianzhioffer.queue;

import java.util.ArrayDeque;

public class RecentCounter {
    ArrayDeque<Integer> queue;

    public RecentCounter() {
        queue = new ArrayDeque<>();
    }

    public int ping(int t) {
        while (!queue.isEmpty() && queue.getFirst() < (t - 3000)) {
            queue.removeFirst();
        }
        queue.add(t);
        System.out.println(queue.size());
        return queue.size();
    }

    public static void main(String[] args) {
        RecentCounter recentCounter = new RecentCounter();
        recentCounter.ping(1);
        recentCounter.ping(100);
        recentCounter.ping(3001);
        recentCounter.ping(3002);
    }
}
