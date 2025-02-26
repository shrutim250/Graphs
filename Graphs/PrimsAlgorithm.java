import java.util.*;

class Edge {
    int destination, weight;

    Edge(int destination, int weight) {
        this.destination = destination;
        this.weight = weight;
    }
}

public class PrimsAlgorithm {
    public static int primsMST(int n, List<List<Edge>> graph) {
        boolean[] visited = new boolean[n]; // Track visited nodes
        PriorityQueue<Edge> minHeap = new PriorityQueue<>(Comparator.comparingInt(e -> e.weight));
        
        minHeap.add(new Edge(0, 0)); // Start from node 0
        int mstCost = 0;
        int edgesUsed = 0;

        while (!minHeap.isEmpty() && edgesUsed < n) {
            Edge current = minHeap.poll();
            int node = current.destination;

            if (visited[node]) continue; // Skip already visited nodes

            visited[node] = true;
            mstCost += current.weight;
            edgesUsed++;

            for (Edge neighbor : graph.get(node)) {
                if (!visited[neighbor.destination]) {
                    minHeap.add(new Edge(neighbor.destination, neighbor.weight));
                }
            }
        }

        return mstCost;
    }

    public static void main(String[] args) {
        int n = 5; // Number of nodes
        List<List<Edge>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) graph.add(new ArrayList<>());

        // Adding edges: (node1, node2, weight)
        addEdge(graph, 0, 1, 2);
        addEdge(graph, 0, 3, 6);
        addEdge(graph, 1, 2, 3);
        addEdge(graph, 1, 3, 8);
        addEdge(graph, 1, 4, 5);
        addEdge(graph, 2, 4, 7);
        addEdge(graph, 3, 4, 9);

        System.out.println("Minimum Cost of MST: " + primsMST(n, graph));
    }

    private static void addEdge(List<List<Edge>> graph, int u, int v, int weight) {
        graph.get(u).add(new Edge(v, weight));
        graph.get(v).add(new Edge(u, weight));
    }
}
