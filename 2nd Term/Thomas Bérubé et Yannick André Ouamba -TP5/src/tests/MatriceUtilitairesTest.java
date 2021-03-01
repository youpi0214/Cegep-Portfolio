package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import utilitaires.MatriceUtilitaires;

public class MatriceUtilitairesTest
{
	private int[][] m1 =
	{
			{ 1, 2, 1, 0 },
			{ 0, 3, 1, 1 },
			{ -1, 0, 3, 1 },
			{ 3, 1, 2, 0 } }, m2 =  
	{
			{ 5, 1 },
			{ 6, 2 },
			{ 7, 3 } }, m3 =
	{
			{ 2, 3 },
			{ -5, 7 } };

	@Test
	public void testToStringMat()
	{
		assertEquals(MatriceUtilitaires.toStringMat(m1), "[1, 2, 1, 0]\n"
				+ "[0, 3, 1, 1]\n" + "[-1, 0, 3, 1]\n" + "[3, 1, 2, 0]\n");

		assertEquals(MatriceUtilitaires.toStringMat(m2),
				"[5, 1]\n" + "[6, 2]\n" + "[7, 3]\n");

		assertEquals(MatriceUtilitaires.toStringMat(m3),
				"[2, 3]\n" + "[-5, 7]\n");
	}

	@Test
	public void testGetMatTranspose()
	{
		assertEquals(
				MatriceUtilitaires
						.toStringMat(MatriceUtilitaires.getMatTranspose(m1)),
				"[1, 0, -1, 3]\n" + "[2, 3, 0, 1]\n" + "[1, 1, 3, 2]\n"
						+ "[0, 1, 1, 0]\n");

		assertEquals(
				MatriceUtilitaires
						.toStringMat(MatriceUtilitaires.getMatTranspose(m2)),
				"[5, 6, 7]\n" + "[1, 2, 3]\n");

		assertEquals(
				MatriceUtilitaires
						.toStringMat(MatriceUtilitaires.getMatTranspose(m3)),
				"[2, -5]\n" + "[3, 7]\n");
	}

	@Test
	public void testGetMatMultScalaire()
	{

		assertEquals(
				MatriceUtilitaires.toStringMat(
						MatriceUtilitaires.getMatMultScalaire(m1, 5)),
				"[5, 10, 5, 0]\n" + "[0, 15, 5, 5]\n" + "[-5, 0, 15, 5]\n"
						+ "[15, 5, 10, 0]\n");

		assertEquals(
				MatriceUtilitaires.toStringMat(
						MatriceUtilitaires.getMatMultScalaire(m1, -5)),
				"[-5, -10, -5, 0]\n" + "[0, -15, -5, -5]\n"
						+ "[5, 0, -15, -5]\n" + "[-15, -5, -10, 0]\n");

		assertEquals(
				MatriceUtilitaires.toStringMat(
						MatriceUtilitaires.getMatMultScalaire(m1, 0)),
				"[0, 0, 0, 0]\n" + "[0, 0, 0, 0]\n" + "[0, 0, 0, 0]\n"
						+ "[0, 0, 0, 0]\n");

		assertEquals(
				MatriceUtilitaires.toStringMat(
						MatriceUtilitaires.getMatMultScalaire(m2, 1)),
				"[5, 1]\n" + "[6, 2]\n" + "[7, 3]\n");

		assertEquals(
				MatriceUtilitaires.toStringMat(
						MatriceUtilitaires.getMatMultScalaire(m3, -1)),
				"[-2, -3]\n" + "[5, -7]\n");
	}

	@Test
	public void testGetMatModuloX()
	{
		assertEquals(
				MatriceUtilitaires
						.toStringMat(MatriceUtilitaires.getMatModuloX(m1, 4)),
				"[1, 2, 1, 0]\n" + "[0, 3, 1, 1]\n" + "[3, 0, 3, 1]\n"
						+ "[3, 1, 2, 0]\n");

		assertEquals(
				MatriceUtilitaires
						.toStringMat(MatriceUtilitaires.getMatModuloX(m1, 2)),
				"[1, 0, 1, 0]\n" + "[0, 1, 1, 1]\n" + "[1, 0, 1, 1]\n"
						+ "[1, 1, 0, 0]\n");

		assertEquals(
				MatriceUtilitaires.toStringMat(MatriceUtilitaires.getMatModuloX(
						MatriceUtilitaires.getMatMultScalaire(m2, 5), 6)),
				"[1, 5]\n" + "[0, 4]\n" + "[5, 3]\n");

		assertEquals(
				MatriceUtilitaires.toStringMat(MatriceUtilitaires.getMatModuloX(
						MatriceUtilitaires.getMatMultScalaire(m3, 9), 7)),
				"[4, 6]\n" + "[4, 0]\n");
	}

	@Test
	public void testGetDeterminant()
	{
		int[][] mat5 =
		{
				{ 1, -2, 8, 0, 9 },
				{ 2, -7, -9, 4, 7 },
				{ 2, 3, 0, 4, 7 },
				{ 2, -7, 1, 1, 7 },
				{ 3, 5, 1, 4, 2 } };
		assertEquals(MatriceUtilitaires.getDeterminant(m1), 16);
		assertEquals(MatriceUtilitaires.getDeterminant(m3), 29);
		assertEquals(MatriceUtilitaires.getDeterminant(mat5), 268);

		assertEquals(MatriceUtilitaires.getDeterminant(
				MatriceUtilitaires.getMatMultScalaire(m3, 6)), 1044);

		assertEquals(MatriceUtilitaires.getDeterminant(
				MatriceUtilitaires.getMatMultScalaire(m3, 0)), 0);

		// le vrai déterminant donne 29/4 mais le type int donne 5
		assertEquals(MatriceUtilitaires.getDeterminant(
				MatriceUtilitaires.getMatMultScalaire(m3, 0.5f)), 5);
	}

	@Test
	public void testGetMatCofacteurs()
	{
		assertEquals(
				MatriceUtilitaires
						.toStringMat(MatriceUtilitaires.getMatCofacteurs(m1)),
				"[-8, 8, 8, -32]\n" + "[3, 1, -5, 18]\n" + "[-3, -1, 5, -2]\n"
						+ "[7, -3, -1, 10]\n");

		assertEquals(
				MatriceUtilitaires
						.toStringMat(MatriceUtilitaires.getMatCofacteurs(m3)),
				"[7, 5]\n" + "[-3, 2]\n");
	}

	@Test
	public void testGetMatAdjointe()
	{
		System.out.println(MatriceUtilitaires
				.toStringMat(MatriceUtilitaires.getMatAdjointe(m3)));

		assertEquals(
				MatriceUtilitaires
						.toStringMat(MatriceUtilitaires.getMatAdjointe(m1)),
				"[-8, 3, -3, 7]\n" + "[8, 1, -1, -3]\n" + "[8, -5, 5, -1]\n"
						+ "[-32, 18, -2, 10]\n");

		assertEquals(
				MatriceUtilitaires
						.toStringMat(MatriceUtilitaires.getMatAdjointe(m3)),
				"[7, -3]\n" + "[5, 2]\n");
	}
}
