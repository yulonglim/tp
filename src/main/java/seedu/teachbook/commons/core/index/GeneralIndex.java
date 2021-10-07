package seedu.teachbook.commons.core.index;

/**
 * Represents a zero-based or one-based index.
 *
 * {@code Index} should be used right from the start (when parsing in a new user input), so that if the current
 * component wants to communicate with another component, it can send an {@code Index} to avoid having to know what
 * base the other component is using for its index. However, after receiving the {@code Index}, that component can
 * convert it back to an int if the index will not be passed to a different component again.
 */
public class GeneralIndex {
    private int zeroBasedIndex;

    /**
     * Index can only be created by calling {@link GeneralIndex#fromZeroBased(int)} or
     * {@link GeneralIndex#fromOneBased(int)}.
     */
    private GeneralIndex(int zeroBasedIndex) {
        this.zeroBasedIndex = zeroBasedIndex;
    }

    public int getZeroBased() {
        return zeroBasedIndex;
    }

    public int getOneBased() {
        return zeroBasedIndex + 1;
    }

    /**
     * Creates a new {@code Index} using a zero-based index.
     */
    public static GeneralIndex fromZeroBased(int zeroBasedIndex) {
        return new GeneralIndex(zeroBasedIndex);
    }

    /**
     * Creates a new {@code Index} using a one-based index.
     */
    public static GeneralIndex fromOneBased(int oneBasedIndex) {
        return new GeneralIndex(oneBasedIndex - 1);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof GeneralIndex // instanceof handles nulls
                && zeroBasedIndex == ((GeneralIndex) other).zeroBasedIndex); // state check
    }
}
