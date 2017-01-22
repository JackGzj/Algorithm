package juesai2013;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;

public class Wangluoxunlu
{
    static TreeMap<Integer, ArrayList<Integer>> table = new TreeMap<Integer, ArrayList<Integer>>();
    static boolean[] visit = null;
    static int ans =0;
	public static void main(String[] args)
	{
		int nodeNum, edgeNum, i;
        Scanner sc = new Scanner(System.in);
		nodeNum = sc.nextInt();
		edgeNum = sc.nextInt();
		for (i = 0; i < edgeNum; i++)
		{
			int begin = sc.nextInt(), end = sc.nextInt();
			add(begin, end);
			add(end, begin);
		}
		sc.close();
		//从nodeNum个键值对上深搜 
		for (i = 1; i <= nodeNum; i++)
		{
			visit = new boolean[nodeNum + 1];
			// 参数：当前搜到的点，搜到的是第几个点，起点位置 
			dfs(i, 0, i);
		}
		System.out.println(table);
		System.out.println(ans);
	}
	
	public static void add(int begin, int end)
	{
		ArrayList<Integer> besideNode = null;
		if (table.containsKey(begin))
		{
			besideNode = table.get(begin);
			besideNode.add(end);
			table.put(begin, besideNode);
		}
		else
		{
			besideNode = new ArrayList<Integer>();
			besideNode.add(end);
			table.put(begin, besideNode);
		}
	}

	public static void dfs(int now, int n, int start)
	{
		System.out.println("DFS:" + now + " " + n + " " +start);
		visit[now] = true;
		//一条路径有四个点，从起点s开始搜到第四次就满足一条路径(为什么！！！）
		if (n >= 3)
		{
			ans ++;
			return;
		}
		//搜到点now,取点now的邻接节点，坐标往下搜 
		ArrayList<Integer> besideNode = table.get(now);
		//往下一个点找
		for (int i = 0; i < besideNode.size(); i++)
		{
			int next = besideNode.get(i);
			//下一个点没访问过，或者下一个点就是起点了
			if (!visit[next] || (next == start && n == 2))
			{
				dfs(next, n + 1, start);
				// 当w==s时，不用 置0，因为总是从start为起点，start一直标记为访问过
				if (next != start)
				{
					visit[next] = false;
				}
			}
		}
	}
}
