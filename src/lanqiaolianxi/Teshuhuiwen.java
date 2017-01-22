package lanqiaolianxi;

import java.util.Scanner;

public class Teshuhuiwen
{
    public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		sc.close();
        for (int i = 10000; i < 999999; i++)
        {
        	int[] num = new int[6];
        	int temp = i, j = 0, sum = 0, wei;
        	while(temp > 0)
        	{
        		num[j] = temp % 10;
        		temp /= 10;
        		sum += num[j];
        		j++;
        	}
        	if (i < 100000)
        		wei = 5;
        	else 
        		wei = 6;
        	for (j = 0; j < wei; j++)
        	{
        		if (num[j] == num[wei - j - 1] && j < wei / 2)
        		{
        			continue;
        		}
        		break;
        	}
        	if (j >= wei / 2 && sum == n)
        	{
        		System.out.println(i);
        	}
        }
	}
}