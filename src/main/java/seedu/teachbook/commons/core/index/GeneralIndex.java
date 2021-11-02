package seedu.teachbook.commons.core.index;

/**
 * Represents a zero-based or one-based index.
 *
 * {@code GeneralIndex} is used for indexing the currently selected class.
 * It should not be used with values that can cause integer overflow.
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
     * Creates a new {@code GeneralIndex} using a zero-based index.
     */
    public static GeneralIndex fromZeroBased(int zeroBasedIndex) {
        return new GeneralIndex(zeroBasedIndex);
    }

    /**
     * Creates a new {@code GeneralIndex} using a one-based index.
     */
    public static GeneralIndex fromOneBased(int oneBasedIndex) {
        return new GeneralIndex(oneBasedIndex - 1);
    }

    /**
     * Decrement the value of this {@code GeneralIndex} by one.
     */
    public GeneralIndex minusOne() {
        return GeneralIndex.fromZeroBased(zeroBasedIndex - 1);
    }

    /**
     * Checks if the value of this {@code GeneralIndex} is smaller than the value of {@code otherIndex}.
     * @return {@code true} if the value of this {@code GeneralIndex} is smaller than the value of {@code otherIndex}.
     */
    public boolean isSmallerThan(GeneralIndex otherIndex) {
        return this.zeroBasedIndex < otherIndex.zeroBasedIndex;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof GeneralIndex // instanceof handles nulls
                && zeroBasedIndex == ((GeneralIndex) other).zeroBasedIndex); // state check
    }
}
