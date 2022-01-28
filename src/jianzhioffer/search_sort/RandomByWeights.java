package jianzhioffer.search_sort;

import java.util.Random;

/**
 * 按权重生成随机数
 */
public class RandomByWeights {
    int sum;
    int[] pre;

    public RandomByWeights(int[] w) {
        pre = new int[w.length];
        pre[0] = w[0];
        sum = w[0];
        for (int i = 1; i < w.length; i++) {
            pre[i] = pre[i - 1] + w[i];
            sum += w[i];
        }
    }

    public int pickIndex() {
        int ran = new Random().nextInt(sum) + 1;
        return bs(ran);
    }

    private int bs(int num) {
        int left = 0, right = pre.length - 1, mid;
        while (left <= right) {
            mid = (left + right) / 2;
            if (pre[mid] < num) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }
}
