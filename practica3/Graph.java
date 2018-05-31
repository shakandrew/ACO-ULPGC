import java.util.*;

class Graph {
    private int n, m; // n - vertex number, m - number of edges
    private ArrayList<Set<Integer>> edges;

    public Graph(int n) {
        this.n = n;
        this.m = 0;
        this.edges = new ArrayList<Set<Integer>>();
        for (int i = 0; i < n; i++)
            this.edges.add(new HashSet<Integer>());
    }

    public int getN() {
        return this.n;
    }

    public int getM() {
        return this.m;
    }

    public void addEdge(int vertex1, int vertex2) {
        if (!this.edges.get(vertex1).contains(vertex2) && vertex1!=vertex2) {
            this.m++;
            this.edges.get(vertex1).add(vertex2);
            this.edges.get(vertex2).add(vertex1);
        }
    }

    public void delEdge(int vertex1, int vertex2) {
        if (this.edges.get(vertex1).contains(vertex2)) {
            this.m--;
            this.edges.get(vertex1).remove(vertex2);
            this.edges.get(vertex2).remove(vertex1);
        }
    }

    public void detachVertex(int vertex) {
        for (int neighbour : this.edges.get(vertex)) {
            this.edges.get(neighbour).remove(vertex);
            this.m--;
        }
        this.edges.set(vertex, new HashSet<>());
    }

    public Graph copy() {
        Graph obj = new Graph(this.n);
        obj.m = this.m;
        for (int i = 0; i < this.edges.size(); i++) {
            obj.edges.add(new HashSet<Integer>());
            for (int neighbour : this.edges.get(i))
                obj.edges.get(i).add(neighbour);
        }
        return obj;
    }

    public static ArrayList<Integer> vertexCover(Graph obj) {
        int vertex1, vertex2;
        Graph g = obj.copy();
        Random rand = new Random();
        ArrayList<Integer> ans = new ArrayList<Integer>();
        ArrayList<Integer> unused = new ArrayList<Integer>();
        for (int i = 0; i < g.n; i++)
            if (g.edges.get(i).size() > 0)
                unused.add(i);

        while (g.m > 0) {
            int ind1 = rand.nextInt(unused.size());
            int ind2 = rand.nextInt(unused.size());

            if (g.edges.get(unused.get(ind1)).contains(unused.get(ind2))) {
                if (ind1 < ind2) {
                    vertex2 = unused.remove(ind2);
                    vertex1 = unused.remove(ind1);
                } else {
                    vertex1 = unused.remove(ind1);
                    vertex2 = unused.remove(ind2);
                }
                g.detachVertex(vertex1);
                g.detachVertex(vertex2);
                ans.add(vertex1);
                ans.add(vertex2);
            }
        }
        return ans;
    }

    @Override
    public String toString() {
        StringBuilder ans = new StringBuilder();
        int index = 0;
        for (Set<Integer> i : this.edges) {
            ans.append("\t[" + index + "]: " + i + "\n");
            index++;
        }
        return ans.toString();
    }
}
