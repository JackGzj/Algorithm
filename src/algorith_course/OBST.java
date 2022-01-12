package algorith_course;

import java.util.Scanner;

public class OBST
{
	public static int N;
	public static double[] p = null;
	public static double[] q = null;
	public static double[][] w = null;
	public static double[][] e = null;
	public static int[][] root = null;

	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("请输入节点个数");
		N = sc.nextInt();

		p = new double[N + 1];
		q = new double[N + 1];
		w = new double[N + 2][N + 2];
		e = new double[N + 2][N + 2];
		root = new int[N + 2][N + 2];

		System.out.println("请依次输入搜索到" + N + "个节点的概率值P");
		p[0] = -1;
		for (int i = 1; i <= N; i++)
		{
			p[i] = sc.nextDouble();
		}

		System.out.println("请依次输入搜索到" + (N + 1) + "个虚拟键的概率值Q");
		for (int i = 0; i <= N; i++)
		{
			q[i] = sc.nextDouble();
		}
		sc.close();

		dp();
		
		System.out.println();
		System.out.println("最优二叉搜索树搜索代价=" + e[1][N]);
		
		System.out.println();
		printRoot();
		
		System.out.println("最优二叉树结构：");
		printOptimalBST(1, N, -1);
	}

	public static void dp()
	{
		for (int i = 1; i <= N + 1; ++i)
		{
			w[i][i - 1] = q[i - 1];
			e[i][i - 1] = q[i - 1];
		}

		// 由下到上，由左到右逐步计算
		for (int len = 1; len <= N; ++len)
		{
			for (int i = 1; i <= N - len + 1; ++i)
			{
				int j = i + len - 1;
				e[i][j] = Integer.MAX_VALUE;
				w[i][j] = w[i][j - 1] + p[j] + q[j];

				// 求取最小代价的子树的根
				for (int k = i; k <= j; ++k)
				{
					double temp = e[i][k - 1] + e[k + 1][j] + w[i][j];
					if (temp < e[i][j])
					{
						e[i][j] = temp;
						root[i][j] = k;
					}
				}
			}
		}
	}

	// 输出最优二叉查找树所有子树的根
	public static void printRoot()
	{
		System.out.println("各子树的根：");
		for (int i = 1; i <= N; ++i)
		{
			for (int j = 1; j <= N; ++j)
			{
				System.out.print(root[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

	// 打印最优二叉查找树的结构
	// 打印出[i,j]子树，它是根r的左子树和右子树
	public static void printOptimalBST(int i, int j, int r)
	{
		int rootChild = root[i][j];// 子树根节点
		if (rootChild == root[1][N])
		{
			// 输出整棵树的根
			System.out.println("k" + rootChild + "是根");
			printOptimalBST(i, rootChild - 1, rootChild);
			printOptimalBST(rootChild + 1, j, rootChild);
			return;
		}

		if (j < i - 1)
		{
			return;
		}
		else if (j == i - 1)// 遇到虚拟键
		{
			if (j < r)
			{
				System.out.println("d" + j + "是" + "k" + r + "的左孩子");
			}
			else
				System.out.println("d" + j + "是" + "k" + r + "的右孩子");
			return;
		}
		else// 遇到内部结点
		{
			if (rootChild < r)
			{
				System.out.println("k" + rootChild + "是" + "k" + r + "的左孩子");
			}
			else
				System.out.println("k" + rootChild + "是" + "k" + r + "的右孩子");
		}

		printOptimalBST(i, rootChild - 1, rootChild);
		printOptimalBST(rootChild + 1, j, rootChild);
	}

}
