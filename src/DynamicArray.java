import java.util.Arrays;

/**
 * Integer array with manual insert / delete / search / display operations.
 * The array grows automatically (capacity doubles) when it becomes full.
 * "Steps" counts element shifts or comparisons so complexity can be observed.
 *
 * Owner: Rimasa (MSF.Rimasa) - Array component
 */
public class DynamicArray {

    private int[] data;
    private int size;
    private long lastSteps;     // steps used by the most recent operation

    public DynamicArray(int capacity) {
        if (capacity < 1) {
            throw new IllegalArgumentException("Capacity must be at least 1.");
        }
        this.data = new int[capacity];
        this.size = 0;
    }

    public int size()            { return size; }
    public int capacity()        { return data.length; }
    public boolean isEmpty()     { return size == 0; }
    public long getLastSteps()   { return lastSteps; }

    /** Doubles the capacity when full. Returns true if a resize happened. */
    private boolean ensureCapacity() {
        if (size == data.length) {
            data = Arrays.copyOf(data, data.length * 2);
            return true;
        }
        return false;
    }

    /** Inserts at the end - O(1) normally. */
    public boolean insertAtEnd(int value) {
        boolean resized = ensureCapacity();
        data[size++] = value;
        lastSteps = 1;
        return resized;
    }
}