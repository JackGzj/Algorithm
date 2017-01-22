package juesai2014;

public class Haidaofenjinbin
{

	public static void main(String[] args)
	{
		for (int i = 1000; i < 5000; i++)
		{
			int gold = i;
			for (int j =0; j < 5; j++)
			{
				if (gold % 5 == 4)
				{
					gold++;
					gold /= 5;
					gold *= 4;
				}
				else break;
				if (j == 4)
				{
					System.out.println("re:" + i + "  gold:" +gold);
				}
			}
		}

	}

}
