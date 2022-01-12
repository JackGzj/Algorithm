package practise2017;

/**
 * 2016蓝桥省赛第一第二题
 * @author Jack
 *
 */
public class MeiQiuShuMu
{
    public static void main(String[] args)
    {
        dierti();
    }
    
    /**
     * 第一题 煤球数量
     */
    public static void diyiti()
    {
        int total = 0;
        for (int i = 1; i <= 100; i++)
        {
            int ceng = 0;
            for (int j = 1; j <= i; j++)
            {
                ceng += j;
            }
            total += ceng;
            System.out.println("第" + i + "层有" + ceng + "个煤球");
            // System.out.println("第" + i + "层有" + total + "个煤球");
        }
        System.out.println("共有" + total + "个煤球");
    }
    
    /**
     * 第二题 生日蜡烛
     */
    public static void dierti()
    {
        for (int i = 1; i < 100; i++)
        {
            for (int j = i + 1; j < 100; j++)
            {
                int lazhu = 0;
                boolean flag = false;
                for (int k = i; k <= j; k++)
                {
                    lazhu += k;
                    if (lazhu == 236)
                    {
                        flag = true;
                        System.out.println(i + "," + j + "," + lazhu);
                        break;
                    }
                }
                if (flag)
                {
                    break;
                }
            }
        }
        
    }

}
