import java.util.*;

public class MaxFlowClass {

    // Returns true if there is a path from source 's' to sink 't' in residual graph.
    // Also fills parent[] to store the path
    private static boolean bfs(int[][] rGraph, int s, int t, int[] parent) {
        boolean[] visited = new boolean[rGraph.length];
        Arrays.fill(visited, false);

        Queue<Integer> q = new LinkedList<>();
        q.add(s);
        visited[s] = true;
        parent[s] = -1;

        while (!q.isEmpty()) {
            int u = q.poll();
            for (int v = 0; v < rGraph.length; v++) {
                if (!visited[v] && rGraph[u][v] > 0) {
                    q.add(v);
                    parent[v] = u;
                    visited[v] = true;
                }
            }
        }
        return visited[t];
    }

    // Returns the maximum flow from s to t in the given graph
    private static int fordFulkerson(int[][] graph, int s, int t) {
        int u, v;
        int[][] rGraph = new int[graph.length][graph.length];

        // Create a residual graph with capacities equal to the original graph
        for (u = 0; u < graph.length; u++)
            for (v = 0; v < graph.length; v++)
                rGraph[u][v] = graph[u][v];

        // This array is filled by BFS and to store the path from source to sink
        int[] parent = new int[graph.length];

        int maxFlow = 0;  // Initialize the maximum flow

        // Augment the flow while there is a path from source to sink
        while (bfs(rGraph, s, t, parent)) {
            // Find minimum residual capacity of the edges along the path filled by BFS
            int pathFlow = Integer.MAX_VALUE;
            for (v = t; v != s; v = parent[v]) {
                u = parent[v];
                pathFlow = Math.min(pathFlow, rGraph[u][v]);
            }

            // Update residual capacities of the edges and reverse edges along the path
            for (v = t; v != s; v = parent[v]) {
                u = parent[v];
                rGraph[u][v] -= pathFlow;
                rGraph[v][u] += pathFlow;
            }

            // Add path flow to overall flow
            maxFlow += pathFlow;
        }
        return maxFlow;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numInstances = scanner.nextInt();
        for (int i = 0; i < numInstances; i++) {
            int n = scanner.nextInt();
            int e = scanner.nextInt();
            int[][] graph = new int[n][n];
            for (int j = 0; j < e; j++) {
                int u = scanner.nextInt() - 1;  // Adjust to 0-indexed
                int v = scanner.nextInt() - 1;
                int w = scanner.nextInt();
                graph[u][v] += w;
            }
            System.out.println(fordFulkerson(graph, 0, n - 1));  // Source is node 1 and sink is node n
        }
        scanner.close();
    }
}
