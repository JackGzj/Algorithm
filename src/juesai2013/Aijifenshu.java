package juesai2013;

public class Aijifenshu
{

	public static void main(String[] args)
	{
		int ans = 0;
		float a = (float)2/45;
		for (int i = 2; i < 9999; i++)
		{
			for (int j = i + 1; j < 9999; j++)
			{
				if ((float)1 / i + (float)1 / j == a)
				{
					ans ++;
					System.out.println("1/" + i + "+ 1/" + j);
				}
			}
		}
		System.out.println(ans);
	}

}
