import java.util.List;

/**
 * Console submenu for the Graph component.
 *
 * Owner: Azzah (MIFZ.Azzah) - Graph component
 */
public class GraphMenu {

    private final Graph graph;

    public GraphMenu(Graph graph) {
        this.graph = graph;
    }

    public void run() {
        while (true) {
            System.out.println("\n--------------- GRAPH OPERATIONS ---------------");
            System.out.println("1. Add Vertex");
            System.out.println("2. Add Edge");
            System.out.println("3. Remove Edge");
            System.out.println("4. Display Graph (Adjacency List)");
            System.out.println("5. Display Adjacency Matrix");
            System.out.println("6. BFS Traversal");
            System.out.println("7. DFS Traversal");
            System.out.println("8. Search for a Vertex (BFS or DFS)");
            System.out.println("9. Shortest Path between two Vertices");
            System.out.println("10. Load Sample Graph");
            System.out.println("11. Return to Main Menu");
            int choice = InputHelper.readInt("Enter your choice: ", 1, 11);

            try {
                switch (choice) {
                    case 1 -> addVertex();
                    case 2 -> addEdge();
                    case 3 -> removeEdge();
                    case 4 -> graph.display();
                    case 5 -> graph.displayMatrix();
                    case 6 -> traverse(true);
                    case 7 -> traverse(false);
                    case 8 -> searchVertex();
                    case 9 -> shortestPath();
                    case 10 -> loadSample();
                    case 11 -> { return; }
                }
            } catch (IllegalArgumentException e) {
                System.out.println("  [!] " + e.getMessage());
            }
        }
    }

    private void addVertex() {
        String name = InputHelper.readWord("Enter vertex name (e.g. a city, type exactly, no spaces): ");
        if (graph.addVertex(name)) {
            System.out.println("  Vertex " + name + " added.");
            ResultLog.add("Graph", "Added vertex " + name);
        } else {
            System.out.println("  [!] Vertex " + name + " already exists.");
        }
    }

    private void addEdge() {
        if (graph.vertexCount() < 2) {
            System.out.println("  [!] Add at least two vertices first.");
            return;
        }
        String a = InputHelper.readWord("Enter first vertex: ");
        String b = InputHelper.readWord("Enter second vertex: ");
        graph.addEdge(a, b);
        System.out.println("  Edge " + a + " - " + b + " added.");
        ResultLog.add("Graph", "Added edge " + a + " - " + b);
    }

    private void removeEdge() {
        if (graph.edgeCount() == 0) {
            System.out.println("  [!] The graph has no edges.");
            return;
        }
        String a = InputHelper.readWord("Enter first vertex: ");
        String b = InputHelper.readWord("Enter second vertex: ");
        if (graph.removeEdge(a, b)) {
            System.out.println("  Edge " + a + " - " + b + " removed.");
            ResultLog.add("Graph", "Removed edge " + a + " - " + b);
        } else {
            System.out.println("  [!] That edge does not exist.");
        }
    }

    private boolean requireNonEmpty() {
        if (graph.isEmpty()) {
            System.out.println("  [!] Graph is empty. Add vertices or load the sample graph first.");
            return false;
        }
        return true;
    }

    private void traverse(boolean bfs) {
        if (!requireNonEmpty()) return;
        String start = InputHelper.readWord("Enter start vertex: ");
        Graph.TraversalResult r = bfs ? graph.bfs(start) : graph.dfs(start);
        System.out.println("  " + r.algorithm + " order : " + String.join(" -> ", r.order));
        System.out.println("  Vertices reached: " + r.order.size() + " of " + graph.vertexCount()
                + " | Steps: " + r.steps + " | Time: " + r.nanos + " ns");
        ResultLog.add("Graph", r.algorithm + " from " + start + ": " + String.join(" ", r.order)
                + " (steps=" + r.steps + ")");
    }

    private void searchVertex() {
        if (!requireNonEmpty()) return;
        String start = InputHelper.readWord("Enter start vertex: ");
        String target = InputHelper.readWord("Enter vertex to find: ");
        int method = InputHelper.readInt("Use 1) BFS or 2) DFS? ", 1, 2);
        Graph.TraversalResult r = method == 1 ? graph.bfs(start, target) : graph.dfs(start, target);
        System.out.println("  " + r.algorithm + " visited: " + String.join(" -> ", r.order));
        System.out.println(r.found
                ? "  Vertex " + target + " FOUND after " + r.steps + " steps."
                : "  Vertex " + target + " NOT reachable from " + start + " (" + r.steps + " steps).");
        ResultLog.add("Graph", r.algorithm + " search " + start + " to " + target + " -> "
                + (r.found ? "found" : "not found") + " (steps=" + r.steps + ")");
    }

    private void shortestPath() {
        if (!requireNonEmpty()) return;
        String from = InputHelper.readWord("Enter source vertex: ");
        String to = InputHelper.readWord("Enter destination vertex: ");
        List<String> path = graph.shortestPath(from, to);
        if (path.isEmpty()) {
            System.out.println("  No path exists between " + from + " and " + to + ".");
        } else {
            System.out.println("  Shortest path: " + String.join(" -> ", path) + "  (" + (path.size() - 1) + " edges)");
        }
        ResultLog.add("Graph", "Shortest path " + from + " to " + to + " -> "
                + (path.isEmpty() ? "none" : String.join(" ", path)));
    }

    private void loadSample() {
        if (!graph.isEmpty() && !InputHelper.readYesNo("  This replaces the current graph. Continue?")) {
            return;
        }
        graph.loadSample();
        System.out.println("  Sample road network loaded (8 cities, 8 roads).");
        graph.display();
        ResultLog.add("Graph", "Sample graph loaded");
    }
}
