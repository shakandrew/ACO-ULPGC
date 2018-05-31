import java.util.*;

class Practica4 {
    public static void main(String[] args) {
		PrimeChecker pc = new PrimeChecker();
		System.out.println("Type number to check if it's prime or not");
		int n = Integer.parseInt(System.console().readLine());
		System.out.println(pc.check(n) ? "Prime" : "Not prime");
		System.out.println("Number of executed tests: ");
		System.out.println(pc.getLastResult());
		System.out.println("Number of tests: ");
		System.out.println(pc.getLastListSize());
	}
}
