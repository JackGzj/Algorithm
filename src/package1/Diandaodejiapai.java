package package1;

public class Diandaodejiapai
{

	public static void main(String[] args)
	{
		int[] num = { 0, 1, 2, 5, 6, 8, 9 };
		int[] jiage = new int[4];
		int[] earnSum = new int[30], earnDif = new int[30], loseSum = new int[30], loseDif = new int[30];
		int i, j, sum, rsum, dif = 0;
		int nume = 0, numl = 0;
		for (jiage[0] = 0; jiage[0] < 7; jiage[0]++)
		{
			for (jiage[1] = 0; jiage[1] < 7; jiage[1]++)
			{
				for (jiage[2] = 0; jiage[2] < 7; jiage[2]++)
				{
					for (jiage[3] = 0; jiage[3] < 7; jiage[3]++)
					{
						sum = num[jiage[0]] * 1000 + num[jiage[1]] * 100 + num[jiage[2]] * 10 + num[jiage[3]];
						rsum = 0;
						for (i = 0; i < 4; i++)
						{
                             if (num[jiage[i]] == 6)
                             {
                            	 rsum += 9 * shiCiFang(i);
                             }
                             else if (num[jiage[i]] == 9)
                             {
                            	 rsum += 6 * shiCiFang(i);
                             }
                             else
                             {
                            	 rsum += num[jiage[i]] * shiCiFang(i);
                             }
						}
						if (sum > 1000)
						{
							dif = sum - rsum;
						}
						if (dif > 800 && dif < 900)
						{
							earnSum[nume] = sum;
							earnDif[nume] = dif;
							nume ++;
						}
						if (dif > -300 && dif < -200)
						{
							loseSum[numl] = sum;
							loseDif[numl] = dif;
							numl ++;
						}
					}
				}
			}
		}
		for (i = 0; i < nume; i++)
		{
			for (j = 0; j < numl; j++)
			{
				if (earnDif[i] + loseDif[j] == 558)
				{
					System.out.println(loseSum[j]);
					break;
				}
			}
		}
	}

	static int shiCiFang(int n)
	{
		switch(n)
		{
			case 0: return 1;
			case 1: return 10;
			case 2: return 100;
			case 3: return 1000;
		    default: return 0;
	    }
	}
}
