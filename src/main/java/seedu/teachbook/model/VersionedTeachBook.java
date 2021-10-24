package seedu.teachbook.model;

import java.util.ArrayList;
import java.util.List;

public class VersionedTeachBook extends TeachBook {
    private final List<ReadOnlyTeachBook> teachBookStateList;
    private int currentStatePointer;

    public VersionedTeachBook(ReadOnlyTeachBook initialState) {
        super(initialState);

        teachBookStateList = new ArrayList<>();
        teachBookStateList.add(new TeachBook(initialState));
        currentStatePointer = 0;
    }

    /**
     * Saves a copy of the current {@code AddressBook} state at the end of the state list.
     * Undone states are removed from the state list.
     */
    public void commit() {
        removeStatesAfterCurrentPointer();
        teachBookStateList.add(new TeachBook(this));
        currentStatePointer++;
    }

    private void removeStatesAfterCurrentPointer() {
        teachBookStateList.subList(currentStatePointer + 1, teachBookStateList.size()).clear();
    }

    /**
     * Restores the address book to its previous state.
     */
    public void undo() {
        if (!canUndo()) {
            throw new NoUndoableStateException();
        }
        currentStatePointer--;
        resetData(teachBookStateList.get(currentStatePointer));
    }

    /**
     * Restores the address book to its previously undone state.
     */
    public void redo() {
        if (!canRedo()) {
            throw new NoRedoableStateException();
        }
        currentStatePointer++;
        resetData(teachBookStateList.get(currentStatePointer));
    }

    /**
     * Returns true if {@code undo()} has address book states to undo.
     */
    public boolean canUndo() {
        return currentStatePointer > 0;
    }

    /**
     * Returns true if {@code redo()} has address book states to redo.
     */
    public boolean canRedo() {
        return currentStatePointer < teachBookStateList.size() - 1;
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof VersionedTeachBook)) {
            return false;
        }

        VersionedTeachBook otherVersionedTeachBook = (VersionedTeachBook) other;

        // state check
        return super.equals(otherVersionedTeachBook)
                && teachBookStateList.equals(otherVersionedTeachBook.teachBookStateList)
                && currentStatePointer == otherVersionedTeachBook.currentStatePointer;
    }

    /**
     * Thrown when trying to {@code undo()} but can't.
     */
    public static class NoUndoableStateException extends RuntimeException {
        private NoUndoableStateException() {
            super("Current state pointer at start of addressBookState list, unable to undo.");
        }
    }

    /**
     * Thrown when trying to {@code redo()} but can't.
     */
    public static class NoRedoableStateException extends RuntimeException {
        private NoRedoableStateException() {
            super("Current state pointer at end of addressBookState list, unable to redo.");
        }
    }
}
