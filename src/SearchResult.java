/**
 * Holds the outcome of one search: where it was found, how many steps
 * (comparisons) it took and how long it took.
 *
 * Owner: Rimasa (MSF.Rimasa) - Searching component
 */
public class SearchResult {

    private final String algorithm;
    private final int index;        // -1 when not found
    private final long steps;
    private final long nanos;

    public SearchResult(String algorithm, int index, long steps, long nanos) {
        this.algorithm = algorithm;
        this.index = index;
        this.steps = steps;
        this.nanos = nanos;
    }

    public String getAlgorithm() { return algorithm; }
    public int getIndex()        { return index; }
    public boolean isFound()     { return index != -1; }
    public long getSteps()       { return steps; }
    public long getNanos()       { return nanos; }

    @Override
    public String toString() {
        return String.format("%-14s | %-16s | steps = %-8d | time = %d ns",
                algorithm, isFound() ? "found at index " + index : "not found", steps, nanos);
    }
}
