package practise2017;

public class Jianyoupiao
{
    public static boolean[] stamps = new boolean[13];
    public static int total = 0;
    public static int connect = 0;
    
    public static void main(String[] args)
    {
        dfs(0, 0);
        System.out.println(total);
    }

    public static void dfs(int num, int beg)
    {
        if (num == 5)
        {
            check();
            for (int i = 0; i < 13; i++)
                System.out.print(stamps[i] + " ");
            System.out.println();
            return;
        }
        if (beg == 12)
            return;
        for (int i = beg + 1; i < 13; i++)
        {
            stamps[i] = true;
            dfs(num + 1, i);
            stamps[i] = false;
        }
    }
    
    public static void check()
    {
        int i;
        for (i = 1; i < 13; i++)
            if (stamps[i])
                break;
        boolean[] temp = new boolean[13];
        for (int j = 1; j < 13; j++)
            temp[j] = stamps[j];
        connect = 0;
        floodfill(temp, i);
        if (connect == 5)
            total++;
    }
    
    public static void floodfill(boolean[] temp, int begin)
    {
        temp[begin] = false;
        connect ++;
        // 上
        if (begin > 4)
        {
            if (temp[begin - 4])
                floodfill(temp, begin - 4);
        }
        // 左
        if (begin % 4 != 1)
        {
            if (temp[begin - 1])
                floodfill(temp, begin - 1);
        }
        // 右
        if (begin % 4 != 0)
        {
            if (temp[begin + 1])
                floodfill(temp, begin + 1);
        }
        // 下
        if (begin < 9)
        {
            if (temp[begin + 4])
                floodfill(temp, begin + 4);
        }
    }
}
