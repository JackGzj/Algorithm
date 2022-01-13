package juesai2013;

public class Tiansuanshi {
    static int ans = 0;

    public static void main(String[] args) {
        int[] list = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        make(list, 0, 10);
        System.out.println(ans);
    }

    public static void make(int[] list, int current, int m) {
        if (current == m - 1) {
            int a, b, c;
            a = getNum(list, 0, 3);
            b = getNum(list, 4, 7);
            c = getNum(list, 8, 9);
            if (a < 1000 || b < 1000 || c < 10) {
                return;
            }
            if ((a - b) * c == 900) {
                ans++;
                System.out.println(a + " " + b + " " + c);
            }
        } else {
            make(list, current + 1, m);
            for (int i = current + 1; i < m; i++) {
                // 交换list[i]和list[current]
                int temp = list[current];
                list[current] = list[i];
                list[i] = temp;
                // 对current+1位及之后的数字进行全全排列
                make(list, current + 1, m);
                // 还原
                temp = list[current];
                list[current] = list[i];
                list[i] = temp;
            }
        }
    }

    public static int getNum(int[] list, int from, int to) {
        int sum = 0;
        for (int i = from; i <= to; i++) {
            sum = list[i] + sum * 10;
        }
        return sum;
    }

}
