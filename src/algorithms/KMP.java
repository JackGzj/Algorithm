package algorithms;

import java.util.Scanner;

public class KMP
{
	public static void main(String[] args)
	{
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		String source, pattern = null;
		source = sc.next();
        pattern = sc.next();
        System.out.println(KMP(source, pattern));
	}

	public static int KMP(String source, String pattern)
	{
		int sLen = source.length(), pLen = pattern.length();
		int[] overlay = new int[sLen];
		overlay[0] = -1;
		int index = 0;
		// 字符串覆盖
		for (int i = 1; i < pLen; i++)
		{
			index = overlay[i - 1];
			while(index >= 0 && (pattern.charAt(index + 1) != pattern.charAt(i)))
			{
				index = overlay[index];
			}
			if (pattern.charAt(i) == pattern.charAt(index + 1))
			{
				overlay[i] = index + 1;
			}
			else
			{
				overlay[i] = -1;
			}
		}
		// KMP
		int sindex = 0, pindex = 0;
		while(sindex < sLen && pindex < pLen)
		{
			if (pattern.charAt(pindex) == source.charAt(sindex))
			{
				++pindex;
				++sindex;
			}
			else if (pindex == 0)
			{
				sindex++;
			}
			else
			{
				pindex = overlay[pindex - 1] + 1;
			}
		}
		if (pindex == pLen)
		{
			return sindex - pindex;
		}
		else
		{
			return -1;
		}
	}
}
