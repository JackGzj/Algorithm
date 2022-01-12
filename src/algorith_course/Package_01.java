package algorith_course;

import java.util.Scanner;

public class Package_01
{

	public static void main(String[] args)
	{
		int i;
		Scanner sc = new Scanner(System.in);
		System.out.println("请输入物品个数和背包大小");
		int n = sc.nextInt(), size = sc.nextInt();
		int[] weight = new int[n], value = new int[n];
		System.out.println("请输入" + n + "个物品重量");
		for (i = 0; i < n; i++)
		{
			weight[i] = sc.nextInt();
		}
		System.out.println("请输入" + n + "个物品重量");
		for (i = 0; i < n; i++)
		{
			value[i] = sc.nextInt();
		}
		sc.close();

		int[][] dp = new int[n + 1][size + 1];

		for (i = 1; i <= n; i++)
		{
			for (int j = 1; j <= size; j++)
			{
				if (j >= weight[i - 1])
				{
					dp[i][j] = Math.max(dp[i - 1][j - weight[i - 1]] + value[i - 1], dp[i - 1][j]);
				}
				else
				{
					dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
				}
			}
		}
		
		/*for (i = 1; i <= n; i++)
		{
			for (int j = 1; j <= size; j++)
			{
				System.out.print(dp[i][j] + " ");
			}
			System.out.println();
		}*/
		System.out.println("最大价值 : " + dp[n][size]);
	}

}
