package practise2017;

import java.util.HashSet;
import java.util.Set;

public class Coupingfangshu
{
    public static void main(String[] args)
    {
        Set<Integer> v = new HashSet<Integer>();
        int count = 0, sq = 0;
        for (int i = 0; i < 100000; i++)
        {
            sq = i * i;
            int m = 0;
            if (sq < 100)
            {
                count++;
            } else if (sq >= 100)
            {
                int n = sq;
                while (n != 0)
                {
                    m++;
                    v.add(n % 10);
                    n = n / 10;

                }
                if (v.size() == m)
                    count++;
                v.clear();
            }
        }
        System.out.println(count);
    }
}
