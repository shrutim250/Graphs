import java.util.*;

class UnionFind {
    private int[] parent, rank;

    public UnionFind(int n) {
        parent = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i; // Each node is its own parent initially
            rank[i] = 1;   // Initial rank is 1
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

        if (rootU == rootV) return false; // Already connected

        // Attach smaller rank tree under root of the larger rank tree
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

public class UnionFindDemo {
    public static void main(String[] args) {
        int n = 5; // Number of elements (0 to 4)
        UnionFind uf = new UnionFind(n);

        // Performing union operations
        uf.union(0, 1);
        uf.union(1, 2);
        uf.union(3, 4);

        // Checking connectivity
        System.out.println("Are 0 and 2 connected? " + (uf.find(0) == uf.find(2))); // True
        System.out.println("Are 0 and 3 connected? " + (uf.find(0) == uf.find(3))); // False

        // Performing another union
        uf.union(2, 4);
        System.out.println("Are 0 and 3 connected after union? " + (uf.find(0) == uf.find(3))); // True
    }
}
