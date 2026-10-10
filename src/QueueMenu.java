/**
 * Console submenu for the Queue component.
 *
 * Owner: Ahamed (ANT.Ahamed) - Queue component
 */
public class QueueMenu {

    private final CircularQueue queue;

    public QueueMenu(CircularQueue queue) {
        this.queue = queue;
    }

    public void run() {
        while (true) {
            System.out.println("\n--------------- QUEUE OPERATIONS ---------------");
            System.out.println("1. Enqueue");
            System.out.println("2. Dequeue");
            System.out.println("3. Peek / Front");
            System.out.println("4. Display Queue");
            System.out.println("5. Return to Main Menu");
            int choice = InputHelper.readInt("Enter your choice: ", 1, 5);

            try {
                switch (choice) {
                    case 1 -> {
                        int value = InputHelper.readInt("Enter value to enqueue: ");
                        queue.enqueue(value);
                        System.out.println("  Enqueued " + value + ".");
                        ResultLog.add("Queue", "Enqueue " + value);
                    }
                    case 2 -> {
                        int value = queue.dequeue();
                        System.out.println("  Dequeued " + value + ".");
                        ResultLog.add("Queue", "Dequeue -> " + value);
                    }
                    case 5 -> { return; }
                }
            } catch (IllegalStateException e) {
                System.out.println("  [!] " + e.getMessage());
            }
        }
    }
}
