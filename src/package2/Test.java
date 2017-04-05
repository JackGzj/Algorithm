package package2;

import java.util.Calendar;

public class Test
{   
    public static int getCurrentWeek()
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.SUNDAY);
        calendar.set(2016, 11, 31);
        System.out.println(calendar.getTime());
        return calendar.get(Calendar.WEEK_OF_YEAR);
    }
    
	public static void main(String[] args)
	{
	    System.out.println(getCurrentWeek());
	}

}
