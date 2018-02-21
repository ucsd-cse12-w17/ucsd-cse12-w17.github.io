package ancestors;

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
		@Override
		public String toString() {
			return "Dog [furColor=" + furColor + ", weightInLbs=" + weightInLbs + ", mom=" + mom + ", dad=" + dad + "]";
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
	
	// Return a list of all the weights of the dogs
	// in the family, including all ancestors
	public static List<Integer> allDogWeights(Dog d) {
		if(d == null) { return new ArrayList<>(); }
		List<Integer> momWeights = allDogWeights(d.mom);
		List<Integer> dadWeights = allDogWeights(d.dad);
		momWeights.add(0, d.weightInLbs);
		momWeights.addAll(dadWeights);
		return momWeights;
	}
	
	public static List<Integer> allDogWeights(Dog d, List<Integer> weightsSoFar) {
		if(d == null) { return weightsSoFar; }
		allDogWeights(d.mom, weightsSoFar);
		allDogWeights(d.dad, weightsSoFar);
		weightsSoFar.add(d.weightInLbs);
		return weightsSoFar;
	}
	
	@Test
	public void allDogsTest() {
		System.out.println(allDogWeights(rex));
		System.out.println(allDogWeights(rex, new ArrayList<>()));
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
