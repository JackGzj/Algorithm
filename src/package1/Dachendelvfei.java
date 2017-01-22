package package1;

import java.util.Scanner;

public class Dachendelvfei
{
	private static final int inf = 99999;
    private static int[][] city = null;
    private static int ans = 0;
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		// 读入城市个数
        int numOfCity = sc.nextInt();
        city = new int[numOfCity][numOfCity];
        // 初始化
        for (int i = 0; i < numOfCity; i++)
        {
        	for (int j = 0; j < numOfCity; j++)
        	{
        		city[i][j] = inf;
        	}
        }
        // 读入路线
        for (int i = 0; i < (numOfCity - 1); i++)
        {
        	int p = sc.nextInt();
        	int q = sc.nextInt();
        	int distance = sc.nextInt();
        	city[p - 1][q - 1] = distance;
        	city[q - 1][p - 1] = distance;
        }
        optimizeFloyd(numOfCity);
        // floyd(numOfCity);
        
        // 计算并输出
        ans = (int) (0.5 * ans * ans + 10.5 * ans);
        System.out.println(ans);

	}
     
	// floyd暴力搜索
/*	private static void floyd(int n)
	{
		for (int k = 0; k < n; k++)
		{
			for (int i = 0; i < n; i++)
			{
				for (int j = 0; j < n; j++)
				{
					city[i][j] = Math.min(city[i][j], city[i][k] + city[k][j]);
				}
			}
		}
		// 寻找最大值
        for (int i = 0; i < n; i++)
        {
        	for (int j = 0; j < n; j++)
        	{
        		ans = Math.max(city[i][j], ans);
        	}
        }

	}*/
	
	private static void optimizeFloyd(int n)
	{
		// 找到一个最远点
		int k,j;
		int[] longest = {0,0};
		for (k = 0; k < n; k++)
		{
			for (j = 0; j < n; j++)
			{
				city[1][j] = Math.min(city[1][j], city[1][k] + city[k][j]);
				if (longest[0] < city[1][j])
				{
					longest[0] = city[1][j];
					longest[1] = j;
				}
			}
		}
		int nowstart = longest[1];
		for (k = 0; k < n; k++)
		{
			for (j = 0; j < n; j++)
			{
				city[nowstart][j] = Math.min(city[nowstart][j], city[nowstart][k] + city[k][j]);
			}
		}
		for (k = 0; k < n; k++)
		{
			if (k == nowstart) continue;
			ans = Math.max(ans, city[nowstart][k]);
		}
		// System.out.println(ans);
	}
	
	/*private static void output(int n)
	{
        for (int i = 0; i < n; i++)
        {
        	for (int j = 0; j < n; j++)
        	{
        		System.out.print(city[i][j] + " ");;
        	}
        	System.out.println();
        }
	}*/
}
