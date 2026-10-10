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


    /** Inserts at index (0..size). Elements are shifted right - O(n). */
    public boolean insertAt(int index, int value) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index must be between 0 and " + size + ".");
        }
        boolean resized = ensureCapacity();
        long shifts = 0;
        for (int i = size; i > index; i--) {
            data[i] = data[i - 1];
            shifts++;
        }
        data[index] = value;
        size++;
        lastSteps = shifts + 1;
        return resized;
    }

    /** Deletes the element at index and shifts the rest left - O(n). Returns the removed value. */
    public int deleteAt(int index) {
        if (isEmpty()) {
            throw new IllegalStateException("Array is empty. Nothing to delete.");
        }
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index must be between 0 and " + (size - 1) + ".");
        }
        int removed = data[index];
        long shifts = 0;
        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
            shifts++;
        }
        size--;
        lastSteps = shifts + 1;
        return removed;
    }


    /** Linear search - returns index or -1. Counts comparisons in lastSteps. */
    public int linearSearch(int target) {
        long comparisons = 0;
        for (int i = 0; i < size; i++) {
            comparisons++;
            if (data[i] == target) {
                lastSteps = comparisons;
                return i;
            }
        }
        lastSteps = comparisons;
        return -1;
    }

    /** Deletes the first occurrence of value. Returns the index it was at, or -1. */
    public int deleteByValue(int value) {
        int index = linearSearch(value);
        if (index != -1) {
            long searchSteps = lastSteps;
            deleteAt(index);
            lastSteps += searchSteps;
        }
        return index;
    }

    public int get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of range.");
        }
        return data[index];
    }

    /** Returns a copy containing only the used elements. */
    public int[] toArray() {
        return Arrays.copyOf(data, size);
    }

    public boolean isSorted() {
        for (int i = 1; i < size; i++) {
            if (data[i - 1] > data[i]) return false;
        }
        return true;
    }

    /** Sorts the used part of the array in ascending order. */
    public void sort() {
        Arrays.sort(data, 0, size);
    }

    public void clear() {
        size = 0;
    }

    /** Prints the array with its indexes. */
    public void display() {
        if (isEmpty()) {
            System.out.println("  Array is empty.");
            return;
        }
        StringBuilder values = new StringBuilder("  Value : ");
        StringBuilder indexes = new StringBuilder("  Index : ");
        for (int i = 0; i < size; i++) {
            values.append(String.format("%-6d", data[i]));
            indexes.append(String.format("%-6d", i));
        }
        System.out.println(values);
        System.out.println(indexes);
        System.out.println("  (size = " + size + ", capacity = " + data.length + ")");
    }
}