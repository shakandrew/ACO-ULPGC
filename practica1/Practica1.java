import java.util.*;

class Practica1 {
    public static void main(String[] args) {
		Tests tests = new Tests();
		if (tests.runPreparedTests()) {
			System.out.println("Prepared tests done");
		} else {
			System.out.println("Prepared tests failed");
		}

		if (tests.runFuzzTests(300)) {
			System.out.println("Random tests done");
		} else {
			System.out.println("Random tests failed");
		}
    }
}
