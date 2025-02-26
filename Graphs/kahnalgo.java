import java.util.*;

public class kahnalgo {

    // Function to return array containing vertices in
    // Topological order.
    public static int[] topologicalSort( List<List<Integer> > adj, int V)
    {
        // Array to store indegree of each vertex
        int[] indegree = new int[V];
        for (int i = 0; i < V; i++) {
            for (int it : adj.get(i)) {
                indegree[it]++;
            }
        }

        // Queue to store vertices with indegree 0
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < V; i++) {
            if (indegree[i] == 0) {
                q.offer(i);
            }
        }

        int[] result = new int[V];
        int index = 0;
        while (!q.isEmpty()) {
            int node = q.poll();
            result[index++] = node;

            // Decrease indegree of adjacent vertices as the
            // current node is in topological order
            for (int it : adj.get(node)) {
                indegree[it]--;

                // If indegree becomes 0, push it to the
                // queue
                if (indegree[it] == 0) {
                    q.offer(it);
                }
            }
        }

        // Check for cycle
        if (index != V) {
            System.out.println("Graph contains cycle!");
            return new int[0];
        }

        return result;
    }

    public static void kahn(String[] args)
    {
        // Number of nodes
        int n = 6;

        // Edges
        int[][] edges = { { 0, 1 }, { 1, 2 }, { 2, 3 },
                          { 4, 5 }, { 5, 1 }, { 5, 2 } };

        // Graph represented as an adjacency list
        List<List<Integer> > adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        // Constructing adjacency list
        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
        }

        // Performing topological sort
        System.out.println("Topological sorting of the graph: ");
        int[] result = topologicalSort(adj, n);

        // Displaying result
        for (int i : result) {
            System.out.print(i + " ");
        }
    }
}
