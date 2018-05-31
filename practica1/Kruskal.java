import java.util.*;

//una structura de aristas
class Edge {
	int source, destination, weight;

	public Edge(int source, int destination, int weight) {
		this.source = source;
		this.destination = destination;
		this.weight = weight;
	}

	public int getWeight() { return weight; }
	public int getSource() { return source; }
	public int getDestination() { return destination; }

	@Override
	public String toString() {
		return "(" + source + " - " + destination + " : " + weight + ")";
	}
};
class Kruskal {


    public static Map<Integer, Integer> createSet(int n) {
		Map<Integer, Integer> parent = new HashMap<>();
        //crear un conjunto para cada vertice
        for (int i = 0; i < n; i++)
            parent.put(i, i);
		return parent;
    }

    //buscar el root del conjunto en que hay elemento k
    private static int findRoot(int k, Map<Integer, Integer> parent) {
        if (parent.get(k) == k)
            return k;
        return findRoot(parent.get(k), parent);
    }

	private static int distanceToRoot(int k, Map<Integer, Integer> parent) {
		if (parent.get(k) == k)
			return 0;
		return distanceToRoot(k, parent) + 1;
	}

    //unir dos subconjuntos
    private static void union(int a, int b, Map<Integer, Integer> parent) {
        int x = findRoot(a, parent);
        int y = findRoot(b, parent);
		if (distanceToRoot(x, parent) < distanceToRoot(y, parent)) {
	        parent.put(x, y);
		} else {
	        parent.put(y, x);
		}
    }

    //construir arbol de expansion minima
    public static List<Edge> KruskalAlgorithm(List<Edge> edges, int n) {
        //lista de aristas de arbol de expansion minima
        List<Edge> minimumSpanningTree = new ArrayList();
		
		//ordenar segun peso creciente
        Collections.sort(edges, (a, b) -> a.weight - b.weight);

        //initializar subconjuntos para cada vertice 
        // KruskalMain ds = new KruskalMain();
		Map<Integer, Integer> parent = createSet(n);
        // ds.createSet(n);

        int index = 0;

        //el numero de aristas es n-1 siendo n el numero de vertices
        while (minimumSpanningTree.size() != n - 1) {
            //el proximo arista con peso minimo
            Edge minEdge = edges.get(index++);

            //buscar root de conjuntos con dos vertices de minEdge
            int x = findRoot(minEdge.source, parent);
            int y = findRoot(minEdge.destination, parent);

            //si tienen roots diferentes entonces no hay ciclos y podemos anadir arista al arbol
            if (x != y) {
                minimumSpanningTree.add(minEdge);
                union(x, y, parent);
            }
        }
        return minimumSpanningTree;
    }
}
