package seedu.teachbook.model;

import static seedu.teachbook.model.Model.PREDICATE_SHOW_ALL_STUDENTS;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import javafx.util.Pair;
import seedu.teachbook.commons.core.index.DefaultIndices;
import seedu.teachbook.commons.core.index.GeneralIndex;
import seedu.teachbook.model.student.Student;

/**
 * @author yulonglim-reused
 *
 * Code is adapted from AB4 which has implemented its own undo and redo function.
 */
public class VersionedTeachBook extends TeachBook {
    private final List<Pair<Pair<Predicate<Student>, GeneralIndex>, ReadOnlyTeachBook>> teachBookStateList;
    private int currentStatePointer;

    public VersionedTeachBook(ReadOnlyTeachBook initialState) {
        super(initialState);

        teachBookStateList = new ArrayList<>();
        Pair<Predicate<Student>, GeneralIndex> displaySettings;
        if (initialState.getClassList().size() >= 1) {
            displaySettings = new Pair<>(PREDICATE_SHOW_ALL_STUDENTS, DefaultIndices.INDEX_DEFAULT_INITIAL_CLASS);
        } else {
            displaySettings = new Pair<>(PREDICATE_SHOW_ALL_STUDENTS, DefaultIndices.INDEX_NO_CLASS);
        }
        Pair<Pair<Predicate<Student>, GeneralIndex>, ReadOnlyTeachBook> teachBookState =
                new Pair<>(displaySettings, new TeachBook(initialState));
        teachBookStateList.add(teachBookState);
        currentStatePointer = 0;
    }

    /**
     * Saves a copy of the current {@code TeachBook} state at the end of the state list.
     * Undone states are removed from the state list.
     */
    public void commit(GeneralIndex index, Predicate<Student> predicate) {
        removeStatesAfterCurrentPointer();
        Pair<Predicate<Student>, GeneralIndex> displaySettings = new Pair<>(predicate, index);
        Pair<Pair<Predicate<Student>, GeneralIndex>, ReadOnlyTeachBook> teachBookState =
                new Pair<>(displaySettings, new TeachBook(this));
        teachBookStateList.add(teachBookState);
        System.out.println(teachBookState);
        currentStatePointer++;
    }

    private void removeStatesAfterCurrentPointer() {
        teachBookStateList.subList(currentStatePointer + 1, teachBookStateList.size()).clear();
    }

    /**
     * Restores the teachbook to its previous state.
     */
    public Pair<Predicate<Student>, GeneralIndex> undo() {
        if (!canUndo()) {
            throw new NoUndoableStateException();
        }
        currentStatePointer--;
        resetData(teachBookStateList.get(currentStatePointer).getValue());
        return teachBookStateList.get(currentStatePointer).getKey();
    }

    /**
     * Restores the teachbook to its previously undone state.
     */
    public Pair<Predicate<Student>, GeneralIndex> redo() {
        if (!canRedo()) {
            throw new NoRedoableStateException();
        }
        currentStatePointer++;
        resetData(teachBookStateList.get(currentStatePointer).getValue());
        return teachBookStateList.get(currentStatePointer).getKey();
    }

    /**
     * Returns true if {@code undo()} has teachbook states to undo.
     */
    public boolean canUndo() {
        return currentStatePointer > 0;
    }

    /**
     * Returns true if {@code redo()} has teachbook states to redo.
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
