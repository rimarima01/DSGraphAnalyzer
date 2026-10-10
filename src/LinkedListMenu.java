/**
 * Console submenu for the Linked List component.
 *
 * Owner: Amhar (MUM.Amhar) - Linked List component
 */
public class LinkedListMenu {

    private final SinglyLinkedList list;

    public LinkedListMenu(SinglyLinkedList list) {
        this.list = list;
    }

    public void run() {
        while (true) {
            System.out.println("\n------------- LINKED LIST OPERATIONS -------------");
            System.out.println("1. Insert at Head");
            System.out.println("2. Insert at Tail");
            System.out.println("3. Insert at Position");
            System.out.println("4. Delete by Value");
            System.out.println("5. Delete Head");
            System.out.println("6. Search for a Value");
            System.out.println("7. Display List");
            System.out.println("8. Reverse List");
            System.out.println("9. Return to Main Menu");
            int choice = InputHelper.readInt("Enter your choice: ", 1, 9);

            try {
                switch (choice) {
                    case 1 -> {
                        int value = InputHelper.readInt("Enter value: ");
                        list.insertAtHead(value);
                        System.out.println("  Inserted " + value + " at head. Steps = " + list.getLastSteps());
                        ResultLog.add("LinkedList", "Insert head " + value + " (steps=" + list.getLastSteps() + ")");
                    }
                    case 2 -> {
                        int value = InputHelper.readInt("Enter value: ");
                        list.insertAtTail(value);
                        System.out.println("  Inserted " + value + " at tail. Steps = " + list.getLastSteps());
                        ResultLog.add("LinkedList", "Insert tail " + value + " (steps=" + list.getLastSteps() + ")");
                    }
                    case 3 -> {
                        int pos = InputHelper.readInt("Enter position (0 to " + list.size() + "): ", 0, list.size());
                        int value = InputHelper.readInt("Enter value: ");
                        list.insertAt(pos, value);
                        System.out.println("  Inserted " + value + " at position " + pos + ". Steps = " + list.getLastSteps());
                        ResultLog.add("LinkedList", "Insert " + value + " at pos " + pos + " (steps=" + list.getLastSteps() + ")");
                    }
                    case 4 -> {
                        if (list.isEmpty()) {
                            System.out.println("  [!] List is empty. Nothing to delete.");
                            break;
                        }
                        int value = InputHelper.readInt("Enter value to delete: ");
                        boolean deleted = list.deleteByValue(value);
                        System.out.println(deleted
                                ? "  Deleted " + value + ". Steps = " + list.getLastSteps()
                                : "  Value " + value + " not found. Steps = " + list.getLastSteps());
                        ResultLog.add("LinkedList", "Delete " + value + " -> " + (deleted ? "deleted" : "not found"));
                    }
                    case 5 -> {
                        int value = list.deleteHead();
                        System.out.println("  Deleted head node (" + value + ").");
                        ResultLog.add("LinkedList", "Delete head -> " + value);
                    }
                    case 6 -> {
                        if (list.isEmpty()) {
                            System.out.println("  [!] List is empty. Nothing to search.");
                            break;
                        }
                        int value = InputHelper.readInt("Enter value to search: ");
                        int pos = list.search(value);
                        System.out.println(pos == -1
                                ? "  Value " + value + " not found. Nodes visited = " + list.getLastSteps()
                                : "  Value " + value + " found at position " + pos + ". Nodes visited = " + list.getLastSteps());
                        ResultLog.add("LinkedList", "Search " + value + " -> " + (pos == -1 ? "not found" : "pos " + pos)
                                + " (steps=" + list.getLastSteps() + ")");
                    }
                    case 7 -> list.display();
                    case 8 -> {
                        if (list.isEmpty()) {
                            System.out.println("  [!] List is empty. Nothing to reverse.");
                            break;
                        }
                        list.reverse();
                        System.out.println("  List reversed.");
                        list.display();
                        ResultLog.add("LinkedList", "List reversed");
                    }
                    case 9 -> { return; }
                }
            } catch (IllegalStateException | IndexOutOfBoundsException e) {
                System.out.println("  [!] " + e.getMessage());
            }
        }
    }
}
