import java.util.ArrayList;
import java.util.List;

/**
 * Central log of every important operation and result in the program.
 * Used by menu option 8 "Display All Results".
 *
 * Owner: Rimasa (MSF.Rimasa) - Shared foundation (results log)
 */
public final class ResultLog {

    private static final int MAX_ENTRIES = 1000;
    private static final List<String> ENTRIES = new ArrayList<>();

    private ResultLog() { }

    /** Adds one entry, e.g. add("Array", "Inserted 5 at index 2"). */
    public static void add(String module, String message) {
        if (ENTRIES.size() >= MAX_ENTRIES) {
            ENTRIES.remove(0);              // keep the newest entries
        }
        ENTRIES.add(String.format("[%-11s] %s", module, message));
    }

    /** Prints every stored result. */
    public static void displayAll() {
        System.out.println("\n=============================================");
        System.out.println("             ALL RESULTS (LOG)");
        System.out.println("=============================================");
        if (ENTRIES.isEmpty()) {
            System.out.println("No results recorded yet. Use the other menus first.");
        } else {
            for (int i = 0; i < ENTRIES.size(); i++) {
                System.out.printf("%3d. %s%n", i + 1, ENTRIES.get(i));
            }
            System.out.println("---------------------------------------------");
            System.out.println("Total recorded operations: " + ENTRIES.size());
        }
        System.out.println("=============================================");
    }
}
