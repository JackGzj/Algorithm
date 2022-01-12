package algorith_course;

import java.util.Scanner;

public class Maxsum
{

	public static void main(String[] args)
	{
		int max = 0, b = 0;
		int[] s = null;
		Scanner sc = new Scanner(System.in);
		System.out.println("请输入数字个数");
		int n = sc.nextInt();
		s = new int[n];
		System.out.println("请依次输入" + n + "个整数");
		for (int i = 0; i < n; i++)
		{
			s[i] = sc.nextInt();
		}
		sc.close();
		
		for (int i = 0; i < s.length; ++i)
		{
			// 小于0，则越加越小
			if (b > 0)
			{
				b += s[i];
			}
			else
			{
				b = s[i];
			}
			if (b > max)
			{
				max = b;
			}
			System.out.println("b = " + b + " s[" + i + "] = " + s[i]);
		}
		System.out.println("Max sum: " + max);
	}

}
