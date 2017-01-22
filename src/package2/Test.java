package package2;

import java.util.Date;

public class Test
{   
	private static final long key =  74494944248264l;
	private static final int mod1 = 550351;
	private static final int mod2 = 131234569;
	private static final int multiply = 12347;
	
    public static long DJBHash(String str)  
    {  
       long hash = 5381;  
       for(int i = 0; i < str.length(); i++)  
       {  
          hash = ((hash << 5) + hash) + str.charAt(i);  
       }  
       return hash;  
    } 
    
    public static void find()
    {
    	long i;
    	for (i = 130000000l; i < 230000000l; i++)
    	{
    		boolean flag = false;
    		for(long j = 2l; j < Math.sqrt(i); j++)
    		{
    			if (i % j == 0)
    			{
    				flag = true;
    				break;
    			}	
    		}
    		if (flag)
    		{
    			continue;
    		}
    		else
    		{
    			System.out.println(i);
    			// break;
    		}
    	}
    }
    
	public static void main(String[] args)
	{
		// find();
		Date date = new Date();
		long timestamp = (date.getTime() / 1000);
		long hash = (((DJBHash(Long.toString(timestamp ^ key))) % mod1) * multiply) ^ key;// (date.getTime() / 1000) ^ key;
		System.out.println(timestamp);
		System.out.println(hash);
        System.out.println(DJBHash(Long.toString(hash)) % mod2);
	}

}
