package practise2017;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class Yasuobianhuan
{

    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];
        int[] ans = new int[n];
        HashMap<Integer, Integer> numsMap = new HashMap<Integer, Integer>();
        HashSet<Integer> different = null;
        for (int i = 0; i < n; i++)
        {
            nums[i] = sc.nextInt();
            // 第一次出现的数字
            if (!numsMap.containsKey(nums[i]))
            {
                numsMap.put(nums[i], i);
                ans[i] = nums[i] * -1;
            }
            else
            {
                // 重复出现的数字
                // 查上次出现的位置和当前位置之间都几个不一样
                different = new HashSet<Integer>();
                int difNums = 0;
                for (int j = numsMap.get(nums[i]); j < i; j++)
                {
                    if (nums[j] != nums[i] && !different.contains(nums[j]))
                    {
                        different.add(nums[j]);
                        difNums++;
                    }
                }
                // System.out.println("index: " + i + ", set: " + different);
                // 更新位置
                numsMap.put(nums[i], i);
                ans[i] = difNums;
            }
        }
        
        for (int i = 0; i < n; i++)
        {
            System.out.print(ans[i] + " ");
        }
        System.out.println();
        sc.close();
    }

}
