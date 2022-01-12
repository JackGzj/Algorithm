package algorith_course;

import java.util.Scanner;

public class BinarySearch
{
	public static void main(String[] args)
	{
		int[] a = { 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25 };
		Scanner sc = new Scanner(System.in);
		
		while (sc.hasNext())
		{
			int left = 0, rigth = a.length - 1, middle = (left + rigth) / 2, num;
			num = sc.nextInt();
			
			if (num > a[rigth] || num < a[left])
			{
				System.out.println("0");
			}
			else
			{
				while (a[middle] != num)
				{
					if (a[middle] > num)
					{
						rigth = middle;
						middle = (left + rigth) / 2;
					}
					else
					{
						left = middle;
						middle = (left + rigth) / 2;
					}
				}
				System.out.println("index : " + (middle + 1));
			}
		}
		sc.close();
	}
}
