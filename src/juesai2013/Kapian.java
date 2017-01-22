package juesai2013;

import java.util.Scanner;
import java.util.TreeMap;
import java.util.ArrayList;

public class Kapian
{
    static int[] available, num = null;
    static int[] remain = new int[101];
    static TreeMap<Integer, ArrayList<Integer>> table = new TreeMap<Integer, ArrayList<Integer>>();
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int i, maxnum = 0, newChoose, numLen, availableLen, flag;
		// 读入第一行
		String line = sc.nextLine();
		String[] lineint = line.split(" ");
		numLen = lineint.length;
		num = new int[numLen];
		for (i = 0; i < numLen; i++)
		{
			int tempnum = Integer.parseInt(lineint[i]);
			num[i] = tempnum;
			remain[tempnum] ++;
			if (tempnum > maxnum)
				maxnum = tempnum;
		}
		// 读入第二行
		line = sc.nextLine();
		lineint = line.split(" "); 
		availableLen = lineint.length;
		available = new int[availableLen];
		for (i = 0; i < availableLen; i++)
		{
			available[i] = Integer.parseInt(lineint[i]);
		}
        sc.close();
        // 将第一行所有数字在1~第一行最大的数范围内的约数和倍数放入一个TreeMap中记录
        for (i = 0; i < numLen; i++)
        {
        	if (table.containsKey(num[i]))
        		continue;
        	else
        	{
        		ArrayList<Integer> ysbs = new ArrayList<Integer>();
        		for (int j = 1; j <= maxnum; j++)
        		{
        			if (num[i] % j == 0 || j % num[i] == 0)
        			{
        				ysbs.add(j);
        			}
        		}
        		table.put(num[i], ysbs);
        	}
        }
        for (i = 0; i < availableLen; i++)
        {
        	newChoose = available[i];
        	remain[newChoose] --;
        	flag = dfs(newChoose);
        	// System.out.println("newChoose in main:" + newChoose + " " + flag);
        	remain[newChoose] ++;
        	if (flag == 1)
        	{
        		System.out.println(newChoose);
        		return;
        	}
        }
        System.out.println("-1");
        return;
	}
	
	public static int dfs(int tempChoose)
	{
		// System.out.println("tempChoose:" + tempChoose);
		int i, newChoose, flag;
		ArrayList<Integer> ysbs = table.get(tempChoose);
		// 遍历约数倍数数组
		for (i = 0; i < ysbs.size() ; i++)
		{
			newChoose = ysbs.get(i);
			// 可取
			if (remain[newChoose] > 0)
			{
				remain[newChoose] --;
	        	flag = dfs(newChoose);
	        	// System.out.println("newChoose:" + newChoose + " " + flag);
	        	remain[newChoose] ++;
	        	// 对方必输则我必胜（对手有必胜的可能，则我必败）
	        	if (flag == 1)
	        	{
	        		return -1;
	        	}
			}
		}
		return 1;
	}

}
