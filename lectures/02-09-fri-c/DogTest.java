import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class DogTest {

	class Dog {
		String furColor;
		int weightInLbs;
		// If mom or dad is null, that means we don't know anything about them!
		Dog mom, dad;

		public Dog(String furColor, int weightInLbs, Dog mom, Dog dad) {
			this.mom = mom;
			this.dad = dad;
			this.furColor = furColor;
			this.weightInLbs = weightInLbs;
		}
	}
	Dog ada = new Dog("tan", 33, new Dog("brown", 40, null, null), null);
	Dog rex = new Dog("black", 60,
			new Dog("black", 55,
					new Dog("black", 58, null, null),
					new Dog("black", 70, null, null)),
			new Dog("black", 65,
					new Dog("black", 62, null, null),
					new Dog("white", 12, null, null)));
	public static int numberOfKnownGenerations(Dog d) {
		if(d == null) { return 0; }
		int momGens = numberOfKnownGenerations(d.mom);
		int dadGens = numberOfKnownGenerations(d.dad);
		if(dadGens > momGens) { return dadGens + 1; }
		else { return momGens + 1; }		
	}
	@Test
	public void testGenerations() {
		assertEquals(2, numberOfKnownGenerations(ada));
		assertEquals(3, numberOfKnownGenerations(rex));
		assertEquals(0, numberOfKnownGenerations(null));
	}
	
	public static boolean dogWithFurColorInFamily(Dog d, String fur) {
		if(d == null) { return false; }
		return  dogWithFurColorInFamily(d.mom, fur) ||
				dogWithFurColorInFamily(d.dad, fur) ||
				d.furColor.equals(fur);
	}
	@Test
	public void testColor() {
		assertTrue(dogWithFurColorInFamily(rex, "white"));
		assertFalse(dogWithFurColorInFamily(rex, "tan"));
		assertTrue(dogWithFurColorInFamily(ada, "tan"));
		assertFalse(dogWithFurColorInFamily(null, "brown"));
	}
	
	public static int heaviestWeightInFamily(Dog d) {
		if(d == null) { return 0; }
		return Math.max(d.weightInLbs,
				Math.max(heaviestWeightInFamily(d.mom),
						 heaviestWeightInFamily(d.dad)));
	}
	
	public static List<Dog> allDogs(Dog d) {
		if(d == null) { return new ArrayList<>(); }
		List<Dog> momL = allDogs(d.mom);
		List<Dog> dadL = allDogs(d.dad);
		momL.addAll(dadL);
		momL.add(d);
		return momL;
	}
	
	
	
	
	

	
	
	
	
	
	
	
	/*
	 * 	Dog ada = new Dog("tan", 33, new Dog("brown", 40, null, null), null);
	Dog rex = new Dog("black", 60,
			new Dog("black", 55,
					new Dog("black", 58, null, null),
					new Dog("black", 70, null, null)),
			new Dog("black", 65,
					new Dog("black", 62, null, null),
					new Dog("white", 12, null, null)));

	 */
	
	
	
	
	// Rex's pic from http://www.malmstrom.af.mil/News/Features/Display/Article/825572/ruff-job-malmstrom-k-9s-follow-their-nose/
	// His grandfather's picture is from https://commons.wikimedia.org/wiki/File:Frensh_Poodle_Enano.jpg
}

