/**
 * Singly linked list of integers.
 * Insert at head is O(1); insert at tail, search and delete are O(n).
 * "Steps" (nodes visited) is recorded in lastSteps for performance comparison.
 *
 * Owner: Amhar (MUM.Amhar) - Linked List component
 */
public class SinglyLinkedList {

    /** One node of the list. */
    private static class Node {
        int value;
        Node next;
        Node(int value) { this.value = value; }
    }

    private Node head;
    private int size;
    private long lastSteps;

    public int size()          { return size; }
    public boolean isEmpty()   { return head == null; }
    public long getLastSteps() { return lastSteps; }

    /** Insert at the beginning - O(1). */
    public void insertAtHead(int value) {
        Node node = new Node(value);
        node.next = head;
        head = node;
        size++;
        lastSteps = 1;
    }

    /** Insert at the end - O(n) because we walk to the last node. */
    public void insertAtTail(int value) {
        Node node = new Node(value);
        long steps = 1;
        if (head == null) {
            head = node;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
                steps++;
            }
            current.next = node;
        }
        size++;
        lastSteps = steps;
    }

    /** Insert at a position (0..size). */
    public void insertAt(int index, int value) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Position must be between 0 and " + size + ".");
        }
        if (index == 0) {
            insertAtHead(value);
            return;
        }
        Node current = head;
        long steps = 1;
        for (int i = 0; i < index - 1; i++) {
            current = current.next;
            steps++;
        }
        Node node = new Node(value);
        node.next = current.next;
        current.next = node;
        size++;
        lastSteps = steps;
    }

    /** Delete the first node holding value. Returns true if deleted. */
    public boolean deleteByValue(int value) {
        if (head == null) {
            throw new IllegalStateException("List is empty. Nothing to delete.");
        }
        long steps = 1;
        if (head.value == value) {
            head = head.next;
            size--;
            lastSteps = steps;
            return true;
        }
        Node current = head;
        while (current.next != null) {
            steps++;
            if (current.next.value == value) {
                current.next = current.next.next;
                size--;
                lastSteps = steps;
                return true;
            }
            current = current.next;
        }
        lastSteps = steps;
        return false;
    }

    /** Delete the first node (head). Returns the removed value. */
    public int deleteHead() {
        if (head == null) {
            throw new IllegalStateException("List is empty. Nothing to delete.");
        }
        int value = head.value;
        head = head.next;
        size--;
        lastSteps = 1;
        return value;
    }

    /** Search for value - returns its position (0-based) or -1. */
    public int search(int value) {
        Node current = head;
        long steps = 0;
        int index = 0;
        while (current != null) {
            steps++;
            if (current.value == value) {
                lastSteps = steps;
                return index;
            }
            current = current.next;
            index++;
        }
        lastSteps = steps;
        return -1;
    }

    /** Reverses the list in place - O(n). */
    public void reverse() {
        Node previous = null;
        Node current = head;
        long steps = 0;
        while (current != null) {
            Node next = current.next;
            current.next = previous;
            previous = current;
            current = next;
            steps++;
        }
        head = previous;
        lastSteps = steps;
    }

    public void clear() {
        head = null;
        size = 0;
    }

    /** Prints the list as  10 -> 20 -> 30 -> null */
    public void display() {
        if (head == null) {
            System.out.println("  List is empty.");
            return;
        }
        StringBuilder sb = new StringBuilder("  HEAD -> ");
        Node current = head;
        while (current != null) {
            sb.append(current.value).append(" -> ");
            current = current.next;
        }
        sb.append("null");
        System.out.println(sb);
        System.out.println("  (size = " + size + ")");
    }
}
