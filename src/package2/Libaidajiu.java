package package2;

public class Libaidajiu
{
	// 遇花为b，遇店为a
    static char[] steprecord = new char[15];
    static int ans = 0;
	public static void main(String[] args)
	{
		walk(0, 0, 0, 2);
		System.out.println(ans);

	}

	
	static void walk (int step, int store, int flower, int wine)
	{
		if (store > 5 || flower > 10)
			return;
		else if (store == 5 && flower == 10 && step == 15)
		{
			if (wine ==0 && steprecord[14] == 'b')
			{
				ans ++;
			}
			return;    
		}
		steprecord[step] = 'b';
		walk(step + 1, store, flower + 1, wine - 1);
		steprecord[step] = 'a';
		walk(step + 1, store + 1, flower, wine * 2);
	}
}
