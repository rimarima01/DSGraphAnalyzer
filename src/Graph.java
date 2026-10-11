import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/**
 * Undirected, unweighted graph stored as an ADJACENCY LIST.
 * Vertices are named with text labels (A, B, Delhi ...).
 * Supports add vertex/edge, display, BFS, DFS, search and shortest path.
 *
 * Steps counted by the traversals = vertices visited + edges examined.
 * Both BFS and DFS are O(V + E).
 *
 * Owner: Azzah (MIFZ.Azzah) - Graph component
 */
public class Graph {

    /** Result of a traversal or search. */
    public static class TraversalResult {
        public final String algorithm;
        public final List<String> order = new ArrayList<>();   // order vertices were visited
        public long steps;
        public long nanos;
        public boolean found;                                  // used by search

        public TraversalResult(String algorithm) {
            this.algorithm = algorithm;
        }
    }

    // LinkedHashMap keeps vertices in the order they were added
    private final Map<String, List<String>> adjacency = new LinkedHashMap<>();
    private int edgeCount = 0;

    public int vertexCount()        { return adjacency.size(); }
    public int edgeCount()          { return edgeCount; }
    public boolean isEmpty()        { return adjacency.isEmpty(); }
    public boolean hasVertex(String v) { return adjacency.containsKey(v); }

    public List<String> vertices() {
        return new ArrayList<>(adjacency.keySet());
    }

    /** Adds a vertex. Returns false if it already exists. */
    public boolean addVertex(String name) {
        if (adjacency.containsKey(name)) {
            return false;
        }
        adjacency.put(name, new ArrayList<>());
        return true;
    }

    /** Adds an undirected edge. Throws for unknown vertices, self-loops and duplicates. */
    public void addEdge(String a, String b) {
        if (!hasVertex(a) || !hasVertex(b)) {
            throw new IllegalArgumentException("Both vertices must exist first.");
        }
        if (a.equals(b)) {
            throw new IllegalArgumentException("Self-loops are not allowed.");
        }
        if (adjacency.get(a).contains(b)) {
            throw new IllegalArgumentException("Edge " + a + " - " + b + " already exists.");
        }
        adjacency.get(a).add(b);
        adjacency.get(b).add(a);
        edgeCount++;
    }

    /** Removes an undirected edge. Returns false if it did not exist. */
    public boolean removeEdge(String a, String b) {
        if (!hasVertex(a) || !hasVertex(b) || !adjacency.get(a).contains(b)) {
            return false;
        }
        adjacency.get(a).remove(b);
        adjacency.get(b).remove(a);
        edgeCount--;
        return true;
    }

    /** Prints the adjacency list. */
    public void display() {
        if (isEmpty()) {
            System.out.println("  Graph is empty.");
            return;
        }
        System.out.println("  Adjacency List:");
        for (Map.Entry<String, List<String>> entry : adjacency.entrySet()) {
            System.out.println("  " + entry.getKey() + " -> " + entry.getValue());
        }
        System.out.println("  (vertices = " + vertexCount() + ", edges = " + edgeCount + ")");
    }

    /** Prints the adjacency matrix (1 = edge exists). */
    public void displayMatrix() {
        if (isEmpty()) {
            System.out.println("  Graph is empty.");
            return;
        }
        List<String> names = vertices();
        int width = 4;
        for (String n : names) width = Math.max(width, n.length() + 2);
        System.out.println("  Adjacency Matrix:");
        System.out.print("  " + " ".repeat(width));
        for (String n : names) System.out.print(String.format("%-" + width + "s", n));
        System.out.println();
        for (String row : names) {
            System.out.print("  " + String.format("%-" + width + "s", row));
            for (String col : names) {
                System.out.print(String.format("%-" + width + "s", adjacency.get(row).contains(col) ? "1" : "0"));
            }
            System.out.println();
        }
    }

    /** Breadth-First Search from start (queue). Stops early if target != null and is found. */
    public TraversalResult bfs(String start, String target) {
        requireVertex(start);
        TraversalResult result = new TraversalResult("BFS");
        long begin = System.nanoTime();

        Set<String> visited = new HashSet<>();
        Deque<String> queue = new ArrayDeque<>();
        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            String current = queue.poll();
            result.order.add(current);
            result.steps++;                                  // visiting a vertex
            if (target != null && current.equals(target)) {
                result.found = true;
                break;
            }
            for (String neighbour : adjacency.get(current)) {
                result.steps++;                              // examining an edge
                if (visited.add(neighbour)) {
                    queue.add(neighbour);
                }
            }
        }
        result.nanos = System.nanoTime() - begin;
        return result;
    }

    /** Depth-First Search from start (stack). Stops early if target != null and is found. */
    public TraversalResult dfs(String start, String target) {
        requireVertex(start);
        TraversalResult result = new TraversalResult("DFS");
        long begin = System.nanoTime();

        Set<String> visited = new HashSet<>();
        Deque<String> stack = new ArrayDeque<>();
        stack.push(start);

        while (!stack.isEmpty()) {
            String current = stack.pop();
            if (!visited.add(current)) {
                continue;                                    // already visited
            }
            result.order.add(current);
            result.steps++;                                  // visiting a vertex
            if (target != null && current.equals(target)) {
                result.found = true;
                break;
            }
            // push neighbours in reverse so the first neighbour is visited first
            List<String> neighbours = adjacency.get(current);
            for (int i = neighbours.size() - 1; i >= 0; i--) {
                result.steps++;                              // examining an edge
                if (!visited.contains(neighbours.get(i))) {
                    stack.push(neighbours.get(i));
                }
            }
        }
        result.nanos = System.nanoTime() - begin;
        return result;
    }

    /** Full traversal helpers. */
    public TraversalResult bfs(String start) { return bfs(start, null); }
    public TraversalResult dfs(String start) { return dfs(start, null); }

    /** Shortest path (fewest edges) between two vertices using BFS. Empty list if no path. */
    public List<String> shortestPath(String from, String to) {
        requireVertex(from);
        requireVertex(to);
        Map<String, String> parent = new HashMap<>();
        Deque<String> queue = new ArrayDeque<>();
        queue.add(from);
        parent.put(from, null);

        while (!queue.isEmpty()) {
            String current = queue.poll();
            if (current.equals(to)) {
                List<String> path = new ArrayList<>();
                for (String v = to; v != null; v = parent.get(v)) {
                    path.add(v);
                }
                Collections.reverse(path);
                return path;
            }
            for (String neighbour : adjacency.get(current)) {
                if (!parent.containsKey(neighbour)) {
                    parent.put(neighbour, current);
                    queue.add(neighbour);
                }
            }
        }
        return new ArrayList<>();
    }

    private void requireVertex(String v) {
        if (!hasVertex(v)) {
            throw new IllegalArgumentException("Vertex \"" + v + "\" does not exist.");
        }
    }

    /** Replaces the graph content with a sample road network of 8 Sri Lankan cities. */
    public void loadSample() {
        adjacency.clear();
        edgeCount = 0;
        for (String v : new String[]{"Colombo", "Negombo", "Galle", "Matale", "Kandy", "Matara", "Badulla", "Ampara"}) {
            addVertex(v);
        }
        String[][] roads = {{"Colombo", "Negombo"}, {"Colombo", "Galle"}, {"Negombo", "Matale"}, {"Negombo", "Kandy"},
                            {"Galle", "Matara"}, {"Galle", "Badulla"}, {"Kandy", "Ampara"}, {"Matara", "Ampara"}};
        for (String[] r : roads) {
            addEdge(r[0], r[1]);
        }
    }

    /** Builds a connected random graph with n vertices (V0..Vn-1) for performance testing. */
    public static Graph generateRandom(int n, int extraEdges) {
        Graph g = new Graph();
        Random random = new Random(42);                      // fixed seed = repeatable results
        for (int i = 0; i < n; i++) {
            g.addVertex("V" + i);
        }
        for (int i = 1; i < n; i++) {                        // connect each vertex to an earlier one
            g.addEdge("V" + i, "V" + random.nextInt(i));
        }
        int added = 0;
        int attempts = 0;
        while (added < extraEdges && attempts < extraEdges * 20) {
            attempts++;
            String a = "V" + random.nextInt(n);
            String b = "V" + random.nextInt(n);
            if (!a.equals(b) && !g.adjacency.get(a).contains(b)) {
                g.addEdge(a, b);
                added++;
            }
        }
        return g;
    }
}
