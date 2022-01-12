package practise2017;

import java.util.Scanner;

public class Jiaohuanpingzi
{

    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] bottle = new int[n];
        for (int i = 0; i < n; i++)
        {
            bottle[i] = sc.nextInt();
        }
        sc.close();
        int total = 0;
        for (int i = 0; i < n; i++)
        {
            if (bottle[i] != i + 1)
            {
                for (int j = i + 1; j < n; j++)
                {
                    if (bottle[j] == i + 1)
                    {
                        int temp = bottle[i];
                        bottle[i] = bottle[j];
                        bottle[j] = temp;
                        total ++;
                    }
                }
            }
        }
        System.out.println(total);
    }

}
