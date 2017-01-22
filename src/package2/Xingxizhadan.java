package package2;

import java.util.Calendar;

public class Xingxizhadan
{

	public static void main(String[] args)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.set(2014, 10, 9);
		calendar.add(Calendar.DATE, 1000);
        System.out.println(calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-" + calendar.get(Calendar.DATE));
	}

}
