package juesai2014;

public class Liujiaohuanfang
{
	// static int[] hf = new int[19];

	static int[] nums = { 15, 13, 10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
	static int[] num = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 0, 14, 0, 16, 17, 18, 19 };

	public static void main(String[] args)
	{
		/*
		 * 第一行的头两个数字是：15 13 
		 * 把 1 2 3 ... 19 共19个整数排列成六角形状 ,如下：
		 *          * * *
                   * * * *
                  * * * * *
                   * * * * 
                    * * *
   0 1 2
  3 4 5 6
 7 8 9 0 1
  2 3 4 5
   6 7 8
		 */
		int[] num1 = { 19, 9, 17, 11, 16, 12 };
		for (int i = 0; i < 6; i++)
		{
			nums[6] = num1[i];
			nums[11] = 38 - nums[6] - nums[2];
			if (nums[11] > 19)
				continue;
			num[num1[i] - 1] = 0;
			num[nums[11] - 1] = 0;
			for (int j = 0; j < 19; j++)
			{
				if (num[j] == 0)
					continue;
				nums[3] = num[j];
				nums[7] = 38 - nums[3] - nums[0];
				if (nums[7] > 19)
					continue;
				num[j] = 0;
				num[nums[7] - 1] = 0;
				for (int k = 0; k < 19; k++)
				{
					if (num[k] == 0)
						continue;
					nums[12] = num[k];
					nums[16] = 38 - nums[12] - nums[7];
					if (nums[16] > 19)
						continue;
					num[k] = 0;
					num[nums[16] - 1] = 0;
					for (int l = 0; l < 19; l++)
					{
						if (num[l] == 0)
							continue;
						nums[15] = num[l];
						nums[18] = 38 - nums[15] - nums[11];
						nums[17] = 38 - nums[16] - nums[18];
						if (nums[17] > 19 || nums[18] > 19)
							continue;
					}
					num[k] = nums[12];
					num[nums[16] - 1] = nums[16];
				}
				num[j] = nums[3];
				num[nums[7] - 1] = nums[7];
			}
			num[num1[i] - 1] = nums[6];
			num[nums[11] - 1] = nums[11];
		}
		for (int i = 0; i < 19; i++)
		{
			System.out.print(nums[i] + " ");
			if(i == 2 ||i == 6 || i == 11 || i == 15 || i == 18)
				System.out.println();
		}
	}

}
