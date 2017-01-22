package package2;


public class Paixingzongshu
{
	public static void main(String[] args)
	{
		int ans = 0;
		long start = System.currentTimeMillis();
		for (int a1 = 0; a1 < 5; a1++)
			for (int a2 = 0; a2 < 5; a2++)
				for (int a3 = 0; a3 < 5; a3++)
					for (int a4 = 0; a4 < 5; a4++)
						for (int a5 = 0; a5 < 5; a5++)
							for (int a6 = 0; a6 < 5; a6++)
								for (int a7 = 0; a7 < 5; a7++)
									for (int a8 = 0; a8 < 5; a8++)
										for (int a9 = 0; a9 < 5; a9++)
											for (int a10 = 0; a10 < 5; a10++)
												for (int a11 = 0; a11 < 5; a11++)
													for (int a12 = 0; a12 < 5; a12++)
														for (int a13 = 0; a13 < 5; a13++)
														{
															if (a1 + a2 + a3 + a4 + a5 + a6 + a7 + a8 + a9 + a10 + a11 + a12 + a13 == 13)
															{
																ans ++;
															}
														}
		long end = System.currentTimeMillis();
		System.out.println(ans);
		System.out.println(end - start);
	}
}
