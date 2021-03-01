package test_prof_conformes;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


@RunWith(Suite.class)
@SuiteClasses({ CarteConformeTest.class, PaquetDeCartesConformeTest.class, PileConformeTest.class,
		PiocheConformeTest.class })
public class AllTests {

}
