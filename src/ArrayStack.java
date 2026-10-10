/**
 * Stack (LIFO) implemented with a fixed-size array.
 * push / pop / peek are all O(1).
 *
 * Owner: Ahamed (ANT.Ahamed) - Stack component
 */
public class ArrayStack {

    private final int[] elements;
    private int top;            // index of the top element, -1 when empty

    public ArrayStack(int capacity) {
        if (capacity < 1) {
            throw new IllegalArgumentException("Capacity must be at least 1.");
        }
        elements = new int[capacity];
        top = -1;
    }

    public boolean isEmpty() { return top == -1; }
    public boolean isFull()  { return top == elements.length - 1; }
    public int size()        { return top + 1; }
    public int capacity()    { return elements.length; }
}
