package tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses(
{ FichierUtilitairesTest.class, ListeCombinatoireTest.class,
		MathUtilitairesTest.class, MatriceUtilitairesTest.class,
		VecteurDeCaracteresTest.class, ListeMatricesChiffrementTest.class,
		MessageChiffrerDechiffrerTest.class })
public class AllTests
{

}
