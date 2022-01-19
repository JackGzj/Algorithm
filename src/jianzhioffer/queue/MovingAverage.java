package jianzhioffer.queue;

import java.util.ArrayDeque;

public class MovingAverage {
    int size;
    ArrayDeque<Integer> queue = new ArrayDeque<>();

    public MovingAverage(int size) {
        this.size = size;
    }

    public double next(int val) {
        if (queue.size() == size) {
            queue.pollFirst();
        }
        queue.add(val);
        double sum = 0;
        for (Integer i : queue) {
            System.out.print(i + " ");
            sum += i;
        }
        System.out.println();
        System.out.println(sum / queue.size());
        return sum / queue.size();
    }

    public static void main(String[] args) {
        MovingAverage ma = new MovingAverage(3);
        ma.next(1);
        ma.next(10);
        ma.next(3);
        ma.next(5);
        ma.next(8);
    }
}
