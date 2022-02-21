package algorithms;

import java.util.Scanner;

public class QS
{
    private static int ans = -1;
    public static void main(String[] args)
    {
        int len = 0;
        Scanner sc = new Scanner(System.in);
        len = sc.nextInt();
        int[] a = new int[len];
        int index = (len + 1) / 2 - 1;
        for (int i = 0; i < len; i++)
        {
            a[i] = sc.nextInt();
        }

        quickSort(a, 0, len - 1, index);
        System.out.println(ans);
        sc.close();
    }

    static void quickSort(int n[], int low, int high, int index)
    {
        if (low > high)
            return;
        int first = low, last = high, key = n[low];
        while (first < last)
        {
            while (first < last && n[last] >= key)
                last--;
            n[first] = n[last];
            while (first < last && n[first] <= key)
                first++;
            n[last] = n[first];
        }
        n[first] = key;
//        System.out.println("===============");
//        for (int i = low; i <= high; i++)
//        {
//            System.out.print(n[i] + " ");
//        }
//        System.out.println();
//        System.out.println(key + " first: " + first + " lats: " + last +  " low: " + low + " high: " + high + ", " + index);
        if (first == index)
        {
            ans = key;
        }
        else if (first > index)
        {
            // 排序之后，查看枢纽元的位置。如果它的位置大于K，就说明，要求出前面一个子序列的第K大的元素
            quickSort(n, low, first - 1, index);
        }
        else {
            // 如果小于K，就说明要求出在后面一个序列的第K - 前一个序列的长度个元素。
            quickSort(n, first + 1, high, index);
        }
        // System.out.println(low + ", " + high + ", " + key + ", "  + first);
    }

}
