/**
 * Linear search and binary search with step counting and timing.
 * Linear search: O(n)   Binary search: O(log n) - needs a sorted array.
 *
 * Owner: Rimasa (MSF.Rimasa) - Searching component
 */
public final class SearchAlgorithms {

    private SearchAlgorithms() { }

    /** Checks every element from the start until the target is found. */
    public static SearchResult linearSearch(int[] data, int target) {
        long start = System.nanoTime();
        long steps = 0;
        int found = -1;
        for (int i = 0; i < data.length; i++) {
            steps++;                         // one comparison
            if (data[i] == target) {
                found = i;
                break;
            }
        }
        return new SearchResult("Linear Search", found, steps, System.nanoTime() - start);
    }

    /** Repeatedly halves the search range. The array MUST be sorted. */
    public static SearchResult binarySearch(int[] data, int target) {
        long start = System.nanoTime();
        long steps = 0;
        int low = 0;
        int high = data.length - 1;
        int found = -1;
        while (low <= high) {
            steps++;                         // one comparison with the middle element
            int mid = low + (high - low) / 2;
            if (data[mid] == target) {
                found = mid;
                break;
            } else if (data[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return new SearchResult("Binary Search", found, steps, System.nanoTime() - start);
    }

    /** True if the array is in ascending order. */
    public static boolean isSorted(int[] data) {
        for (int i = 1; i < data.length; i++) {
            if (data[i - 1] > data[i]) return false;
        }
        return true;
    }
}
