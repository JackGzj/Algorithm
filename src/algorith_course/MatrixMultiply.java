package algorith_course;

import java.util.Scanner;

public class MatrixMultiply
{
	private static int numOfMatrix = 7;
	private static int[][] s;

	public static void main(String[] args)
	{
		int[] p;
		int row = 0, col = 0, index = 0;
		System.out.println("请输入矩阵个数");
		Scanner sc = new Scanner(System.in);
		numOfMatrix = sc.nextInt();
		numOfMatrix++;
		p = new int[numOfMatrix];
		System.out.println("请根据提示依次输入连乘矩阵的行、列数，确保相邻矩阵可以相乘");

		// 检查并读入矩阵的行数和列数
		for (int i = 1; i < numOfMatrix; i++)
		{
			System.out.println("请输入第" + i + "个矩阵的行、列");
			if (i == 1)
			{
				p[index++] = sc.nextInt();
				p[index++] = sc.nextInt();
			}
			else
			{
				while (true)
				{
					row = sc.nextInt();
					col = sc.nextInt();
					if (p[index - 1] != row)
					{
						System.out.println("输入参数有误，请确保相邻矩阵能相乘！");
					}
					else
					{
						p[index++] = col;
						break;
					}
				}
			}
		}
		sc.close();

		s = new int[numOfMatrix][numOfMatrix];
		System.out.println("最少需要做" + MatrixChain(p) + "次乘法");
		System.out.println("矩阵最优计算次序为：");
		Traceback(1, numOfMatrix - 1);
	}

	// 通过动态规划求矩阵连乘最优情况以及代价
	public static int MatrixChain(int[] p)
	{
		int[][] m = new int[numOfMatrix][numOfMatrix];
		for (int i = 1; i < numOfMatrix; i++)
		{
			m[i][i] = 0;
		}
		for (int r = 2; r < numOfMatrix; r++) // r为当前计算的链长（子问题规模）
		{
			for (int i = 1; i < numOfMatrix - r + 1; i++)// n-r+1为最后一个r链的前边界
			{
				// 计算前边界为r，链长为r的链的后边界
				int j = i + r - 1;
				m[i][j] = m[i + 1][j] + p[i - 1] * p[i] * p[j];// 将链ij划分为A(i) *
																// ( A[i+1:j] )

				s[i][j] = i;

				for (int k = i + 1; k < j; k++)
				{
					// 将链ij划分为( A[i:k] )* (A[k+1:j])
					int t = m[i][k] + m[k + 1][j] + p[i - 1] * p[k] * p[j];
					if (t < m[i][j])
					{
						m[i][j] = t;
						s[i][j] = k;
					}
				}
			}
		}
		return m[1][numOfMatrix - 1];
	}

	public static void Traceback(int i, int j)
	{
		if (i == j)
		{
			return;
		}
		Traceback(i, s[i][j]);
		Traceback(s[i][j] + 1, j);
		System.out.print("Multiply A" + i + "," + s[i][j] + " and A" + (s[i][j] + 1) + "," + j);
		System.out.println();
	}

}
