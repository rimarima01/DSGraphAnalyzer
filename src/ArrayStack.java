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

    /** Adds an element on top. Throws if the stack is full (overflow). */
    public void push(int value) {
        if (isFull()) {
            throw new IllegalStateException("Stack Overflow: the stack is full (capacity " + elements.length + ").");
        }
        elements[++top] = value;
    }

    /** Removes and returns the top element. Throws if empty (underflow). */
    public int pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack Underflow: cannot pop from an empty stack.");
        }
        return elements[top--];
    }

    /** Returns the top element without removing it. */
    public int peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty: nothing to peek.");
        }
        return elements[top];
    }
}
