package package1;

import java.util.Scanner;

public class Cuowufapiao
{

	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int totalLine = sc.nextInt();
		int[] num = new int[100000];
		int i, j, current = 0, lose = 0, repeat = 0;
		for (i = 0; i <= totalLine; i++)
		{
			String line = sc.nextLine();
			String[] numOfString= line.split(" ");
			for (j = 0; j < numOfString.length; j++)
			{
				try
				{
					num[current++] = Integer.valueOf(numOfString[j]);
				}
				catch (Exception e)
				{
					// TODO: handle exception
				}
			}
		}
		quickSort(num, 0, current - 1);
		for (i = 1; i < (current - 1); i++)
		{
			if(num[i] == num[i + 1])
			{
				repeat = num[i];
				continue;
			}
			if(num[i] + 2 == num[i + 1])
			{
				lose = num[i];
				continue;
			}
		}
		System.out.println((lose + 1) + " " + repeat);
		sc.close();

	}

	static void quickSort (int n[] ,int low, int high)
	{
		if (low > high)
			return;
		int left = low, right = high;
		int key = n[left];
		while (left < right)
		{
			while (left < right && n[right] >= key)
				right--;
			n[left] = n[right];
			while (left < right && n[left] <= key)
				left++;
			n[right] = n[left];
		}
		n[left] = key;
		quickSort(n, low, left - 1);
		quickSort(n, left + 1, high);
	}
}
