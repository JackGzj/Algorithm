package package2;

public class CutStamp
{
	static boolean[] bo = new boolean[13];
	static int ans = 0;
	static int tmp = 0;

	public static void floodfill(boolean[] bo, int x)
	{
		bo[x] = false;
		tmp++;
		// 上
		if (x > 4)
		{
			if (bo[x - 4])
				floodfill(bo, x - 4);
		}
		// 左
		if (x % 4 != 1)
		{
			if (bo[x - 1])
				floodfill(bo, x - 1);
		}
		// 
		if (x % 4 != 0)
		{
			if (bo[x + 1])
				floodfill(bo, x + 1);
		}
		if (x < 9)
		{
			if (bo[x + 4])
				floodfill(bo, x + 4);
		}
	}

	public static void check()
	{
		int i;
		for (i = 1; i < 13; i++)
			if (bo[i])
				break;
		boolean[] temp = new boolean[13];
		for (int j = 1; j < 13; j++)
			temp[j] = bo[j];
		tmp = 0;
		floodfill(temp, i);
		if (tmp == 5)
			ans++;
	}

	public static void dfs(int num, int beg)
	{
		if (num == 5)
		{
			check();
			/*for (int i = 0; i < 13; i++)
				System.out.print(bo[i] + " ");
			System.out.println();*/
			return;
		}
		if (beg == 12)
			return;
		for (int i = beg + 1; i < 13; i++)
		{
			bo[i] = true;
			dfs(num + 1, i);
			bo[i] = false;
		}
	}

	public static void main(String[] args)
	{
		dfs(0, 0);
		System.out.println(ans);
	}

}
