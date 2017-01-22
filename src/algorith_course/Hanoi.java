package algorith_course;

import java.util.Scanner;

public class Hanoi
{
    private static int step = 1;
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		if (n > 0)
		{
			move(n, 'A', 'B', 'C');
			System.out.println("共移动：" + step + "步");
		}
		else
		{
			System.out.println("输入有误！");
		}
		sc.close();
	}

	public static void move(int n, char source, char transfer, char target)
	{
		if (n > 0)
		{
			move(n - 1, source, target, transfer);
			System.out.println("第" + step + "步，把第" + n + "个塔从" + source + "移到" + target);
			step ++;
			move(n - 1, transfer, source, target);
		}
	}
}
