import java.util.*;

class Test {
	int n;
	List<Edge> edges;
	int result;
	public Test(List<Edge> edges, int n, int result){
		this.edges = edges;
		this.n = n;
		this.result = result;
	}

	public int getN() { return n; }
	public List<Edge> getEdges() { return edges; }
	public int getResult() { return result; }
}
class Tests {
	// initializar el grafo
	ArrayList<Test> preparedTests;

	public Tests(){
		preparedTests = new ArrayList();
		// Dominika's test
		preparedTests.add(new Test(Arrays.asList(
			new Edge(0, 1, 7), new Edge(1, 2, 8),
			new Edge(0, 3, 5), new Edge(1, 3, 9),
			new Edge(1, 4, 7), new Edge(2, 4, 5),
			new Edge(3, 4, 15), new Edge(3, 5, 6),
			new Edge(4, 5, 8), new Edge(4, 6, 9),
			new Edge(5, 6, 11)
		), 7, 39));
		// http://eduinf.waw.pl/inf/alg/001_search/0141.php
		preparedTests.add(new Test(Arrays.asList(
			new Edge(0, 1, 4), new Edge(0, 7, 8),
			new Edge(1, 2, 8), new Edge(1, 7, 11),
			new Edge(2, 3, 7), new Edge(2, 5, 4),
			new Edge(2, 8, 2), new Edge(3, 5, 14),
			new Edge(3, 4, 9), new Edge(4, 5, 10),
			new Edge(5, 6, 2), new Edge(6, 7, 1),
			new Edge(6, 8, 6), new Edge(7, 8, 7)
		), 9, 37));
		// http://lcm.csa.iisc.ernet.in/dsa/node184.html
		preparedTests.add(new Test(Arrays.asList(
			new Edge(0, 1, 6), new Edge(0, 2, 1),
			new Edge(0, 3, 5), new Edge(1, 2, 5),
			new Edge(1, 4, 3), new Edge(2, 3, 5),
			new Edge(2, 4, 6), new Edge(2, 5, 4),
			new Edge(3, 5, 2), new Edge(4, 5, 6)
		), 6, 15));
		// star with one longer arm
		preparedTests.add(new Test(Arrays.asList(
			new Edge(0, 1, 1), new Edge(0, 2, 2),
			new Edge(0, 3, 3), new Edge(0, 4, 4),
			new Edge(0, 5, 5), new Edge(1, 6, 6),
			new Edge(6, 7, 10)
		), 8, 31));
		preparedTests.add(new Test(Arrays.asList(
			new Edge(0, 1, 1), new Edge(1, 2, 1),
			new Edge(2, 3, 1), new Edge(3, 4, 1),
			new Edge(4, 5, 1), new Edge(5, 6, 1),
			new Edge(6, 7, 1), new Edge(7, 8, 1),
			new Edge(8, 9, 1), new Edge(9, 0, 1),
			new Edge(9, 2, 1), new Edge(8, 3, 1),
			new Edge(7, 4, 1)
		), 10, 9));
		preparedTests.add(new Test(Arrays.asList(
			new Edge(0, 1, 2), new Edge(1, 2, 2),
			new Edge(2, 3, 2), new Edge(3, 4, 2),
			new Edge(4, 5, 2), new Edge(5, 0, 2),
			new Edge(1, 3, 4), new Edge(3, 5, 4),
			new Edge(5, 1, 4), new Edge(2, 4, 4),
			new Edge(4, 0, 4), new Edge(0, 2, 4),
			new Edge(0, 3, 5), new Edge(1, 4, 5),
			new Edge(2, 5, 5)
		), 6, 10));
		preparedTests.add(new Test(Arrays.asList(
			new Edge(0, 1, 2), new Edge(1, 2, 1),
			new Edge(2, 3, 5), new Edge(3, 4, 5),
			new Edge(4, 5, 1), new Edge(5, 0, 2),
			new Edge(1, 3, 3), new Edge(3, 5, 3),
			new Edge(5, 1, 3), new Edge(2, 4, 3),
			new Edge(4, 0, 3), new Edge(0, 2, 3),
			new Edge(0, 3, 4)
		), 6, 9));
		preparedTests.add(new Test(Arrays.asList(
		), 1, 0));
		preparedTests.add(new Test(Arrays.asList(
			new Edge(0, 0, 1), new Edge(0, 0, 2),
			new Edge(0, 0, 3)
		), 1, 0));
	}

	private Boolean checkCyclesRec(int parent, int current, Set visited, int[][] connections, int n) {
		visited.add(current);
		for (int i = 0; i < n; i++) {
			if (connections[current][i] != -1) {
				if (i != parent) {
					if (visited.contains(i) || !checkCyclesRec(current, i, visited, connections, n)) {
						return false;
					}
				}
			}
		}
		return true;
	}

	private Boolean checkCycles(Test test, List<Edge> edges) {
		Set visited = new HashSet();
		int n = test.getN();
		int[][] connections = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				connections[i][j] = -1;
			}
		}
		for (Edge edge: edges) {
			int a = edge.getDestination();
			int b = edge.getSource();
			int w = edge.getWeight();
			if (connections[a][b] != -1) {
				return false;
			}
			connections[a][b] = w;
			connections[b][a] = w;
		}
		visited.add(0);
		return checkCyclesRec(0, 0, visited, connections, test.getN()) && visited.size() == test.getN();
	}

	public Boolean checkTest(Test test) {
		List<Edge> e = Kruskal.KruskalAlgorithm(test.getEdges(), test.getN());
		int sum = 0;
        for (Edge edge: e) {
			sum += edge.getWeight();
        }
		if (!checkCycles(test, e)){
			return false;
		}
		if (sum != test.getResult()) {
			return false;
		}
		if (e.size() + 1 != test.getN()) {
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

	public Boolean runFuzzTests(int n) {
		for (int i = 0; i < n; i++) {
			Test test = getRandomTest();
			if (!checkTest(test)) {
				return false;
			}
		}
		return true;
	}
	private static Test getRandomTest() {
		Random rand = new Random();
		ArrayList<Edge> edges = new ArrayList();
		int val;
		int total_sum = 0;
		int n = 100;
		for (int i = 1; i < n; i++) {
			val = rand.nextInt(100) + 1;
			Edge e = new Edge(rand.nextInt(i), i, val);
			edges.add(e);
			total_sum += val;
		}
		for (int i = 0; i < n / 2; i++) {
			val = rand.nextInt(100) + 100;
			Edge e = new Edge (rand.nextInt(n), rand.nextInt(n), val);
			edges.add(e);
		}
		Collections.shuffle(edges);
		Test test = new Test(edges, n, total_sum);
		return test;
	}
}
