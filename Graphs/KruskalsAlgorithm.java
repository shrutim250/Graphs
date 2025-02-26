import java.util.*;

class Edge implements Comparable<Edge> {
    int source, destination, weight;

    Edge(int source, int destination, int weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }

    // Sorting edges based on weight (ascending order)
    public int compareTo(Edge other) {
        return this.weight - other.weight;
    }
}

class UnionFind {
    private int[] parent, rank;

    public UnionFind(int n) {
        parent = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }

    // Find with Path Compression
    public int find(int node) {
        if (parent[node] != node) {
            parent[node] = find(parent[node]); // Path Compression
        }
        return parent[node];
    }

    // Union by Rank
    public boolean union(int u, int v) {
        int rootU = find(u);
        int rootV = find(v);

        if (rootU == rootV) return false; // Cycle detected

        if (rank[rootU] > rank[rootV]) {
            parent[rootV] = rootU;
        } else if (rank[rootU] < rank[rootV]) {
            parent[rootU] = rootV;
        } else {
            parent[rootV] = rootU;
            rank[rootU]++;
        }
        return true;
    }
}

public class KruskalsAlgorithm {
    public static List<Edge> kruskalMST(int n, List<Edge> edges) {
        Collections.sort(edges); // Step 1: Sort edges by weight
        UnionFind uf = new UnionFind(n);
        List<Edge> mst = new ArrayList<>();
        int mstCost = 0;

        for (Edge edge : edges) {
            if (uf.union(edge.source, edge.destination)) { // Step 2: Add edge if no cycle
                mst.add(edge);
                mstCost += edge.weight;
                if (mst.size() == n - 1) break; // Stop when we have n-1 edges
            }
        }

        System.out.println("Minimum Cost of MST: " + mstCost);
        return mst;
    }

    public static void main(String[] args) {
        int n = 6; // Number of nodes
        List<Edge> edges = new ArrayList<>();

        // Adding edges: (source, destination, weight)
        edges.add(new Edge(0, 1, 4));
        edges.add(new Edge(0, 2, 4));
        edges.add(new Edge(1, 2, 2));
        edges.add(new Edge(1, 3, 5));
        edges.add(new Edge(2, 3, 8));
        edges.add(new Edge(2, 4, 10));
        edges.add(new Edge(3, 4, 9));
        edges.add(new Edge(3, 5, 4));
        edges.add(new Edge(4, 5, 6));

        List<Edge> mst = kruskalMST(n, edges);

        System.out.println("Edges in the MST:");
        for (Edge edge : mst) {
            System.out.println(edge.source + " - " + edge.destination + " : " + edge.weight);
        }
    }
}

