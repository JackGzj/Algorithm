package juesai2013;

public class Touzimiti
{
	static int[] bot1 = {0, 0, 0, 8, 8, 8};
    static int[] bot2 = {1, 1, 4 ,5 ,6, 7};
    static int[] xiaoming= new int[6];
    static int[] ans= new int[6];
    static int max = 0;
    
    public static void count()
    {
    	int m = 0, x1 = 0, x2 = 0, i;
    	for (i = 0; i < 6; i++)
    	{
    		x1 = 0;
    		x2 = 0;
    		for (int j = 0; j < 6; j++)
    		{
    			if (bot1[j] < xiaoming[i])
        			x1 ++;
        		else
        			break;
    		}
    		for (int j = 0; j < 6; j++)
        	{
        		if (bot2[j] < xiaoming[i])
        			x2 ++;
        		else
        			break;
        	}
    		m += x1 * x2;
    	}
    	if (m > max)
    	{
    		for (i = 0; i < 6; i++)
    			ans[i] = xiaoming[i];
    		max = m;
    	}
    }
    
	public static void dfs(int n, int begin, int sum)
	{
		if (sum > 24)
			return;
		if (n == 6)
		{
			if (sum == 24)
			    count();
			return;
		}
		for (int i = begin; i < 9; i++)
		{
			xiaoming[n] = i;
			dfs(n + 1, i, sum + i);
			xiaoming[n] = 0;
		}
	}
	
	public static void main(String[] args)
	{
		dfs(0, 0, 0);
		for (int i = 0; i < 6; i++)
		    System.out.print(ans[i] + " ");
		System.out.println(max);
	}

}
