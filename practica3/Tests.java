import java.util.*;

class Pair<A, B> {
    private A first;
    private B second;

    public Pair(A first, B second) {
        this.first = first;
        this.second = second;
    }

    public A getFirst() {
        return first;
    }

    public B getSecond() {
        return second;
    }
}

class Test {
    private Graph g;
    private int bestSolution;

    public Test(Graph g, int bestSolution) {
        this.g = g;
        this.bestSolution = bestSolution;
    }

    public Test(int n, List<Pair<Integer, Integer>> edges, int bestSolution) {
        this.g = new Graph(n);
        this.bestSolution = bestSolution;
        for (Pair<Integer, Integer> i : edges)
            this.g.addEdge(i.getFirst(), i.getSecond());
    }

    public Graph getGraph() {
        return g;
    }

    public int getbestSolution() {
        return bestSolution;
    }
}

class Tests {
    private ArrayList<Test> preparedTests;

    public Tests() {
        preparedTests = new ArrayList<Test>();
        preparedTests.add(new Test(1, Arrays.asList(
        ), 0));
        preparedTests.add(new Test(2, Arrays.asList(
                new Pair<>(0, 1)
        ), 1));
        preparedTests.add(new Test(3, Arrays.asList(
                new Pair<>(0, 1), new Pair<>(1, 2)
        ), 1));
        preparedTests.add(new Test(3, Arrays.asList(
                new Pair<>(0, 1), new Pair<>(1, 2), new Pair<>(0, 2)
        ), 1));
        preparedTests.add(new Test(10, Arrays.asList(
                new Pair<>(0, 1), new Pair<>(1, 2), new Pair<>(2, 3),
                new Pair<>(3, 4), new Pair<>(4, 5), new Pair<>(5, 6),
                new Pair<>(6, 7), new Pair<>(7, 8), new Pair<>(8, 9),
                new Pair<>(9, 0)
        ), 5));
        preparedTests.add(new Test(11, Arrays.asList(
                new Pair<>(0, 1), new Pair<>(1, 2), new Pair<>(2, 3),
                new Pair<>(3, 4), new Pair<>(4, 5), new Pair<>(5, 6),
                new Pair<>(6, 7), new Pair<>(7, 8), new Pair<>(8, 9),
                new Pair<>(9, 10), new Pair<>(10, 0)
        ), 6));
        preparedTests.add(new Test(6, Arrays.asList(
                new Pair<>(0, 1), new Pair<>(0, 2), new Pair<>(0, 3),
                new Pair<>(0, 4), new Pair<>(0, 5), new Pair<>(1, 2),
                new Pair<>(1, 3), new Pair<>(1, 4), new Pair<>(1, 5),
                new Pair<>(2, 3), new Pair<>(2, 4), new Pair<>(2, 5),
                new Pair<>(3, 4), new Pair<>(3, 5), new Pair<>(4, 5)
        ), 6));
        preparedTests.add(new Test(9, Arrays.asList(
                new Pair<>(0, 1), new Pair<>(1, 2), new Pair<>(0, 3),
                new Pair<>(0, 4), new Pair<>(3, 4), new Pair<>(1, 5),
                new Pair<>(1, 6), new Pair<>(5, 6), new Pair<>(2, 7),
                new Pair<>(2, 8), new Pair<>(7, 8)
        ), 6));
        preparedTests.add(new Test(12, Arrays.asList(
                new Pair<>(0, 1), new Pair<>(1, 2), new Pair<>(0, 3),
                new Pair<>(0, 4), new Pair<>(0, 2), new Pair<>(1, 5),
                new Pair<>(1, 6), new Pair<>(2, 8), new Pair<>(2, 7),
                new Pair<>(0, 9), new Pair<>(1, 10), new Pair<>(2,11)
        ), 3));
        preparedTests.add(new Test(6, Arrays.asList(
                new Pair<>(0, 1), new Pair<>(1, 2), new Pair<>(1, 5),
                new Pair<>(1, 4), new Pair<>(2, 4), new Pair<>(5, 4),
                new Pair<>(4, 3)
        ), 2));

    }

    public void checkDefaultTests() {
        int index = 0;
        for (Test test : this.preparedTests) {
            index++;
            System.out.println("Default test #" + index + "\n" + checkTest(test));
        }
    }

    public static String checkTest(Test test) {
        String errorSTR = "#######\n#ERROR#\n#######\n";
        String okSTR = "#######\n# OK  #\n#######\n";

        ArrayList<Integer> solution = Graph.vertexCover(test.getGraph());
        String ans = "";
        ans = "Graph:\n" + test.getGraph().toString() + "\n" + "Solution: " + solution + "\n";
        if (solution.size() <= test.getbestSolution() * 2 &&
                solution.size() >= test.getbestSolution() &&
                solution.size() <= test.getGraph().getN()) {
            for (int i : solution) {
                test.getGraph().detachVertex(i);
            }
            if (test.getGraph().getM() == 0) {
                ans += okSTR;
            } else {
                ans += errorSTR;
            }
        } else {
            ans += errorSTR;
        }
        return ans;

    }
}
