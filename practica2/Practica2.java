import java.util.*;

class Practica2 {
    public static void main(String[] args) {
		Tests tests = new Tests();
		if (tests.runPreparedTests()) {
			System.out.println("Prepared tests done");
		} else {
			System.out.println("Prepared tests failed");
		}
    }
}
