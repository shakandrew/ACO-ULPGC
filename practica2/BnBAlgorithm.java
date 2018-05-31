import java.util.*;

class Edge {
    private int vertex1, vertex2;
    private int weight;

	public Edge(int source, int destination, int weight) {
		this.vertex1 = source;
		this.vertex2 = destination;
		this.weight = weight;
	}

	public int getWeight() { return weight; }
	public int getVertix1() { return vertex1; }
	public int getVertix2() { return vertex2; }

	@Override
	public String toString() {
		return "(" + vertex1 + " - " + vertex2 + " : " + weight + ")";
	}
}

class BnBAlgorithm {

    private Map<Integer, Map<Integer, Integer>> distances;
    private List<Integer> shortestPath;
    private List<Integer> tmpShortestPath;
    private int bound;
    private List<Edge> edges;
    private int n;
    private int sink;
    private int source;
    private Map<Integer, Integer> parents;
    
    private void setDistances(List<Edge> edges) {
        for(Edge e : edges) {
            if( !distances.containsKey(e.getVertix1())) {
                distances.put(e.getVertix1(), new HashMap<>());
            }
            distances.get(e.getVertix1()).put(e.getVertix2(), e.getWeight());

            if( !distances.containsKey(e.getVertix2())) {
                distances.put(e.getVertix2(), new HashMap<>());
            }
            distances.get(e.getVertix2()).put(e.getVertix1(), e.getWeight());
        }
    }

    private void setParent(int child, int parent) {
        parents.put(child, parent);
    }

    public BnBAlgorithm() {
        bound = 0;
        distances = new HashMap<>();
        shortestPath = new ArrayList<>();
        edges = new ArrayList<>();
        tmpShortestPath = new ArrayList<>();
        parents = new HashMap<>();
    };

    private List<Edge> getGreedyShortestPath() {
        List<Edge> shortestPath = new ArrayList<>();
        List<Edge> visitedEdges = new ArrayList<>();
        int v = source;
        Edge minWeightEdge = new Edge(-1, -1, Integer.MAX_VALUE);

        while( v != sink ) {
            for(Edge e : edges) {
                if( !visitedEdges.contains(e)) {
                    int v1 = e.getVertix1();
                    int v2 = e.getVertix2();
    
                    if( v1 == v ) {
                        setParent( v2, v );
    
                        if(e.getWeight() < minWeightEdge.getWeight()) {
                            minWeightEdge = e;
                        }
                    } else if( v2 == v ) {
                        setParent( v1, v );
    
                        if(e.getWeight() < minWeightEdge.getWeight()) {
                            minWeightEdge = e;
                        }
                    }    
                }
            }

            if( minWeightEdge.getWeight() < Integer.MAX_VALUE ) {
                v = (v == minWeightEdge.getVertix1() ? minWeightEdge.getVertix2() : minWeightEdge.getVertix1());

                visitedEdges.add(minWeightEdge);
                shortestPath.add(minWeightEdge);
                minWeightEdge = new Edge(-1, -1, Integer.MAX_VALUE);
            } else {
                if( shortestPath.size() > 0 ) {
                    shortestPath.remove(shortestPath.get(shortestPath.size()-1));
                }
                v = parents.get(v);
            }
        }
        return shortestPath;
    }

    private int getDistance(List<Integer> path) {
        int distance = 0;
        
        for(int i=0; i<path.size()-1; i++) {
            int weight = distances.get(path.get(i)).get(path.get(i+1));
            distance += weight;
        }
        return distance;
    }

    private void dfsVisit(int v) {
        tmpShortestPath.add(v);
        int distance = getDistance(tmpShortestPath);
        
        if( distance < bound ) {
            if( v != sink ) {
                for(Edge e : edges) {
                    int v1 = e.getVertix1();
                    int v2 = e.getVertix2();
        
                    if( v1 == v && !tmpShortestPath.contains(v2)) {
                        dfsVisit(v2);
                    } else if( v2 == v && !tmpShortestPath.contains(v1)) {
                        dfsVisit(v1);
                    }
                }        
            } else {
                shortestPath = new ArrayList<>(tmpShortestPath);
                
                for(int i=0; i<shortestPath.size()-1; i++) {
                    Integer weight = distances.get(shortestPath.get(i)).get(shortestPath.get(i+1));
                    // System.out.println( shortestPath.get(i) + " - " + shortestPath.get(i+1) + " : " + weight);
                }        

                bound = distance;
            }                    
        }

        tmpShortestPath.remove(tmpShortestPath.size()-1);
    }

    private List<Edge> findShortestPath() {
        setDistances(edges);

        List<Edge> greedyPath = getGreedyShortestPath();
        int greedyPathWeight;

        for(Edge e : greedyPath) {
            int v1 = e.getVertix1();
            int v2 = e.getVertix2();

            if( !shortestPath.contains(v1)) {
                shortestPath.add(v1);
            }
            if( !shortestPath.contains(v2)) {
                shortestPath.add(v2);
            }
        }

        for(Edge e : greedyPath) {
            bound += e.getWeight();
        }
        greedyPathWeight = bound;

        dfsVisit(source);

        if( greedyPathWeight == getDistance(shortestPath)) {
            return greedyPath;
        } else {
            List<Edge> path = new ArrayList<>();

            for(int i=0; i<shortestPath.size()-1; i++) {
                int v1 = shortestPath.get(i);
                int v2 = shortestPath.get(i+1);

                for(Edge e : edges) {
                    if( v1 == e.getVertix1() && v2 == e.getVertix2() || v1 == e.getVertix2() && v2 == e.getVertix1() ) {
                        path.add(e);
                        break;
                    }
                }
            }
            return path;
        }
    }

    public List<Edge> run(List<Edge> edgesList, int n, int source, int sink) {
        edges = new ArrayList<>(edgesList);
        this.source = source;
        this.sink = sink;
        this.n = n;

        return findShortestPath();
    }
}
