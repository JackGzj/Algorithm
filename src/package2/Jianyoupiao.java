package package2;

import java.util.ArrayList;

//思路：组合数+BFS检验是否连在一起
public class Jianyoupiao
{
	private static int ans = 0;
	private static int[] stamp = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};

	public static void main(String[] args)
	{
		ArrayList<Integer> num = null;
		// 全组合
		for (int i = 0; i < 12; i++)
		{
			for (int j = i + 1; j < 12; j++)
			{
				for (int k = j + 1; k < 12; k++)
				{
					for (int l = k + 1; l < 12; l++)
					{
						for (int m = l + 1; m < 12; m++)
						{
							num = new ArrayList<Integer>();
							num.add(stamp[i]);
							num.add(stamp[j]);
							num.add(stamp[k]);
							num.add(stamp[l]);
							num.add(stamp[m]);
							// System.out.println("sourse:" + num);
							if (check(num))
							{
								ans++;
							}
						}
					}
				}
			}
		}
		System.out.println(ans);
	}

	public static boolean check(ArrayList<Integer> num)
	{
		int i;
		int up, down, left, right;
		// list做BFS
		ArrayList<Integer> list = new ArrayList<Integer>();
		// 添加第一个数
		list.add(num.get(0));
		for (i = 0; i < list.size(); i++)
		{
			if (list.size() == 5)
			{
				System.out.println(list);
				return true;
			}
			// 上
			up = list.get(i) - 4;
			if (num.contains(up))
			{  
				if (!list.contains(up))
					list.add(up);
				num.remove(list.get(i));
				// System.out.println(list.get(i) + " up " + up);
			}
			// 下
			down = list.get(i) + 4;
			if (num.contains(down))
			{  
				if (!list.contains(down))
					list.add(down);
				num.remove(list.get(i));
			    // System.out.println(list.get(i) + " down " + down);
			}
			// 左
			left = list.get(i) - 1;
			if (num.contains(left) && (list.get(i) % 4 != 0))
			{  
				if (!list.contains(left))
					list.add(left);
				num.remove(list.get(i));
				// System.out.println(list.get(i) + " left " + (list.get(i) - 1));
			}
			// 右
			right = list.get(i) + 1;
			if (num.contains(right) && (list.get(i) % 4 != 3))
			{  
				if (!list.contains(right))
					list.add(right);
				num.remove(list.get(i));
				// System.out.println(list.get(i) + " right " + (list.get(i) + 1));
			}
		}
		if (list.size() < 5)
		{
			// System.out.println(list);
			return false;
		}
		System.out.println(list);
		return true;

	}
}
