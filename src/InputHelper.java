import java.util.Scanner;

/**
 * Utility class for safe console input.
 * Every read method keeps asking until the user gives valid input.
 *
 * Owner: Rimasa (MSF.Rimasa) - Shared foundation (input validation)
 */
public final class InputHelper {

    private static final Scanner SCANNER = new Scanner(System.in);

    private InputHelper() { }   // utility class - no objects

    /** Reads one line; exits cleanly if the input stream is closed. */
    private static String nextLine() {
        if (!SCANNER.hasNextLine()) {
            System.out.println("\nInput closed. Exiting program.");
            System.exit(0);
        }
        return SCANNER.nextLine().trim();
    }

    /** Reads any whole number. */
    public static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = nextLine();
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("  [!] Invalid input. Please enter a whole number.");
            }
        }
    }

    /** Reads a whole number between min and max (inclusive). */
    public static int readInt(String prompt, int min, int max) {
        while (true) {
            int value = readInt(prompt);
            if (value >= min && value <= max) {
                return value;
            }
            System.out.println("  [!] Please enter a number between " + min + " and " + max + ".");
        }
    }

    /** Reads a non-empty text value (no spaces allowed, used for vertex names). */
    public static String readWord(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = nextLine();
            if (!line.isEmpty() && !line.contains(" ")) {
                return line;
            }
            System.out.println("  [!] Input cannot be empty or contain spaces.");
        }
    }

    /** Reads any non-empty line of text (spaces allowed). */
    public static String readLine(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = nextLine();
            if (!line.isEmpty()) {
                return line;
            }
            System.out.println("  [!] Input cannot be empty.");
        }
    }

    /** Reads a yes/no answer. */
    public static boolean readYesNo(String prompt) {
        while (true) {
            System.out.print(prompt + " (y/n): ");
            String line = nextLine().toLowerCase();
            if (line.equals("y") || line.equals("yes")) return true;
            if (line.equals("n") || line.equals("no")) return false;
            System.out.println("  [!] Please type y or n.");
        }
    }

    /** Waits for the user to press Enter. */
    public static void pause() {
        System.out.print("\nPress Enter to continue...");
        nextLine();
    }
}
