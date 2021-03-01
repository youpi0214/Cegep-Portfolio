package tests_conformites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses(
{ CercleConformeTest.class, CouleurConformeTest.class, FormeConformeTest.class,
		JeuMemoireConformeTest.class, RectangleConformeTest.class,
		TriangleConformeTest.class, TypeTriangleConformeTest.class,
		VecteurFormesConformeTest.class })
public class AllTests
{

}
