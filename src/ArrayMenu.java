/**
 * Console submenu for the Array component.
 *
 * Owner: Rimasa (MSF.Rimasa) - Array component
 */
public class ArrayMenu {

    private final DynamicArray array;

    public ArrayMenu(DynamicArray array) {
        this.array = array;
    }

    public void run() {
        while (true) {
            System.out.println("\n--------------- ARRAY OPERATIONS ---------------");
            System.out.println("1. Insert at End");
            System.out.println("2. Insert at Position");
            System.out.println("3. Delete by Position");
            System.out.println("4. Delete by Value");
            System.out.println("5. Search for a Value");
            System.out.println("6. Display Array");
            System.out.println("7. Sort Array (ascending)");
            System.out.println("8. Fill with Random Numbers");
            System.out.println("9. Clear Array");
            System.out.println("10. Return to Main Menu");
            int choice = InputHelper.readInt("Enter your choice: ", 1, 10);

            try {
                switch (choice) {
                    case 1 -> insertAtEnd();
                    case 2 -> insertAtPosition();
                    case 6 -> array.display();
                    case 10 -> { return; }
                }
            } catch (IllegalStateException | IndexOutOfBoundsException e) {
                System.out.println("  [!] " + e.getMessage());
            }
        }
    }

    private void insertAtEnd() {
        int value = InputHelper.readInt("Enter value to insert: ");
        boolean resized = array.insertAtEnd(value);
        System.out.println("  Inserted " + value + " at the end." + (resized ? " (array was full -> capacity doubled)" : ""));
        ResultLog.add("Array", "Inserted " + value + " at end (steps=" + array.getLastSteps() + ")");
    }

    private void insertAtPosition() {
        int index = InputHelper.readInt("Enter position (0 to " + array.size() + "): ", 0, array.size());
        int value = InputHelper.readInt("Enter value to insert: ");
        array.insertAt(index, value);
        System.out.println("  Inserted " + value + " at index " + index + ". Steps (shifts + insert) = " + array.getLastSteps());
        ResultLog.add("Array", "Inserted " + value + " at index " + index + " (steps=" + array.getLastSteps() + ")");
    }
}