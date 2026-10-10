/**
 * Queue (FIFO) implemented as a circular array.
 * enqueue / dequeue / peek are all O(1).
 *
 * Owner: Ahamed (ANT.Ahamed) - Queue component
 */
public class CircularQueue {

    private final int[] elements;
    private int front;      // index of the first element
    private int count;      // number of stored elements

    public CircularQueue(int capacity) {
        if (capacity < 1) {
            throw new IllegalArgumentException("Capacity must be at least 1.");
        }
        elements = new int[capacity];
        front = 0;
        count = 0;
    }

    public boolean isEmpty() { return count == 0; }
    public boolean isFull()  { return count == elements.length; }
    public int size()        { return count; }
    public int capacity()    { return elements.length; }

    /** Adds an element at the rear. Throws if the queue is full. */
    public void enqueue(int value) {
        if (isFull()) {
            throw new IllegalStateException("Queue Overflow: the queue is full (capacity " + elements.length + ").");
        }
        int rear = (front + count) % elements.length;
        elements[rear] = value;
        count++;
    }

    /** Removes and returns the front element. Throws if the queue is empty. */
    public int dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue Underflow: cannot dequeue from an empty queue.");
        }
        int value = elements[front];
        front = (front + 1) % elements.length;
        count--;
        return value;
    }

    /** Returns the front element without removing it. */
    public int peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty: nothing at the front.");
        }
        return elements[front];
    }

    /** Prints the queue from front to rear. */
    public void display() {
        if (isEmpty()) {
            System.out.println("  Queue is empty.");
            return;
        }
        StringBuilder sb = new StringBuilder("  FRONT -> ");
        for (int i = 0; i < count; i++) {
            sb.append(elements[(front + i) % elements.length]);
            if (i < count - 1) sb.append(" | ");
        }
        sb.append(" <- REAR");
        System.out.println(sb);
        System.out.println("  (size = " + count + ", capacity = " + elements.length + ")");
    }
}
