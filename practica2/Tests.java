import java.util.*;

class Test {

	List<Edge> edges;
	int n;
	int start;
	int sink;
	int result;

	public Test(List<Edge> edges, int n, int start, int sink, int result){
		this.edges = edges;
		this.n = n;
		this.start = start;
		this.sink = sink;
		this.result = result;
	}

	public List<Edge> getEdges() { return edges; }
	public int getN() { return n; }
	public int getResult() { return result; }
	public int getSink() { return sink; }
	public int getStart() { return start; }
}

class Tests {
	// initializar el grafo
	ArrayList<Test> preparedTests;

	public Tests(){
		preparedTests = new ArrayList<>();
		//example from the lecture
		preparedTests.add(new Test(Arrays.asList(
            new Edge(0, 1, 1), new Edge(2, 0, 3), 
            new Edge(3, 5, 2), new Edge(3, 6, 7), 
            new Edge(7, 5, 1), new Edge(6, 1, 3),
            new Edge(2, 5, 3), new Edge(4, 1, 5), 
            new Edge(6, 7, 1), new Edge(4, 2, 4), 
			new Edge(3, 0, 2), new Edge(4, 7, 4)
		), 8, 0, 7, 5));
		// Dominika's test
		preparedTests.add(new Test(Arrays.asList(
			new Edge(0, 1, 7), new Edge(1, 2, 8),
			new Edge(0, 3, 5), new Edge(1, 3, 9),
			new Edge(1, 4, 7), new Edge(2, 4, 5),
			new Edge(3, 4, 15), new Edge(3, 5, 6),
			new Edge(4, 5, 8), new Edge(4, 6, 9),
			new Edge(5, 6, 11)
		), 7, 0, 6, 22));
		// http://eduinf.waw.pl/inf/alg/001_search/0141.php
		preparedTests.add(new Test(Arrays.asList(
			new Edge(0, 1, 4), new Edge(0, 7, 8),
			new Edge(1, 2, 8), new Edge(1, 7, 11),
			new Edge(2, 3, 7), new Edge(2, 5, 4),
			new Edge(2, 8, 2), new Edge(3, 5, 14),
			new Edge(3, 4, 9), new Edge(4, 5, 10),
			new Edge(5, 6, 2), new Edge(6, 7, 1),
			new Edge(6, 8, 6), new Edge(7, 8, 7)
		), 9, 0, 8, 14));
		// http://lcm.csa.iisc.ernet.in/dsa/node184.html
		preparedTests.add(new Test(Arrays.asList(
			new Edge(0, 1, 6), new Edge(0, 2, 1),
			new Edge(0, 3, 5), new Edge(1, 2, 5),
			new Edge(1, 4, 3), new Edge(2, 3, 5),
			new Edge(2, 4, 6), new Edge(2, 5, 4),
			new Edge(3, 5, 2), new Edge(4, 5, 6)
		), 6, 0, 4, 7));
		// star with one longer arm
		preparedTests.add(new Test(Arrays.asList(
			new Edge(0, 1, 1), new Edge(0, 2, 2),
			new Edge(0, 3, 3), new Edge(0, 4, 4),
			new Edge(0, 5, 5), new Edge(1, 6, 6),
			new Edge(6, 7, 10)
		), 8, 0, 6, 7));
		preparedTests.add(new Test(Arrays.asList(
			new Edge(0, 1, 1), new Edge(1, 2, 1),
			new Edge(2, 3, 1), new Edge(3, 4, 1),
			new Edge(4, 5, 1), new Edge(5, 6, 1),
			new Edge(6, 7, 1), new Edge(7, 8, 1),
			new Edge(8, 9, 1), new Edge(9, 0, 1),
			new Edge(9, 2, 1), new Edge(8, 3, 1),
			new Edge(7, 4, 1)
		), 10, 0, 7, 3));
		preparedTests.add(new Test(Arrays.asList(
			new Edge(0, 1, 2), new Edge(1, 2, 2),
			new Edge(2, 3, 2), new Edge(3, 4, 2),
			new Edge(4, 5, 2), new Edge(5, 0, 2),
			new Edge(1, 3, 4), new Edge(3, 5, 4),
			new Edge(5, 1, 4), new Edge(2, 4, 4),
			new Edge(4, 0, 4), new Edge(0, 2, 4),
			new Edge(0, 3, 5), new Edge(1, 4, 5),
			new Edge(2, 5, 5)
		), 6, 0, 4, 4));
		preparedTests.add(new Test(Arrays.asList(
			new Edge(0, 1, 2), new Edge(1, 2, 1),
			new Edge(2, 3, 5), new Edge(3, 4, 5),
			new Edge(4, 5, 1), new Edge(5, 0, 2),
			new Edge(1, 3, 3), new Edge(3, 5, 3),
			new Edge(5, 1, 3), new Edge(2, 4, 3),
			new Edge(4, 0, 3), new Edge(0, 2, 3),
			new Edge(0, 3, 4)
		), 6, 0, 5, 2));
		preparedTests.add(new Test(Arrays.asList(
			new Edge(0, 0, 1), new Edge(0, 0, 2),
			new Edge(0, 0, 3)
		), 1, 0, 0, 0));
	}

	public Boolean checkTest(Test test) {
		BnBAlgorithm algo = new BnBAlgorithm();
		List<Edge> e = algo.run(test.getEdges(), test.getN(), test.getStart(), test.getSink());

		int sum = 0;
        for (Edge edge: e) {
			sum += edge.getWeight();
        }
		if (sum != test.getResult()) {
			return false;
		}
		return true;
	}

	public Boolean runPreparedTests() {
		for (Test test: preparedTests) {
			if (!checkTest(test)) {
				return false;
			}
		}
		return true;
	}

}
