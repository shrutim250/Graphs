import java.util.ArrayList;
import java.util.List;

class GfG {

   // Recursive function for DFS traversal
    static void DFSRec(List<List<Integer> > adj, boolean[] visited, int s){
        // Mark the current vertex as visited
        visited[s] = true;  

        // Print the current vertex
        System.out.print(s + " ");

        // Recursively visit all adjacent vertices that are
        // not visited yet
        for (int i : adj.get(s)) {
            if (!visited[i]) {
                DFSRec(adj, visited, i);
            } 
        }
    }

    // Main DFS function that initializes the visited array
    static void DFS(List<List<Integer> > adj, int s) {
        boolean[] visited = new boolean[adj.size()];
        // Call the recursive DFS function
        DFSRec(adj, visited, s);
    }   
  
    // Function to add an edge to the adjacency list
    static void addEdge(List<List<Integer> > adj, int s, int t){        
        // Add edge from vertex s to t
        adj.get(s).add(t);
        // Due to undirected Graph
        adj.get(t).add(s);
    }


    public static void main(String[] args)
    {
        int V = 5; // Number of vertices in the graph

        // Create an adjacency list for the graph
        List<List<Integer> > adj = new ArrayList<>(V);
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }

        // Define the edges of the graph
        int[][] edges = {
            { 1, 2 }, { 1, 0 }, { 2, 0 }, { 2, 3 }, { 2, 4 }
        };

        // Populate the adjacency list with edges
        for (int[] e : edges) {
            addEdge(adj, e[0], e[1]);
        }

        int source = 1; 
        System.out.println("DFS from source: " + source);
        DFS(adj, source); 
    }
}

