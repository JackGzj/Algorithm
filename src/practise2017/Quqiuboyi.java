package practise2017;

import java.util.Scanner;

public class Quqiuboyi
{
    public static int[] n = new int[3];
    public static int[] init = new int[5];
    public static int[] end = new int[1000];
    public static char[] sign = { '-', '0', '0', '+' };

    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);
        for (int i = 0; i < 3; i++)
        {
            n[i] = scan.nextInt();
        }
        int Min = Math.min(n[0], Math.min(n[1], n[2]));
        /*for (int i = 0; i < 5; i++)
        {
            init[i] = scan.nextInt();
        }*/
        scan.close();
        
        for (int i = 0; i < Min; i++)
        {
            end[i] = 2;
        }
        
        for (int i = Min; i < 10; i++)
        {
            int temp = 0;
            for (int j = 0; j < 3; j++)
            {
                if (i - n[j] < 0)
                    continue;
                else if (end[i - n[j]] == 3)
                {
                    if (n[j] % 2 != 0)
                        // 必胜时必为基数，再拿基数则平（或输）
                        temp = 1 > temp ? 1 : temp;
                } else if (end[i - n[j]] == 0)
                {
                    // 必输时为偶数，再拿偶数还是输，拿基数则平局
                    if (n[j] % 2 == 0)
                        temp = 0;
                    else
                        temp = 2 > temp ? 2 : temp;
                } else if (end[i - n[j]] == 2)
                {
                    if (n[j] % 2 == 0)
                        temp = temp > 0 ? temp : 0;// 2 > temp ? 2 : temp;
                    else
                    {
                        temp = 3;
                        System.out.println("nums:" + i + " from:" + (i - n[j]) + " get: " + n[j]);
                    }
                } else if (end[i - n[j]] == 1)
                {
                    if (n[j] % 2 == 0)
                        temp = 1 > temp ? 1 : temp;
                }
                System.out.print("nums:" + i + " from:" + (i - n[j]) + " result:" + temp + "     ");
            }
            System.out.println();
            end[i] = temp;
        }
        for (int i = 0; i < 10; i++)
        {
            System.out.print(end[i] + " ");
        }
    }
}
