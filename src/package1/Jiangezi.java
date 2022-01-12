package package1;

import java.util.*;

public class Jiangezi
{
	private static int lie = 0, hang = 0, sum = 0;
	private static int[][] gezi = null, visit = null;
	private static final int[][] flag = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		lie = sc.nextInt();
		hang = sc.nextInt();
		gezi = new int[hang][lie];
		visit = new int[hang][lie];
		for (int i = 0; i < hang; i++)
		{
			for (int j = 0; j < lie; j++)
			{
				gezi[i][j] = sc.nextInt();
				sum += gezi[i][j];
			}
		}
		sc.close();
		if (sum % 2 != 0)
		{
			System.out.println(0);
			return;
		}
		else if (gezi[0][0] == sum % 2)
		{
			System.out.println(1);
			return;
		}
		else
		{
			sum /= 2;
			System.out.println(dfs(0, 0, gezi[0][0]));
		}
		
	}
	
	public static int dfs (int x, int y, int num)
	{
		if (num == sum)
		{
		    for (int i = 0; i < hang; i++)
		    {
		        for (int j = 0; j < lie; j++)
		        {
		            System.out.print(visit[i][j] + " ");
		        }
		        System.out.println();
		    }
			return 1;
		}
		int ans = 0;
		for (int i = 0; i < 4; i++)
		{
			
			int tempx = x + flag[i][0], tempy = y + flag[i][1];
			
			// 判断是否越界
			if (tempx >= 0 && tempx < hang && tempy >=0 && tempy < lie)
			{
				// 判断是否已访问和走这一步会不会大于sum
				if (visit[tempx][tempy] == 0 && gezi[tempx][tempy] + num <= sum)
				{
					visit[tempx][tempy] = 1;
					ans = dfs(tempx, tempy, num + gezi[tempx][tempy]);
					if (ans > 0)
					{
						return ans + 1;
					}
					visit[tempx][tempy] = 0;
				}
			 }
		}
		return 0;
	}
	
}