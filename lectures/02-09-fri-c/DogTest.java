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

  The template we talked about is:

  public static ___ReturnType___ dogMethod(Dog d, ...) {
    if(d == null) { what happens in the null/empty case? }
    // Most things that need to process all the dogs in the tree will use:
    ... dogMethod(d.mom, ...) ...
    ... dogMethod(d.dad, ...) ...

    // You might also use d.weightInLbs, d.furColor, and combine those
    // with the results above

    // This is a simple recipe for building a recursive method over binary
    // tree-shaped data
  }

  If you want some more practice, try writing the following:

  Write a method totalWeight that consumes a dog and produces the total
  weight in the dog's family

  Write a method countDogs that consumes a dog and produces the total number
  of dogs in the family

  Write a method countBigDogs that consumes a dog and a threshold weight,
  and returns a count of all dogs with weight over that weight in the family

  Write a method furColors that consumes a dog and produces a Set of all the
  furColors in the dog's family (look up the docs for Set)

  NOTE: not _all_ methods work with the scheme we suggested!  Sometimes you
  need to use a helper method or two. The examples below are instances of this:

  Write a method meanWeight that consumes a dog and produces the average weight
  in the family.

  Write a method hasDifferentColoredAncestor that consumes a dog and returns
  true if some _ancestor_ of that dog has the same fur color, and false if not

  */
	
	
	
	// Rex's pic is from http://www.malmstrom.af.mil/News/Features/Display/Article/825572/ruff-job-malmstrom-k-9s-follow-their-nose/
	// His grandfather's picture is from https://commons.wikimedia.org/wiki/File:Frensh_Poodle_Enano.jpg
}

