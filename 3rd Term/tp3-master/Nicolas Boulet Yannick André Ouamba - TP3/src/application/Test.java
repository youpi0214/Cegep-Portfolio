package application;

import javafx.geometry.Point2D;

public class Test
{

	public static void main(String[] args)
	{
		Point2D p1 = new Point2D(10, -50);
		Point2D p2 = new Point2D(20, 10);

		System.out.println(p1.distance(p2));

	}

}
