/**
 * DATA STRUCTURE & GRAPH PERFORMANCE ANALYZER
 * CIT300 Data Structures and Algorithms - Graded Practical Assignment 2
 *
 * Main menu and integration of every component.
 *
 * Owner: Rimasa (MSF.Rimasa) - Main menu and system integration
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("=============================================");
        System.out.println("   DATA STRUCTURE & GRAPH ANALYZER  (CIT300)");
        System.out.println("=============================================");

        // Capacity of the stack and queue (small default makes overflow easy to demonstrate)
        int capacity = InputHelper.readInt("Enter capacity for Stack and Queue (1 - 100): ", 1, 100);

        // Shared data structures used by the different menus
        DynamicArray array = new DynamicArray(10);
        ArrayStack stack = new ArrayStack(capacity);
        CircularQueue queue = new CircularQueue(capacity);
        SinglyLinkedList list = new SinglyLinkedList();
        Graph graph = new Graph();

        // One menu object per component
        ArrayMenu arrayMenu = new ArrayMenu(array);
        StackMenu stackMenu = new StackMenu(stack);
        QueueMenu queueMenu = new QueueMenu(queue);
        LinkedListMenu listMenu = new LinkedListMenu(list);
        SearchMenu searchMenu = new SearchMenu(array);
        GraphMenu graphMenu = new GraphMenu(graph);
        PerformanceAnalyzer analyzer = new PerformanceAnalyzer(graph);

        boolean running = true;
        while (running) {
            System.out.println("\n=============================================");
            System.out.println(" DATA STRUCTURE & GRAPH ANALYZER");
            System.out.println("=============================================");
            System.out.println("1. Array Operations");
            System.out.println("2. Stack Operations");
            System.out.println("3. Queue Operations");
            System.out.println("4. Linked List Operations");
            System.out.println("5. Searching Operations");
            System.out.println("6. Graph Operations");
            System.out.println("7. Performance Comparison");
            System.out.println("8. Display All Results");
            System.out.println("9. Exit");
            int choice = InputHelper.readInt("Enter your choice: ", 1, 9);

            switch (choice) {
                case 1 -> arrayMenu.run();
                case 2 -> stackMenu.run();
                case 3 -> queueMenu.run();
                case 4 -> listMenu.run();
                case 5 -> searchMenu.run();
                case 6 -> graphMenu.run();
                case 7 -> analyzer.run();
                case 8 -> ResultLog.displayAll();
                case 9 -> {
                    System.out.println("\nThank you for using the Data Structure & Graph Analyzer. Goodbye!");
                    running = false;
                }
            }
        }
    }
}
