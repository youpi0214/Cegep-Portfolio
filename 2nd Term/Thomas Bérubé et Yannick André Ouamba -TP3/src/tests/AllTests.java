package tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses(
{ CercleTest.class, RectangleTest.class, TriangleTest.class,
		VecteurFormesTest.class, JeuMemoireTest.class })
public class AllTests
{

}
