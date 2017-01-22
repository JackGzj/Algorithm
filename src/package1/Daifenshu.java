package package1;

import java.util.Scanner;
/**
 * 
 * @author lenovo
 * 心得：回溯中的搜索排列树
 */
//带分数
public class Daifenshu
{
	public static int N; // 待分解的数字N
	public static int bit = 0; // N的位数
	public static int count = 0; // 带分数的个数，答案

	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		// 读入N
		N = sc.nextInt();
		// 求N的位数，存入bit
		int temp = N;
		while (temp != 0)
		{
			bit++;
			temp /= 10;
		}
		// 对1-9数组全排列
		int[] list = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		make(list, 0, 9);
		// 输出答案
		System.out.println(count);
		sc.close();
	}

	// 对m位数的全排列函数(分治思想)
	public static void make(int[] list, int current, int m)
	{
		if (current == m - 1) // 等于m-1时，只剩下m-1位置上的那个数需要排列了，只有一种结果
		{
			// 此时拿到了一组经过排列的1-9的值，存放在list数组中
			int a, b, c; // 假设N=a+b/c
			// 首先确定a，a最多是bit位的数
			for (int i = 0; i < bit; i++)
			{// i代表a的末尾位置的下标
				a = getnum(list, 0, i);
				// 再确定b，c至少要1位所以b的末尾下标取不到8
				for (int j = i + 1; j < 8; j++)
				{
					b = getnum(list, i + 1, j);
					// b确定了，c也就确定了
					c = getnum(list, j + 1, 8);
					// 检查a,b,c的取值是否符合题意
					if (b % c == 0 && (N - a) * c == b)
					{
						count++;
					}
				}

			}
		}
		// 从第current位到第m-1位上的数字进行全排列
		else
		{
			// 当前排列就是一组，直接向下对current+1位及之后的数字进行全全排列
			make(list, current + 1, m);
			// 否则把当前位和后面的各个位数逐一作交换，形成新的全排列，分治思想
			for (int i = current + 1; i < m; i++)
			{
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

	// 将list的from到to位拼成数字
	public static int getnum(int[] list, int from, int to)
	{
		int sum = 0;
		for (int i = from; i <= to; i++)
		{
			sum = list[i] + sum * 10;
		}
		return sum;
	}
}

