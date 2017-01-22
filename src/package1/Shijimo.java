package package1;

import java.util.Calendar;

public class Shijimo
{

	public static void main(String[] args)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.set(1999, 11, 31);
		for (;;)
		{
			calendar.add(Calendar.YEAR, 100);
			if (calendar.get(Calendar.DAY_OF_WEEK) == 1)
			{
				System.out.println(calendar.get(Calendar.YEAR));
				return;
			}
		}
		
	}

}
