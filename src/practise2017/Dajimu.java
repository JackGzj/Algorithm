package practise2017;

/**
 * 2016蓝桥省赛第三题
 * 
 * @author Jack
 *
 */
public class Dajimu
{
    public static int total = 0;
    public static int[] nums = {0,1,2,3,4,5,6,7,8,9};
    public static boolean[] visited = new boolean[10];
  
    
    public static void main(String[] args)
    {
        int[] list = new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
        quanPaiLie(list, 0, list.length);
        System.out.println("全排列共有:" + total);
        total = 0;
        dfs(0);
        System.out.println("DFS共有:" + total);
    }
    
    /**
     * 方法一：全排列
     * @param a
     * @param current
     * @param length
     */
    public static void quanPaiLie(int[] a, int current, int length)
    {
        if (current == length - 1)
        {
            if (judge(a))
            {
                total++;
                // print(a);
            }
        } else
        {
            quanPaiLie(a, current + 1, length);
            for (int i = current + 1; i < length; i++)
            {
                int temp = a[current];
                a[current] = a[i];
                a[i] = temp;
                quanPaiLie(a, current + 1, length);
                temp = a[current];
                a[current] = a[i];
                a[i] = temp;
            }
        }
    }

    // 判断已经排好的current位是否符合条件
    public static boolean judge(int[] a)
    {
        if (a[0] < a[1] && a[0] < a[2] && a[1] < a[3] && a[1] < a[4] && a[2] < a[4] && a[2] < a[5])
        {
            if (a[3] < a[6] && a[3] < a[7] && a[4] < a[7] && a[4] < a[8] && a[5] < a[8] && a[5] < a[9])
            {
                return true;
            } else
            {
                return false;
            }
        }
        return false;
    }

    public static void print(int[] a)
    {
        for (int i = 0; i < a.length; i++)
        {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }

    public static boolean test(int n)
    {
        if (n == 3)
        {
            if (nums[0] < nums[1] && nums[0] < nums[2])
            {
                return true;
            }
            return false;
        }
        else if (n == 5)
        {
            if(nums[1] < nums[3] && nums[1] < nums[4] && nums[2] < nums[4] && nums[2] < nums[5])
            {
                return true;
            }
            return false;
        }
        else if (n == 9)
        {
            if(nums[3] < nums[6] && nums[3] < nums[7] && nums[4] < nums[7] && nums[4] < nums[8] && nums[5] < nums[8] && nums[5] < nums[9])
            {
                total ++;
                return true;
            }
            return false;
        }
        return true;
    }
    
    public static void dfs(int n)
    {
        int i;
        for (i = 0; i < 10; i++)
        {
            if (!visited[i])
            {
                visited[i] = true;
                nums[n] = i;
                if (test(n))
                {
                    dfs(n + 1);
                    visited[i] = false;
                }
                else
                {
                    visited[i] = false;
                }
            }
        }
    }
}
