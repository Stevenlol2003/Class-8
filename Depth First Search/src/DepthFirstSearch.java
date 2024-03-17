import java.util.Scanner;
import java.util.*;
import java.util.Hashtable;
import java.util.List;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.NoSuchElementException;

public class DepthFirstSearch<NodeType> extends Hashtable<NodeType, DepthFirstSearch.Vertex>{
        /**
         * Vertex objects group a data field with an adjacency list of weighted
         * directed edges that lead away from them.
         */
        protected class Vertex {
            public NodeType data; // vertex label or application specific data
            public LinkedList<Edge> edgesLeaving;

            public Vertex(NodeType data) {
                this.data = data;
                this.edgesLeaving = new LinkedList<>();
            }
        }

        /**
         * Edge objects are stored within their source vertex, and group together
         * their target destination vertex, along with an integer weight.
         */
        protected class Edge {
            public Vertex target;

            public Edge(Vertex target) {
                this.target = target;
            }
        }

        public Hashtable<NodeType, Vertex> vertices; // holds graph verticies, key=data
        public DepthFirstSearch() { vertices = new Hashtable<>(); }

        public boolean insertVertex(NodeType data) {
            if(data == null)
                throw new NullPointerException("Cannot add null vertex");
            if(vertices.containsKey(data)) return false; // duplicate values are not allowed
            vertices.put(data, new Vertex(data));
            return true;
        }

        public boolean insertEdge(String source, String target) {
            if(source == null || target == null)
                throw new NullPointerException("Cannot add edge with null source or target");
            Vertex sourceVertex = this.vertices.get(source);
            Vertex targetVertex = this.vertices.get(target);
            if(sourceVertex == null || targetVertex == null)
                throw new IllegalArgumentException("Cannot add edge with vertices that do not exist");

            // otherwise add new edge to sourceVertex
            sourceVertex.edgesLeaving.add(new Edge(targetVertex));
            return true;
        }


        public boolean containsVertex(NodeType data) {
            if(data == null) throw new NullPointerException("Cannot contain null data vertex");
            return vertices.containsKey(data);
        }

        public boolean containsEdge(NodeType source, NodeType target) {
            if(source == null || target == null) throw new NullPointerException("Cannot contain edge adjacent to null data");
            Vertex sourceVertex = vertices.get(source);
            Vertex targetVertex = vertices.get(target);
            if(sourceVertex == null) return false;
            for(Edge e : sourceVertex.edgesLeaving)
                if(e.target == targetVertex)
                    return true;
            return false;
        }

        public int getEdgeCount() {
            int edgeCount = 0;
            for(Vertex v : vertices.values())
                edgeCount += v.edgesLeaving.size();
            return edgeCount;
        }

        public int getVertexCount() {
            return vertices.size();
        }

        public boolean isEmpty() {
            return vertices.size() == 0;
        }

        public void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int instances = sc.nextInt();
        sc.nextLine();
        int lines;
        for (int i = 0; i < instances; i++) {
            vertices = new DepthFirstSearch();
            lines = sc.nextInt();
            sc.nextLine();
            String[] stringArray;
            for (int j = 0; i < lines; ++i) {
                String line = sc.nextLine();
                stringArray = line.split(" ");
                for (int k = 0; k < stringArray.length; k++) {
                    insertEdge(stringArray[0],stringArray[i]);
                }

            }
        }

    }
}

// python failed attempt example

    //def dfs(node, graph, visited, component):
    //    component.append(node)  # Store answer
    //    visited[node] = True  # Mark visited

    //    # Traverse to each adjacent node of a node
    //    for child in graph[node]:
    //    if not visited[child]:  # Check whether the node is visited or not
    //    dfs(child, graph, visited, component)  # Call the dfs recursively


    //    if __name__ == "__main__":

     //   # Graph of nodes
     //   graph = {
     //   0: [2],
     //   1: [2, 3],
     //   2: [0, 1, 4],
     //   3: [1, 4],
     //   4: [2, 3]
     //   }
     //   node = 0  # Starting node
     //   visited = [False]*len(graph)  # Make all nodes to False initially
     //   component = []
     //   dfs(node, graph, visited, component)  # Traverse to each node of a graph
     //   print(f"Following is the Depth-first search: {component}")  # Print the answer

