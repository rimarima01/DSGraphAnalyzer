import java.util.function.Supplier;

/**
 * Performance / complexity demonstration.
 * Compares algorithms using the NUMBER OF STEPS (reliable) and execution time (approximate).
 *  - Linear Search vs Binary Search
 *  - BFS vs DFS
 *  - Array vs Linked List insertion
 *
 * Owner: Amhar (MUM.Amhar) - Performance comparison
 */
public class PerformanceAnalyzer {

    private static final String LINE  = "=============================================================================";
    private static final String DASH  = "-----------------------------------------------------------------------------";

    private final Graph graph;

    public PerformanceAnalyzer(Graph graph) {
        this.graph = graph;
    }

    public void run() {
        while (true) {
            System.out.println("\n------------- PERFORMANCE COMPARISON -------------");
            System.out.println("1. Linear Search vs Binary Search (one data size)");
            System.out.println("2. Search Growth Table (many data sizes)");
            System.out.println("3. Graph Traversal: BFS vs DFS");
            System.out.println("4. Array vs Linked List Insertion");
            System.out.println("5. Run Full Report (all comparisons)");
            System.out.println("6. Return to Main Menu");
            int choice = InputHelper.readInt("Enter your choice: ", 1, 6);

            switch (choice) {
                case 1 -> searchComparison(InputHelper.readInt("Data size n (10 - 2000000): ", 10, 2000000));
                case 2 -> growthTable();
                case 3 -> graphComparison();
                case 4 -> insertionComparison(InputHelper.readInt("Number of insertions (10 - 20000): ", 10, 20000));
                case 5 -> fullReport();
                case 6 -> { return; }
            }
        }
    }

    // ------------------------------------------------------------------ search

    private int[] sortedData(int n) {
        int[] data = new int[n];
        for (int i = 0; i < n; i++) data[i] = (i + 1) * 2;      // 2, 4, 6 ...
        return data;
    }

    private SearchResult averaged(boolean linear, int[] data, int target) {
        int reps = (int) Math.max(20, 2_000_000L / data.length);
        SearchResult last = null;
        for (int i = 0; i < 50; i++) {                          // warm-up so the JVM is ready
            last = linear ? SearchAlgorithms.linearSearch(data, target) : SearchAlgorithms.binarySearch(data, target);
        }
        long total = 0;
        for (int i = 0; i < reps; i++) {
            last = linear ? SearchAlgorithms.linearSearch(data, target) : SearchAlgorithms.binarySearch(data, target);
            total += last.getNanos();
        }
        return new SearchResult(last.getAlgorithm(), last.getIndex(), last.getSteps(), total / reps);
    }

    private void printHeader() {
        System.out.println(LINE);
        System.out.println(" PERFORMANCE COMPARISON");
        System.out.println(LINE);
        System.out.printf("%-16s %-18s %12s %16s   %s%n", "Operation", "Algorithm", "Steps", "Avg Time (ns)", "Result");
        System.out.println(DASH);
    }

    private void printRow(String op, String algo, long steps, long nanos, String result) {
        System.out.printf("%-16s %-18s %12d %16d   %s%n", op, algo, steps, nanos, result);
    }

    public void searchComparison(int n) {
        int[] data = sortedData(n);
        int worstTarget = data[n - 1];          // last element = worst case for linear search
        int missingTarget = 3;                  // odd number: never in the data

        SearchResult lin = averaged(true, data, worstTarget);
        SearchResult bin = averaged(false, data, worstTarget);
        SearchResult linMiss = averaged(true, data, missingTarget);
        SearchResult binMiss = averaged(false, data, missingTarget);

        System.out.println("\nData: " + n + " sorted elements.");
        printHeader();
        printRow("Search (last)", "Linear Search", lin.getSteps(), lin.getNanos(), "found at index " + lin.getIndex());
        printRow("Search (last)", "Binary Search", bin.getSteps(), bin.getNanos(), "found at index " + bin.getIndex());
        printRow("Search (missing)", "Linear Search", linMiss.getSteps(), linMiss.getNanos(), "not found");
        printRow("Search (missing)", "Binary Search", binMiss.getSteps(), binMiss.getNanos(), "not found");
        System.out.println(LINE);
        System.out.println("WHY? Linear search checks elements one by one: O(n) -> " + lin.getSteps() + " steps.");
        System.out.println("     Binary search halves the range every step: O(log n) -> " + bin.getSteps() + " steps.");
        System.out.println("     Binary search needs SORTED data; linear search works on any data.");

        ResultLog.add("Performance", "Search n=" + n + ": linear steps=" + lin.getSteps() + ", binary steps=" + bin.getSteps());
    }

    public void growthTable() {
        int[] sizes = {10, 100, 1000, 10000, 100000, 1000000};
        System.out.println("\nWorst case: searching for the LAST element.");
        System.out.println(LINE);
        System.out.printf("%12s %18s %18s %14s%n", "Data size n", "Linear steps O(n)", "Binary steps", "log2(n)+1");
        System.out.println(DASH);
        for (int n : sizes) {
            int[] data = sortedData(n);
            long linearSteps = SearchAlgorithms.linearSearch(data, data[n - 1]).getSteps();
            long binarySteps = SearchAlgorithms.binarySearch(data, data[n - 1]).getSteps();
            long theory = (long) (Math.log(n) / Math.log(2)) + 1;
            System.out.printf("%12d %18d %18d %14d%n", n, linearSteps, binarySteps, theory);
            ResultLog.add("Performance", "Growth n=" + n + ": linear=" + linearSteps + ", binary=" + binarySteps);
        }
        System.out.println(LINE);
        System.out.println("WHY? When n grows 10x, linear steps grow 10x but binary steps only grow by about 3.");
    }

    // ------------------------------------------------------------------- graph

    private Graph.TraversalResult averaged(Supplier<Graph.TraversalResult> run) {
        for (int i = 0; i < 20; i++) run.get();                 // warm-up
        int reps = 100;
        long total = 0;
        Graph.TraversalResult last = null;
        for (int i = 0; i < reps; i++) {
            last = run.get();
            total += last.nanos;
        }
        last.nanos = total / reps;
        return last;
    }

    public void graphComparison() {
        Graph target;
        String label;
        if (graph.isEmpty()) {
            System.out.println("  [i] Your graph is empty, so a 1000-vertex generated graph will be used.");
            target = Graph.generateRandom(1000, 1500);
            label = "generated graph (1000 vertices)";
        } else {
            int mode = InputHelper.readInt("Use 1) the current graph or 2) a generated 1000-vertex graph? ", 1, 2);
            target = mode == 1 ? graph : Graph.generateRandom(1000, 1500);
            label = mode == 1 ? "current graph" : "generated graph (1000 vertices)";
        }
        graphComparison(target, label);
    }

    private void graphComparison(Graph g, String label) {
        java.util.List<String> names = g.vertices();
        String start = names.get(0);
        String goal = names.get(names.size() - 1);

        Graph.TraversalResult bfsAll = averaged(() -> g.bfs(start));
        Graph.TraversalResult dfsAll = averaged(() -> g.dfs(start));
        Graph.TraversalResult bfsFind = averaged(() -> g.bfs(start, goal));
        Graph.TraversalResult dfsFind = averaged(() -> g.dfs(start, goal));

        System.out.println("\nGraph: " + label + " - " + g.vertexCount() + " vertices, " + g.edgeCount() + " edges.");
        printHeader();
        printRow("Graph Traversal", "BFS", bfsAll.steps, bfsAll.nanos, bfsAll.order.size() + " vertices visited");
        printRow("Graph Traversal", "DFS", dfsAll.steps, dfsAll.nanos, dfsAll.order.size() + " vertices visited");
        printRow("Find " + goal, "BFS", bfsFind.steps, bfsFind.nanos, bfsFind.found ? "found" : "not found");
        printRow("Find " + goal, "DFS", dfsFind.steps, dfsFind.nanos, dfsFind.found ? "found" : "not found");
        System.out.println(LINE);
        System.out.println("WHY? Both BFS and DFS are O(V + E): a full traversal visits every vertex once and");
        System.out.println("     examines every edge, so the full-traversal steps are equal (V + 2E = "
                + (g.vertexCount() + 2 * g.edgeCount()) + ").");
        System.out.println("     When searching for ONE vertex, the steps differ because BFS explores level by level");
        System.out.println("     while DFS goes deep first - which one stops earlier depends on where the vertex is.");

        ResultLog.add("Performance", "Graph (" + label + "): BFS full steps=" + bfsAll.steps + ", DFS full steps=" + dfsAll.steps
                + ", BFS find steps=" + bfsFind.steps + ", DFS find steps=" + dfsFind.steps);
    }

    // --------------------------------------------------------- data structures

    public void insertionComparison(int n) {
        // 1) insert at the FRONT
        DynamicArray array = new DynamicArray(4);
        long arrayFrontSteps = 0;
        long t = System.nanoTime();
        for (int i = 0; i < n; i++) {
            array.insertAt(0, i);
            arrayFrontSteps += array.getLastSteps();
        }
        long arrayFrontTime = System.nanoTime() - t;

        SinglyLinkedList list = new SinglyLinkedList();
        long listHeadSteps = 0;
        t = System.nanoTime();
        for (int i = 0; i < n; i++) {
            list.insertAtHead(i);
            listHeadSteps += list.getLastSteps();
        }
        long listHeadTime = System.nanoTime() - t;

        // 2) insert at the END
        DynamicArray array2 = new DynamicArray(4);
        long arrayEndSteps = 0;
        t = System.nanoTime();
        for (int i = 0; i < n; i++) {
            array2.insertAtEnd(i);
            arrayEndSteps += array2.getLastSteps();
        }
        long arrayEndTime = System.nanoTime() - t;

        SinglyLinkedList list2 = new SinglyLinkedList();
        long listTailSteps = 0;
        t = System.nanoTime();
        for (int i = 0; i < n; i++) {
            list2.insertAtTail(i);
            listTailSteps += list2.getLastSteps();
        }
        long listTailTime = System.nanoTime() - t;

        System.out.println("\n" + n + " insertions per test (steps = total shifts / nodes visited).");
        printHeader();
        printRow("Insert at front", "Array", arrayFrontSteps, arrayFrontTime, "O(n) each - shifts everything");
        printRow("Insert at front", "Linked List", listHeadSteps, listHeadTime, "O(1) each - just re-link head");
        printRow("Insert at end", "Array", arrayEndSteps, arrayEndTime, "O(1) each - direct index");
        printRow("Insert at end", "Linked List", listTailSteps, listTailTime, "O(n) each - walk to the tail");
        System.out.println(LINE);
        System.out.println("WHY? An array stores items side by side, so inserting at the front must shift every item,");
        System.out.println("     but reaching the end is instant. A linked list is the opposite: the head is instant,");
        System.out.println("     while reaching the tail means following every link. (Time column = total time in ns.)");

        ResultLog.add("Performance", "Insert n=" + n + ": array front=" + arrayFrontSteps + ", list head=" + listHeadSteps
                + ", array end=" + arrayEndSteps + ", list tail=" + listTailSteps);
    }

    // ------------------------------------------------------------- full report

    public void fullReport() {
        System.out.println("\n############ FULL PERFORMANCE REPORT ############");
        searchComparison(10000);
        growthTable();
        if (graph.isEmpty()) {
            graphComparison(Graph.generateRandom(1000, 1500), "generated graph (1000 vertices)");
        } else {
            graphComparison(graph, "current graph");
        }
        insertionComparison(5000);
    }
}
