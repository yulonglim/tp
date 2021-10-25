package seedu.teachbook.model;

import java.util.ArrayList;
import java.util.List;

import javafx.util.Pair;
import seedu.teachbook.commons.core.index.DefaultIndices;
import seedu.teachbook.commons.core.index.GeneralIndex;

public class VersionedTeachBook extends TeachBook {
    private final List<Pair<ReadOnlyTeachBook, GeneralIndex>> teachBookStateList;
    private int currentStatePointer;

    public VersionedTeachBook(ReadOnlyTeachBook initialState) {
        super(initialState);

        teachBookStateList = new ArrayList<>();
        Pair<ReadOnlyTeachBook, GeneralIndex> toAdd;
        if (initialState.getClassList().size() >= 1) {
            toAdd = new Pair<>(new TeachBook(initialState), DefaultIndices.INDEX_DEFAULT_INITIAL_CLASS);
        } else {
            toAdd = new Pair<>(new TeachBook(initialState), DefaultIndices.INDEX_NO_CLASS);
        }
        teachBookStateList.add(toAdd);
        currentStatePointer = 0;
    }

    /**
     * Saves a copy of the current {@code AddressBook} state at the end of the state list.
     * Undone states are removed from the state list.
     */
    public void commit(GeneralIndex index) {
        removeStatesAfterCurrentPointer();
        Pair<ReadOnlyTeachBook, GeneralIndex> toAdd = new Pair<>(new TeachBook(this), index);
        teachBookStateList.add(toAdd);
        currentStatePointer++;
    }

    private void removeStatesAfterCurrentPointer() {
        teachBookStateList.subList(currentStatePointer + 1, teachBookStateList.size()).clear();
    }

    /**
     * Restores the address book to its previous state.
     */
    public GeneralIndex undo() {
        if (!canUndo()) {
            throw new NoUndoableStateException();
        }
        currentStatePointer--;
        resetData(teachBookStateList.get(currentStatePointer).getKey());
        return teachBookStateList.get(currentStatePointer).getValue();
    }

    /**
     * Restores the address book to its previously undone state.
     */
    public GeneralIndex redo() {
        if (!canRedo()) {
            throw new NoRedoableStateException();
        }
        currentStatePointer++;
        resetData(teachBookStateList.get(currentStatePointer).getKey());
        return teachBookStateList.get(currentStatePointer).getValue();
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
            super("Current state pointer at start of TeachBookState list, unable to undo.");
        }
    }

    /**
     * Thrown when trying to {@code redo()} but can't.
     */
    public static class NoRedoableStateException extends RuntimeException {
        private NoRedoableStateException() {
            super("Current state pointer at end of TeachBookState list, unable to redo.");
        }
    }
}
