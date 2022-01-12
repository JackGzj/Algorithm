package algorith_course;

import java.util.Scanner;

public class NQueens
{
	private static int[] chessboard;// 当前解
	private static int N;// 皇后个数
	private static int sum = 0;// 当前已找到的可行方案数

	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		while (true)
		{
			System.out.println("请输入皇后个数，以0结束");
			int n = sc.nextInt();
            if (n <= 0)
            {
            	break;
            }
			
			System.out.println("回溯法求解：");
			long start = System.currentTimeMillis();
			System.out.println("可行解个数 : " + totalNQueensbackTrack(n));
			long end = System.currentTimeMillis();
			System.out.println("用时 : " + (end - start) + "毫秒");
			System.out.println("=============================");

			System.out.println("迭代法求解：");
			start = System.currentTimeMillis();
			System.out.println("可行解个数 : " + totalNQueensIteration(n));
			end = System.currentTimeMillis();
			System.out.println("用时 : " + (end - start) + "毫秒");
			System.out.println("=============================");

		}
		sc.close();
	}

	// 初始化各变量，开始回溯
	public static int totalNQueensbackTrack(int n)
	{
		N = n;
		chessboard = new int[N + 1];
		backTrace(1);
		return sum;
	}

	public static int totalNQueensIteration(int n)
	{
		sum = 0;
		N = n;
		chessboard = new int[N + 1];
		iteration();
		return sum;
	}

	// col行 x[col]列处是否能放置一个皇后
	private static boolean place(int col)
	{
		for (int i = 1; i < col; i++)
		{
			if (Math.abs(col - i) == Math.abs(chessboard[col] - chessboard[i]) || chessboard[col] == chessboard[i])
			{
				return false;
			}
		}
		return true;
	}

	// 回溯
	private static void backTrace(int t)
	{
		if (t > N)
		{
		    System.out.println(chessboard);
			sum++;
		}
		else
		{
			// 第t行，遍历所有的节点
			for (int i = 1; i <= N; i++)
			{
				chessboard[t] = i;
				// 如果第j个节点可以放下皇后
				if (place(t))
				{
					// 接着放下一个
					backTrace(t + 1);
				}
			}
		}
	}

	// 迭代
	private static void iteration()
	{
		int k = 1;
		while (k > 0)
		{
			chessboard[k] += 1;
			while ((chessboard[k] <= N) && !(place(k)))
			{
				chessboard[k] += 1;
			}
			if (chessboard[k] <= N)
			{
				if (k == N)
				{
					sum++;
				}
				else
				{
					k++;
					chessboard[k] = 0;
				}
			}
			else
			{
				k--;
			}
		}

	}

}
