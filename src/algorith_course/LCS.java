package algorith_course;

import java.util.Scanner;

public class LCS
{
	private static StringBuffer subs = new StringBuffer();
	public static void main(String[] args)
	{
		System.out.println("请输入两个字符串");
		Scanner sc = new Scanner(System.in);
		String source = sc.nextLine(), target = sc.nextLine();
		sc.close();
		int sourceLen = source.length(), targetLen = target.length(), i, j;
		int[][] dp = new int[targetLen + 1][sourceLen + 1];
		// 状态矩阵初始化
		for (i = 0; i < targetLen; i++)
		{
			dp[i][0] = 0;
		}

		for (j = 0; j < sourceLen; j++)
		{
			dp[0][j] = 0;
		}

		for (j = 1; j <= sourceLen; j++)
		{
			for (i = 1; i <= targetLen; i++)
			{
				if (target.charAt(i -  1) == source.charAt(j - 1))
				{
					dp[i][j] = dp[i - 1][j - 1] + 1;
				}
				else
				{
					dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
				}
			}
		}

		/*for (i = 0; i <= targetLen; i++)
		{
			for (j = 0; j <= sourceLen; j++)
			{
				System.out.print(dp[i][j] + " ");
			}
			System.out.println();
		}*/
		
		print(dp, target, source, target.length(), source.length());
		System.out.println(subs.reverse());
	}

	// 递归打印最长子序列
	// i行，j列
	public static void print(int[][] opt, String X, String Y, int i, int j)
	{
		if (i == 0 || j == 0)
		{
			return;
		}

		if (X.charAt(i - 1) == Y.charAt(j - 1))
		{
			// System.out.print(X.charAt(i - 1));
			subs.append(X.charAt(i - 1));
			print(opt, X, Y, i - 1, j - 1); 
		}
		else if (opt[i - 1][j] >= opt[i][j])
		{
			print(opt, X, Y, i - 1, j);
		}
		else
		{
			print(opt, X, Y, i, j - 1);
		}
	}
}
