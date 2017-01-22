package algorithms;

import java.math.BigInteger;

public class FastPow
{
	static long qpow(long a, long b, int mod)// 普通快速幂求模
	{
		long ans;
		for (ans = 1l; b > 0l; b >>=  1l, a = a * a % mod)
			if ((b & 1l) == 1l)
				ans = ans * a % mod;
		return ans;
	}
	
	static long qpow(long a, BigInteger b, int mod)// 大指数快速幂求模
	{
		long ans;
		for (ans = 1l; !b.equals(BigInteger.ZERO); b = b.shiftRight(1), a = a * a % mod)
			if (b.and(BigInteger.ONE).equals(BigInteger.ONE))
				ans = ans * a % mod;
		return ans;
	}
	
	public static void main(String[] args)
	{
		System.out.println(qpow(2, 9999, 7));
	}

}
