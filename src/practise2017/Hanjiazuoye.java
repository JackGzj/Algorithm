package practise2017;

public class Hanjiazuoye
{
    public static int total = 0;
    public static int[] nums = new int[13];
    public static boolean[] visited = new boolean[13];
    
    public static void main(String[] args)
    {
         dfs(0);
         System.out.println(total);
    }
    
    public static boolean test(int n)
    {
        if (n == 2 && nums[0] + nums[1] != nums[2])
        {
           return false;
        }
        else if (n == 5 && nums[3] - nums[4] != nums[5])
        {
            return false;
        }
        else if (n == 8 && nums[6] * nums[7] != nums[8])
        {
            return false;
        }
        else if (n == 11 && nums[10] * nums[11] != nums[9])
        {
            return false;
        }
        return true;
    }
    
    public static void dfs(int n)
    {
        if (n >= 13)
        {
            total ++;
            System.out.print(nums[0] + " + " + nums[1] + " = " + nums[2] + "       ");
            System.out.print(nums[3] + " - " + nums[4] + " = " + nums[5] + "       ");
            System.out.print(nums[6] + " * " + nums[7] + " = " + nums[8] + "       ");
            System.out.print(nums[9] + " / " + nums[10] + " = " + nums[11] + "       ");
            System.out.println();
            return;
        }
        else
        {
            for (int i = 0; i < 13; i++)
            {
                if (!visited[i])
                {
                    visited[i] = true;
                    nums[n] = i + 1;
                    if (test(n))
                    {
                        dfs(n + 1);
                    }
                    visited[i] = false;
                }
            }
        }
    }
}
