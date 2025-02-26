// A Java Program to detect
// cycle in an undirected graph

import java.util.ArrayList;

class GfG {

    // A recursive function that
    // uses visited[] and parent to detect
    // cycle in subgraph reachable
    // from vertex v.
    static boolean isCycleUtil(int v, ArrayList<ArrayList<Integer>> adj, boolean[] visited, int parent) {
                      
        // Mark the current node as visited
        visited[v] = true;

        // Recur for all the vertices
        // adjacent to this vertex
        for (int i : adj.get(v)) {
            
            // If an adjacent vertex is not visited,
            // then recur for that adjacent
            if (!visited[i]) {
                if (isCycleUtil(i, adj, visited, v))
                    return true;
            }
            
            // If an adjacent vertex is visited and
            // is not parent of current vertex,
            // then there exists a cycle in the graph.
            else if (i != parent)
                return true;
        }
        
        return false;
    }

    // Returns true if the graph contains
    // a cycle, else false.
    static boolean isCycle(ArrayList<ArrayList<Integer>> adj) {
        int V = adj.size();
        
        // Mark all the vertices as not visited
        boolean[] visited = new boolean[V];

        // Call the recursive helper function
        // to detect cycle in different DFS trees
        for (int u = 0; u < V; u++) {
            
            // Don't recur for u if 
            // it is already visited
            if (!visited[u])
                if (isCycleUtil(u, adj, visited, -1))
                    return true;
        }
        
        return false;
    }

    public static void main(String[] args) {
        int V = 3;
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>(V);

        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }

        adj.get(1).add(0);
        adj.get(0).add(1);
        adj.get(0).add(2);
        adj.get(2).add(0);
        adj.get(1).add(2);
        adj.get(2).add(1);

        if (isCycle(adj)) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
    }
}

