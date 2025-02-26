import java.util.*;

class Edge {
    int source, destination, weight;
    
    Edge(int source, int destination, int weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }
}

public class BellmanFordAlgorithm {
    public static int[] bellmanFord(int n, List<Edge> edges, int source) {
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[source] = 0;

        // Relax all edges (n-1) times
        for (int i = 0; i < n - 1; i++) {
            for (Edge edge : edges) {
                if (dist[edge.source] != Integer.MAX_VALUE && dist[edge.source] + edge.weight < dist[edge.destination]) {
                    dist[edge.destination] = dist[edge.source] + edge.weight;
                }
            }
        }

        // Detect negative weight cycles
        for (Edge edge : edges) {
            if (dist[edge.source] != Integer.MAX_VALUE && dist[edge.source] + edge.weight < dist[edge.destination]) {
                System.out.println("Graph contains a negative weight cycle.");
                return null;
            }
        }

        return dist; // Shortest distances from source
    }

    public static void main(String[] args) {
        int n = 5; // Number of nodes
        List<Edge> edges = new ArrayList<>();

        // Adding edges: (source, destination, weight)
        edges.add(new Edge(0, 1, 6));
        edges.add(new Edge(0, 2, 7));
        edges.add(new Edge(1, 2, 8));
        edges.add(new Edge(1, 3, 5));
        edges.add(new Edge(1, 4, -4));
        edges.add(new Edge(2, 3, -3));
        edges.add(new Edge(2, 4, 9));
        edges.add(new Edge(3, 1, -2));
        edges.add(new Edge(4, 3, 7));

        int source = 0;
        int[] shortestDistances = bellmanFord(n, edges, source);

        if (shortestDistances != null) {
            System.out.println("Shortest distances from node " + source + ": " + Arrays.toString(shortestDistances));
        }
    }
}

