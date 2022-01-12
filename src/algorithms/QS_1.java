package algorithms;

public class QS_1
{
    static void qs(int[] n, int low, int high) {
        if (low >= high) {
            return;
        }
        int i = low, j = high;
        while (i < j) {
            int key = n[j];
            while (i < j && n[i] <= key) {
                i ++;
            }
            n[j] = n[i];
            while (i < j && n[j] >= key) {
                j --;
            }
            n[i] = n[j];
            n[j] = key;
            qs(n, low, i - 1);
            qs(n, j + 1, high);
        }
    }
    
    public static void main(String[] args) {
        int[] n = {3, 1, 6, 4, 7, 5, 2, 9, 8};
        for (int i = 0; i < n.length; i++) {
            System.out.print(n[i] + " ");
        }
        System.out.println();
        qs(n, 0, n.length - 1);
        for (int i = 0; i < n.length; i++) {
            System.out.print(n[i] + " ");
        }
        System.out.println();
    }
}
