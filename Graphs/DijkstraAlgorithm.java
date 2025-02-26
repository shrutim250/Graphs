import java.util.*;

class Edge {
    int destination, weight;
    
    Edge(int destination, int weight) {
        this.destination = destination;
        this.weight = weight;
    }
}

public class DijkstraAlgorithm {
    public static int[] dijkstra(int n, List<List<Edge>> graph, int source) {
        int[] dist = new int[n]; // Stores shortest distances from source
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[source] = 0;

        PriorityQueue<Edge> minHeap = new PriorityQueue<>(Comparator.comparingInt(e -> e.weight));
        minHeap.add(new Edge(source, 0));

        while (!minHeap.isEmpty()) {
            Edge current = minHeap.poll();
            int node = current.destination;
            int currentWeight = current.weight;

            if (currentWeight > dist[node]) continue; // Skip outdated paths

            for (Edge neighbor : graph.get(node)) {
                int newDist = dist[node] + neighbor.weight;
                if (newDist < dist[neighbor.destination]) {
                    dist[neighbor.destination] = newDist;
                    minHeap.add(new Edge(neighbor.destination, newDist));
                }
            }
        }

        return dist; // Returns shortest distances from the source
    }

    public static void main(String[] args) {
        int n = 5; // Number of nodes
        List<List<Edge>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) graph.add(new ArrayList<>());

        // Adding edges: (node1, node2, weight)
        addEdge(graph, 0, 1, 10);
        addEdge(graph, 0, 4, 3);
        addEdge(graph, 1, 2, 2);
        addEdge(graph, 1, 4, 4);
        addEdge(graph, 2, 3, 9);
        addEdge(graph, 3, 2, 7);
        addEdge(graph, 4, 1, 1);
        addEdge(graph, 4, 2, 8);
        addEdge(graph, 4, 3, 2);

        int source = 0;
        int[] shortestDistances = dijkstra(n, graph, source);

        System.out.println("Shortest distances from node " + source + ": " + Arrays.toString(shortestDistances));
    }

    private static void addEdge(List<List<Edge>> graph, int u, int v, int weight) {
        graph.get(u).add(new Edge(v, weight));
        graph.get(v).add(new Edge(u, weight)); // Remove this line for directed graphs
    }
}
