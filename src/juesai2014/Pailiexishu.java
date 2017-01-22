package juesai2014;

import java.util.Scanner;
public class Pailiexishu
{
    static char[] chars, chuan = null;
    static int length = 0, index = -1;
    static String str = null;
    public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		str = sc.next();
		sc.close();
		length = str.length();
		chuan = new char[length];
		chars = new char[length];
		for (int i = 0; i < length; i++)
		{
			chars[i] = (char) (i + 97);
		}
        dfs(0 , 0);

	}
	
	public static void dfs(int x, int num)
	{
		int i;
		if (num == length)
		{
			index ++;
			for (i = 0; i < length; i++)
			{
				if (chuan[i] == str.charAt(i))
				{
					continue;
				}
				else
				{
					break;
				}
			}
			if (i == length)
			{
				System.out.println(index);
				System.exit(0);
			}
			/*for (i = 0; i < length; i++)
			{
				System.out.print(chuan[i] + " ");
			}
			System.out.println(index);*/
			return;
		}
		if (x == length || length == 0)
			return;
		for (i = 0; i < length; i ++)
		{
			if (chars[i] != '0')
			{
				chuan[num] = chars[i];
				chars[i] = '0';
				dfs(i, num + 1);
				chars[i] = chuan[num];
			}
		}
	}

}
