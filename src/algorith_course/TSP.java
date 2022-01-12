package algorith_course;

import java.util.Scanner;

public class TSP
{

	public static void main(String args[])
	{
		Scanner sc = new Scanner(System.in);
		int n = 0;// 结点的个数
		String line = sc.nextLine();// 读入n
		n = Integer.parseInt(line);
		a = new float[n][n];
		int[] vv = new int[n];

		for (int i = 0; i < n; i++)
		{
			line = sc.nextLine();
			String[] sArray = line.split(" ");
			for (int j = 0; j < sArray.length; j++)
			{
				a[i][j] = Integer.parseInt(sArray[j]);
			}
		}
		System.out.println(bbTsp(vv));
		sc.close();
	}

	static float[][] a;// 图的邻接矩阵
	// static float a[][]={{-1,-1,-1,2},{2,-1,-1,-1},{1,3,-1,-1},{-1,-1,1,-1}};
	// static float a[][]={{-1,30,6,4},{30,-1,5,10},{6,5,-1,20},{4,10,20,-1}};
	// static float a[][]={{5,5,5,5},{5,5,5,5},{5,5,5,5},{5,5,5,5}};

	@SuppressWarnings("rawtypes")
	private static class HeapNode implements Comparable
	{
		// 子树费用下界, 当前费用, X[s:n-1]中顶点最小出边费用和
		float lcost, cc, rcost;
		// 根节点到当前结点的路径为X[0:s]
		int s;
		// 需要进一步搜索的结点是x[s+1:n-1]
		int[] x;

		// HeapNode的构造函数
		HeapNode(float lc, float ccc, float rc, int ss, int[] xx)
		{
			lcost = lc;
			cc = ccc;
			s = ss;
			x = xx;
		}

		public int compareTo(Object x)
		{
			float xlc = ((HeapNode) x).lcost;
			if (lcost < xlc)
				return -1;
			if (lcost == xlc)
				return 0;
			return 1;
		}

	}

	public static int bbTsp(int[] v)
	{
		int n = v.length;
		MinHeap heap = new MinHeap(100);
		// minOut[i]是顶点i的最小出边费用
		float[] minOut = new float[n];
		// 最小出边费用和
		float minSum = 0;

		// 计算最小出边费用和
		for (int i = 0; i < n; i++)
		{
			float min = Float.MAX_VALUE;
			for (int j = 0; j < n; j++)
			{
				if (a[i][j] != -1 && a[i][j] < min)
				{
					// 有回路
					min = a[i][j];
				}
			}

			if (min == Float.MAX_VALUE)
			{
				// 无回路
				return -1;
			}

			minOut[i] = min;
			minSum += min;
		}

		// 初始化
		int[] x = new int[n];
		for (int i = 0; i < n; i++)
		{
			x[i] = i;
		}
		HeapNode enode = new HeapNode(0, 0, minSum, 0, x);
		float bestc = Float.MAX_VALUE;

		// 搜索排列空间树
		while (enode != null && enode.s < n - 1)
		{
			// System.out.println(bestc);
			x = enode.x;
			// 叶子结点
			if (enode.s == n - 2)
			{
				if (a[x[n - 2]][x[n - 1]] != -1 && a[x[n - 1]][1] != -1 || bestc == Float.MAX_VALUE)// 当前最优解还不存在的情况
				{
					bestc = enode.cc + a[x[n - 2]][x[n - 1]] + a[x[n - 1]][0];
					enode.cc = bestc;
					enode.lcost = bestc;
					enode.s++;
					heap.put(enode);
				}
			} 
			else
			{
				for (int i = enode.s + 1; i < n; i++)
				{
					// 可行儿子结点
					if (a[x[enode.s]][x[i]] != -1)
					{
						float cc = enode.cc + a[x[enode.s]][x[i]];
						float rcost = enode.rcost - minOut[x[enode.s]];
						float b = cc + rcost;
						if (b < bestc)
						{
							int[] xx = new int[n];
							for (int j = 0; j < n; j++)
								xx[j] = x[j];
							xx[enode.s + 1] = x[i];
							xx[i] = x[enode.s + 1];
							HeapNode node = new HeapNode(b, cc, rcost, enode.s + 1, xx);
							heap.put(node);
						} 
					} 
				}
			}

			enode = (HeapNode) heap.removeMin();
		}
		for (int i = 0; i < n; i++)
		{
			v[i] = x[i];
		}
		return (int) bestc;
	}

	// 构造最小堆
	public static class MinHeap
	{
		// 堆容器
		private HeapNode[] heapArray; 
		// 堆的最大大小
		private int maxSize; 
		// 堆大小
		private int currentSize = 0; 

		// 构造函数
		public MinHeap(int _maxSize)
		{
			maxSize = _maxSize;
			heapArray = new HeapNode[maxSize];
			currentSize = 0;
		}

		// 自上而下调整
		public void filterDown(int start, int endOfHeap)
		{
			int i = start;
			int j = 2 * i + 1; // j是i的左子女位置
			HeapNode temp = heapArray[i];

			// 检查是否到最后位置
			while (j <= endOfHeap)
			{ 
				// 让j指向两子女中的小者
				if (j < endOfHeap 
						&& heapArray[j].cc > heapArray[j + 1].cc)
				{
					j++;
				}
				// 小则不做调整
				if (temp.cc <= heapArray[j].cc)
				{ 
					break;
				}
				// 否则小者上移，i，j下降
				else
				{ 
					heapArray[i] = heapArray[j];
					i = j;
					j = 2 * j + 1;
				}
			}
			heapArray[i] = temp;
		}

		// 自下而上的调整:从结点start开始到0为止，自下向上比较，如果子女的值小于双亲结点的值则互相交换
		public void filterUp(int start)
		{
			int j = start;
			int i = (j - 1) / 2;
			HeapNode temp = heapArray[j];

			// 沿双亲结点路径向上直达根节点
			while (j > 0)
			{ 
				// 双亲结点值小，不调整
				if (heapArray[i].cc <= temp.cc)
				{
					break;
				}
				// 双亲结点值大，调整
				else
				{
					heapArray[j] = heapArray[i];
					j = i;
					i = (i - 1) / 2;
				}
				// 回送
				heapArray[j] = temp; 
			}
		}

		// 插入结点
		public void put(HeapNode node)
		{
			HeapNode newNode = node;
			heapArray[currentSize] = newNode;
			filterUp(currentSize);
			currentSize++;

		}

		// 删除堆中的最小值
		public HeapNode removeMin()
		{
			HeapNode root = heapArray[0];
			heapArray[0] = heapArray[currentSize - 1];
			currentSize--;
			filterDown(0, currentSize - 1);
			return root;
		}
	}

}
