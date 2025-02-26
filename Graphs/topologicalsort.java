// Java program to find topological sort.

import java.util.*;

class GfG {

    // Function to perform DFS and topological sorting
    static void topologicalSortUtil(int v, ArrayList<ArrayList<Integer>> adj, boolean[] visited, Stack<Integer> st) {
        
        // Mark the current node as visited
        visited[v] = true;

        // Recur for all adjacent vertices
        for (int i : adj.get(v)) {
            if (!visited[i]) {
                topologicalSortUtil(i, adj, visited, st);
            }
        }

        // Push current vertex to stack 
        // which stores the result
        st.push(v);
    }

    // Function to perform Topological Sort
    static ArrayList<Integer> topologicalSort(ArrayList<ArrayList<Integer>> adj) {
        int V = adj.size();
        
        // Stack to store the result
        Stack<Integer> st = new Stack<>();
        boolean[] visited = new boolean[V];

        // Call the recursive helper function to store
        // Topological Sort starting from all vertices one by one
        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                topologicalSortUtil(i, adj, visited, st);
            }
        }
        
        ArrayList<Integer> ans = new ArrayList<>();

        // Append contents of stack
        while (!st.isEmpty()) {
            ans.add(st.pop());
        }
        
        return ans;
    }

    public static void main(String[] args) {

        // Graph represented as an adjacency list
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        adj.add(new ArrayList<>(List.of(1)));
        adj.add(new ArrayList<>(List.of(2)));
        adj.add(new ArrayList<>());
        adj.add(new ArrayList<>(List.of(1, 2)));

        ArrayList<Integer> ans = topologicalSort(adj);
        
        for (int node : ans) {
            System.out.print(node + " ");
        }
        System.out.println();
    }
}
