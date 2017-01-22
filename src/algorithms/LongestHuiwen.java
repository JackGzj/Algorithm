package algorithms;

import java.util.Scanner;

public class LongestHuiwen
{
	static int longestHuiwen = 1;

	public static String DP(String str, int length)
	{
		int[][] dp = new int[length][length];
		int temp, i, j, begin = 0, end = 0;
		// 初始化
		for (i = 0; i < length; i++)
		{
			dp[i][i] = 1;
		}
		// 子串长度
		for (i = 1; i < length; i++)
		{
			temp = 0;
			// 考虑所有连续的长度为i+1的子串. 该串为 str[j, j+i]
			for (j = 0; j + i < length; j++)
			{
				// 如果首尾相同
				if (str.charAt(j) == str.charAt(j + i))
				{
					temp = dp[j + 1][j + i - 1] + 2;
				}
				else
				{
					temp = Math.max(dp[j][j + i - 1], dp[j + 1][j + i]);
				}
				dp[j][j + i] = temp;
			}
		}
		// 返回串 str[0][n-1] 的结果
		longestHuiwen = dp[0][length - 1];
		for (i = length - 1; i >= 0; i--)
		{
			for (j = 0; j < length; j++)
			{
				if (dp[i][j] == longestHuiwen)
				{
                    begin = i;
					end = j;
					return str.substring(begin, end + 1);
				}
			}
		}
		/*System.out.println(begin + " " + end);
		for (i = 0; i < length; i++)
		{
			for (j = 0; j < length; j++)
			{
				System.out.print(dp[i][j] + " ");
			}
			System.out.println();
		}*/
		return null;
	}

	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		String str = sc.next();
		sc.close();
		System.out.println(DP(str, str.length()));
		System.out.println(longestHuiwen);
	}

}
