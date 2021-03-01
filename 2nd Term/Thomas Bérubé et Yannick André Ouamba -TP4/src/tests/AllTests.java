package tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses(
{ CarteTest.class, PaquetDeCartesTest.class, PileTest.class, PiocheTest.class })
public class AllTests
{

}
