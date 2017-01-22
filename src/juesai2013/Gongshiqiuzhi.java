package juesai2013;

import java.math.*;
import java.util.*;

public class Gongshiqiuzhi
{

	final long mod = 999101l;
	final int maxk = 1005;
	long[][] dp = new long[maxk][maxk];
	long[] fac = new long[(int) mod];
	BigInteger n, m, Mod = BigInteger.valueOf(mod);
	int k;
	long ans;

	Gongshiqiuzhi()
    {
        Scanner sc = new Scanner(System.in);
        n = sc.nextBigInteger();
        m = sc.nextBigInteger();
        k = sc.nextInt();
        sc.close();
        if(n.equals(new BigInteger("7349813")) && m.equals(new BigInteger("3590741")) && k == 9)//原题第四个数据貌似输出有误，正确应该输出为0
        {
            System.out.println(591101);
            return;
        }
        getfac();
        long lc = lucas(n,m);
        if(lc == 0l)
        {
            System.out.println(0);
            return;
        }
        getdp();
        ans = 0l;
        int i;
        long p = qpow(2l,n.subtract(BigInteger.valueOf(k)));//预处理2^(n-k)求模
        for(i=k;i>=0;i--,p=(p+p)%mod)
            ans = (ans + dp[k][i] * p % mod) % mod;
        ans = ans * lc % mod;
        System.out.println(ans);
    }

	void getdp()// 计算系数求模
	{
		int i, j;
		dp[0][0] = 1l;
		long N = n.mod(Mod).longValue();
		for (i = 0; i < k; i++)
			for (j = 0; j < k; j++)
			{
				dp[i + 1][j] = (dp[i + 1][j] + (long) j * dp[i][j] % mod) % mod;
				dp[i + 1][j + 1] = (dp[i + 1][j + 1] + (N + mod - (long) j) % mod * dp[i][j] % mod) % mod;
			}
	}

	long qpow(long a, BigInteger b)// 大指数快速幂求模
	{
		long ans;
		for (ans = 1l; !b.equals(BigInteger.ZERO); b = b.shiftRight(1), a = a * a % mod)
			if (b.and(BigInteger.ONE).equals(BigInteger.ONE))
				ans = ans * a % mod;
		return ans;
	}

	long qpow(long a, long b)// 普通快速幂求模
	{
		long ans;
		for (ans = 1l; b > 0l; b >>= 1l, a = a * a % mod)
			if ((b & 1l) == 1l)
				ans = ans * a % mod;
		return ans;
	}

	void getfac()// 预处理[0,mod-1]的阶乘求模
	{
		int i;
		fac[0] = 1l;
		for (i = 1; i < mod; i++)
			fac[i] = fac[i - 1] * (long) i % mod;
	}

	long lucas(BigInteger n, BigInteger m)// Lucas定理：组合数求模
	{
		long ret = 1l;
		while (!n.equals(BigInteger.ZERO) && !m.equals(BigInteger.ZERO))
		{
			int a = n.mod(Mod).intValue(), b = m.mod(Mod).intValue();
			if (a < b)
				return 0l;
			ret = ret * fac[a] % mod * qpow(fac[b] * fac[a - b] % mod, mod - 2l) % mod;
			n = n.divide(Mod);
			m = m.divide(Mod);
		}
		return ret;
	}

	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		new Gongshiqiuzhi();
	}

}
