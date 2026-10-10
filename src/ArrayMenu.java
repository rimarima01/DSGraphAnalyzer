import java.util.Random;

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
                    case 3 -> deleteByPosition();
                    case 4 -> deleteByValue();
                    case 5 -> search();
                    case 6 -> array.display();
                    case 7 -> sortArray();
                    case 8 -> fillRandom();
                    case 9 -> clearArray();
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

    private void deleteByPosition() {
        if (array.isEmpty()) {
            System.out.println("  [!] Array is empty. Nothing to delete.");
            return;
        }
        int index = InputHelper.readInt("Enter position to delete (0 to " + (array.size() - 1) + "): ", 0, array.size() - 1);
        int removed = array.deleteAt(index);
        System.out.println("  Deleted " + removed + " from index " + index + ". Steps = " + array.getLastSteps());
        ResultLog.add("Array", "Deleted " + removed + " at index " + index + " (steps=" + array.getLastSteps() + ")");
    }

    private void deleteByValue() {
        if (array.isEmpty()) {
            System.out.println("  [!] Array is empty. Nothing to delete.");
            return;
        }
        int value = InputHelper.readInt("Enter value to delete: ");
        int index = array.deleteByValue(value);
        if (index == -1) {
            System.out.println("  Value " + value + " was not found.");
        } else {
            System.out.println("  Deleted " + value + " (was at index " + index + "). Steps = " + array.getLastSteps());
            ResultLog.add("Array", "Deleted value " + value + " (steps=" + array.getLastSteps() + ")");
        }
    }

    private void search() {
        if (array.isEmpty()) {
            System.out.println("  [!] Array is empty. Nothing to search.");
            return;
        }
        int value = InputHelper.readInt("Enter value to search: ");
        int index = array.linearSearch(value);
        if (index == -1) {
            System.out.println("  Value " + value + " not found. Comparisons = " + array.getLastSteps());
        } else {
            System.out.println("  Value " + value + " found at index " + index + ". Comparisons = " + array.getLastSteps());
        }
        ResultLog.add("Array", "Search " + value + " -> " + (index == -1 ? "not found" : "index " + index)
                + " (steps=" + array.getLastSteps() + ")");
    }

    private void sortArray() {
        if (array.isEmpty()) {
            System.out.println("  [!] Array is empty. Nothing to sort.");
            return;
        }
        array.sort();
        System.out.println("  Array sorted in ascending order.");
        array.display();
        ResultLog.add("Array", "Array sorted (" + array.size() + " elements)");
    }

    private void fillRandom() {
        int count = InputHelper.readInt("How many random numbers (1 - 100000)? ", 1, 100000);
        Random random = new Random();
        for (int i = 0; i < count; i++) {
            array.insertAtEnd(random.nextInt(1000));
        }
        System.out.println("  Added " + count + " random numbers (0-999). New size = " + array.size());
        ResultLog.add("Array", "Added " + count + " random numbers");
    }

    private void clearArray() {
        if (InputHelper.readYesNo("  Clear the whole array?")) {
            array.clear();
            System.out.println("  Array cleared.");
            ResultLog.add("Array", "Array cleared");
        }
    }
}
