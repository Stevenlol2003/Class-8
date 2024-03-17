import java.util.*;

public class Matching {

    static int[][] graph;
    static int[] parent;
    static boolean[] visited;
    static int m, n;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        for (int i = 0; i < t; i++) {
            m = sc.nextInt();
            n = sc.nextInt();
            int q = sc.nextInt();
            graph = new int[m+n+2][m+n+2];
            for (int j = 0; j < q; j++) {
                int a = sc.nextInt() - 1;
                int b = sc.nextInt() - 1 + m;
                graph[a][b] = 1;
            }
            maxMatching();
        }
    }

    static void maxMatching() {
        int maxFlow = 0;
        while (true) {
            int flow = bfs();
            if (flow == 0) break;
            maxFlow += flow;
        }
        if (maxFlow == Math.min(m, n)) {
            System.out.println(maxFlow + " Y");
        } else {
            System.out.println(maxFlow + " N");
        }
    }

    static int bfs() {
        parent = new int[m+n+2];
        Arrays.fill(parent, -1);
        visited = new boolean[m+n+2];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        visited[0] = true;
        while (!queue.isEmpty()) {
            int u = queue.poll();
            for (int v = 0; v <= m+n+1; v++) {
                if (!visited[v] && graph[u][v] > 0) {
                    queue.add(v);
                    parent[v] = u;
                    visited[v] = true;
                }
            }
        }
        if (!visited[m+n+1]) return 0;
        int flow = Integer.MAX_VALUE;
        for (int v = m+n+1; v != 0; v = parent[v]) {
            int u = parent[v];
            flow = Math.min(flow, graph[u][v]);
        }
        for (int v = m+n+1; v != 0; v = parent[v]) {
            int u = parent[v];
            graph[u][v] -= flow;
            graph[v][u] += flow;
        }
        return flow;
    }

}
