package algorith_course;

import java.math.BigInteger;
import java.util.Scanner;

public class Ackermann
{

	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		while (sc.hasNext())
		{
			BigInteger m = sc.nextBigInteger(), n = sc.nextBigInteger();
			System.out.println(akm(m, n));
		}
		sc.close();
	}

	// 数字巨大，用BigInteger存
	public static BigInteger akm(BigInteger m, BigInteger n)
	{
		if (m.equals(BigInteger.ZERO))
		{
			return n.add(BigInteger.ONE);
		}
		else if (m.compareTo(BigInteger.ZERO) == 1 && n.equals(BigInteger.ZERO))
		{
			return akm(m.subtract(BigInteger.ONE), BigInteger.ONE);
		}
		else if (m.compareTo(BigInteger.ZERO) == 1 && n.compareTo(BigInteger.ZERO) == 1)
		{
			return akm(m.subtract(BigInteger.ONE), akm(m, n.subtract(BigInteger.ONE)));
		}
		return BigInteger.ZERO;
	}
}
