package algorithms;

import java.util.Scanner;

public class QS
{

	public static void main(String[] args)
	{
		int len = 0;
		Scanner sc = new Scanner(System.in);
		len = sc.nextInt();
		int[] a = new int[len];
		for (int i = 0; i < len; i++)
		{
			a[i] = sc.nextInt();
		}
		
		quickSort(a, 0, len - 1);
		
		for (int i = 0; i < len; i++)
		{
			if (i < len - 1)
			{
				System.out.print(a[i] + " ");
			}
			else
			{
				System.out.print(a[i]);
			}
		}
		sc.close();
	}

	
	static void quickSort (int n[], int low, int high)
	{
		if (low > high)
			return;
		
		int first = low, last = high, key = n[low];
		while(first < last)
		{
			while (first < last && n[last] >= key)
				last--;
			n[first] = n[last];
			while (first < last && n[first] <= key)
				first++;
			n[last] = n[first];	
		}
		n[first] = key;
		quickSort(n, low, first - 1);
		quickSort(n, first + 1, high);
	}
	
}
