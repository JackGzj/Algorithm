package package2;

public class Shenqisuanshi
{
    static int ans = 0;
	public static void main(String[] args)
	{
		long start = System.currentTimeMillis();
		for (int i = 1000; i < 9999; i++)
		{
			check(i);
		}
		long end = System.currentTimeMillis();
		System.out.println(ans/2);
		System.out.println(end - start);
	}
 
	static void check(int n)
	{
		int[] num = new int[4];
		int number = n, index = 3;
		while (number > 0)
		{
			num[index] = number % 10;
			number /= 10;
			index --;
		}
		for (int i = 0; i < 4; i++)
			for(int j = i + 1; j < 4; j++)
			{
				if (num[i] == num[j])
					return;
			}
		make(num, 0, 4, n);
	}
	
	static void make(int[] list, int current, int m, int tempnum)
	{
		int first = 0, second = 0;
		if (current == m - 1)
		{
			for (int i = 0; i < m; i++)
			{
				first = getnum(list, 0, i);
				second = getnum(list, i + 1, 3);
				if (first * second == tempnum)
				{
					ans ++;
					// System.out.println(first + " " + second + " " + tempnum);
				}
			}
		}
		else
		{
			make (list, current + 1, m, tempnum);
			for (int i = current + 1; i < m; i++)
			{
				int temp = list[current];
				list[current] = list[i];
				list[i] = temp;
				make (list, current + 1, m, tempnum);
				temp = list[current];
				list[current] = list[i];
				list[i] = temp;
			}
		}
	}
	
	static int getnum(int[] list, int begin, int end)
	{
		int sum = 0;
		for (int i = begin; i <= end; i++)
		{
			sum = list[i] + sum * 10;
		}
		return sum;
	}
}
