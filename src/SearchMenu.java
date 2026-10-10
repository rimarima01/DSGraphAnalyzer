import java.util.Arrays;

/**
 * Console submenu for the Searching component.
 * Works on the shared array that was filled in the Array menu.
 *
 * Owner: Rimasa (MSF.Rimasa) - Searching component
 */
public class SearchMenu {

    private final DynamicArray array;

    public SearchMenu(DynamicArray array) {
        this.array = array;
    }

    public void run() {
        while (true) {
            System.out.println("\n-------------- SEARCHING OPERATIONS --------------");
            System.out.println("1. Linear Search (on the array)");
            System.out.println("2. Binary Search (on the array)");
            System.out.println("3. Compare Linear vs Binary (same target)");
            System.out.println("4. Generate Sorted Test Data (for a fair comparison)");
            System.out.println("5. Display Current Array");
            System.out.println("6. Return to Main Menu");
            int choice = InputHelper.readInt("Enter your choice: ", 1, 6);

            switch (choice) {
                case 1 -> linear();
                case 2 -> binary();
                case 3 -> compare();
                case 4 -> generateData();
                case 5 -> array.display();
                case 6 -> { return; }
            }
        }
    }

    /** Makes sure there is data to search. */
    private boolean hasData() {
        if (array.isEmpty()) {
            System.out.println("  [!] The array is empty. Add data in the Array menu or use option 4 here.");
            return false;
        }
        return true;
    }

    private void linear() {
        if (!hasData()) return;
        int target = InputHelper.readInt("Enter value to search: ");
        SearchResult r = SearchAlgorithms.linearSearch(array.toArray(), target);
        System.out.println("  " + r);
        ResultLog.add("Search", r.getAlgorithm() + " target " + target + " -> "
                + (r.isFound() ? "index " + r.getIndex() : "not found") + " (steps=" + r.getSteps() + ")");
    }

    /** Binary search needs sorted data - we sort a copy if the array is not sorted. */
    private int[] sortedCopy() {
        int[] copy = array.toArray();
        if (!SearchAlgorithms.isSorted(copy)) {
            System.out.println("  [i] Array is not sorted. Binary search will use a sorted COPY of the array.");
            Arrays.sort(copy);
        }
        return copy;
    }

    private void binary() {
        if (!hasData()) return;
        int target = InputHelper.readInt("Enter value to search: ");
        int[] data = sortedCopy();
        SearchResult r = SearchAlgorithms.binarySearch(data, target);
        System.out.println("  " + r);
        ResultLog.add("Search", r.getAlgorithm() + " target " + target + " -> "
                + (r.isFound() ? "index " + r.getIndex() : "not found") + " (steps=" + r.getSteps() + ")");
    }

    private void compare() {
        if (!hasData()) return;
        int target = InputHelper.readInt("Enter value to search: ");
        int[] data = sortedCopy();      // same sorted data for both algorithms = fair test
        SearchResult linear = SearchAlgorithms.linearSearch(data, target);
        SearchResult binary = SearchAlgorithms.binarySearch(data, target);
        System.out.println("  Data size = " + data.length);
        System.out.println("  " + linear);
        System.out.println("  " + binary);
        ResultLog.add("Search", "Compare target " + target + " (n=" + data.length + "): linear steps="
                + linear.getSteps() + ", binary steps=" + binary.getSteps());
    }

    private void generateData() {
        int n = InputHelper.readInt("How many sorted elements (1 - 1000000)? ", 1, 1000000);
        array.clear();
        for (int i = 1; i <= n; i++) {
            array.insertAtEnd(i * 2);          // 2, 4, 6, ... (all even, so odd values are "not found")
        }
        System.out.println("  Array now holds " + n + " sorted even numbers (2, 4, 6, ...).");
        ResultLog.add("Search", "Generated " + n + " sorted test elements");
    }
}
