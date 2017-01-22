package algorith_course;

import java.util.Scanner;

public class ChessBoard
{
	public static int[][] chessBoard = null;
	public static int count = 1;

	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("请依次输入棋盘大小（二的n次幂），指定方格的行、列坐标（从0开始算）");
		int size = sc.nextInt(), row = sc.nextInt(), col = sc.nextInt();
		sc.close();
		chessBoard = new int[size][size];
		chessBoard(0, 0, row, col, size);
		for (int i = 0; i < size; i++)
		{
			for (int j = 0; j < size; j++)
			{
				System.out.print(chessBoard[i][j] + " ");
			}
			System.out.println();
		}
	}

	/**
	 * 
	 * @param tr
	 *            棋盘左上角行坐标
	 * @param tc
	 *            棋盘左上角列坐标
	 * @param dr
	 *            指定格子行坐标
	 * @param dc
	 *            指定格子列坐标
	 * @param size
	 *            棋盘大小
	 */
	public static void chessBoard(int tr, int tc, int dr, int dc, int size)
	{
		// System.out.println(tr + " " + tc + " " + dr + " " + dc + " " + size);
		if (size == 1)
			return;
		int t = count++;
		int newSize = size / 2;
		// 指定块在左上棋盘
		if (dr < tr + newSize && dc < tc + newSize)
			chessBoard(tr, tc, dr, dc, newSize);
		else
		{
			chessBoard[tr + newSize - 1][tc + newSize - 1] = t;
			chessBoard(tr, tc, tr + newSize - 1, tc + newSize - 1, newSize);
		}

		// 指定块在右上棋盘
		if (dr < tr + newSize && dc >= tc + newSize)
		{
			chessBoard(tr, tc + newSize, dr, dc, newSize);
		}
		else
		{
			chessBoard[tr + newSize - 1][tc + newSize] = t;
			chessBoard(tr, tc + newSize, tr + newSize - 1, tc + newSize, newSize);
		}

		// 指定块在左下角
		if (dr >= tr + newSize && dc < tc + newSize)
		{
			chessBoard(tr + newSize, tc, dr, dc, newSize);
		}
		else
		{
			chessBoard[tr + newSize][tc + newSize - 1] = t;
			chessBoard(tr + newSize, tc, tr + newSize, tc + newSize - 1, newSize);
		}

		// 指定块在右下角
		if (dr >= tr + newSize && dc >= tc + newSize)
		{
			chessBoard(tr + newSize, tc + newSize, dr, dc, newSize);// 处理有特殊棋子的右下角子棋盘
		}
		else
		{
			chessBoard[tr + newSize][tc + newSize] = t;// 设子棋盘右下角的左上角为特殊棋子，用t型的骨牌覆盖。由于骨牌有三种，当处理过程中同一级设置的特殊棋子用相同的骨牌覆盖
			chessBoard(tr + newSize, tc + newSize, tr + newSize, tc + newSize, newSize);// 处理有用
																						// 骨牌覆盖的格子作为特殊棋子的右下角子棋盘
		}
	}

}
