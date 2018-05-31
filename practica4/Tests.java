import java.util.*;
import java.util.Random;

class Tests {
	PrimeNumbers primes = new PrimeNumbers();
	PrimeChecker checker = new PrimeChecker();
	Random rand = new Random();

	int start = 2;

	public boolean check(int i) {
		return primes.primes.contains(i) == checker.check(i);
	}
	
	public boolean runAutomatic(int max) {
		int good = 0;
		for (int i = start; i < max; i++) {
			if (!check(i)) {
				System.out.printf("Good: %d/%d\n", i - 1, max);
				System.out.printf("Error: %d\n", i);
				// return false;
			}
			good++;
		}
		System.out.printf("Good: %d/%d\n", good, max - start);
		return true;
	}

	public boolean runRandom(int start, int amount) {
		int lastPrime = PrimeNumbers.lastPrime;
		int testNumber;
		int good = 0;
		for (int i = 0; i < amount; i++) {
			testNumber = rand.nextInt(lastPrime - start - 1) + start;
			if (!check(testNumber)) {
				System.out.printf("Good: %d/%d\n", i - 1, amount);
				System.out.printf("Error: %d\n", i);
			}
			good++;
		}
		System.out.printf("Good: %d/%d\n", good, amount);
		return true;
	}

    public static void main(String[] args) {
		Tests tests = new Tests();
		System.out.println("Automatic tests:");
		tests.runAutomatic(400);
		System.out.println("Random tests:");
		tests.runRandom(400, 400);
	}
}
