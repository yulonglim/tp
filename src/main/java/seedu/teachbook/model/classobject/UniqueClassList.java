package seedu.teachbook.model.classobject;

import static java.util.Objects.requireNonNull;
import static seedu.teachbook.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.teachbook.commons.core.index.GeneralIndex;
import seedu.teachbook.commons.core.index.Index;
import seedu.teachbook.model.classobject.exceptions.ClassNameNotFoundException;
import seedu.teachbook.model.classobject.exceptions.DuplicateClassException;

/**
 * A list of Classes that enforces uniqueness between its elements and does not allow nulls.
 * A classobject is considered unique by comparing using {@code classobject#isSameClass(classobject)}.
 * As such, adding and updating of Classes uses classobject#isSameClass(classobject) for equality so
 * as to ensure that the classobject being added or updated is unique in terms of identity in the UniqueClassList.
 * However, the removal of a classobject uses classobject#equals(Object) so
 * as to ensure that the classobject with exactly the same fields will be removed.
 * <p>
 * Supports a minimal set of list operations.
 *
 * @see Class#isSameClass(Class)
 */
public class UniqueClassList implements Iterable<Class> {

    private final ObservableList<Class> internalList = FXCollections.observableArrayList();
    private final ObservableList<Class> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent classobject as the given argument.
     */
    public boolean contains(Class toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameClass);
    }

    /**
     * Adds a classobject to the list.
     * The classobject must not already exist in the list.
     */
    public void add(Class toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateClassException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the classobject {@code target} in the list with {@code editedClass}.
     * {@code target} must exist in the list.
     * The classobject identity of {@code editedClass} must not be the same as another existing classobject in the list.
     */
    public void setClass(Class target, Class editedClass) {
        requireAllNonNull(target, editedClass);
        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new ClassNameNotFoundException();
        }
        if (!target.isSameClass(editedClass) && contains(editedClass)) {
            throw new DuplicateClassException();
        }
        internalList.set(index, editedClass);
    }

    /**
     * Removes the equivalent classobject from the list.
     * The classobject must exist in the list.
     */
    public void remove(Class toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new ClassNameNotFoundException();
        }
    }

    public void setClasses(UniqueClassList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code Classes}.
     * {@code Classes} must not contain duplicate Classes.
     */
    public void setClasses(List<Class> classes) {
        requireAllNonNull(classes);
        if (!classesAreUnique(classes)) {
            throw new DuplicateClassException();
        }
        internalList.setAll(classes);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Class> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    public Class getClassAtIndex(GeneralIndex index) {
        if (internalList.isEmpty()) {
            return new Class(new ClassName("hello"));
        } else {
            return internalList.get(index.getZeroBased());
        }
    }

    public GeneralIndex locateClass(ClassName className) throws ClassNameNotFoundException {
        for (int i = 0; i < internalList.size(); i++) {
            if (internalList.get(i).getClassName().equals((className))) {
                return GeneralIndex.fromZeroBased(i);
            }
        }
        throw new ClassNameNotFoundException();
    }

    @Override
    public Iterator<Class> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueClassList // instanceof handles nulls
                && internalList.equals(((UniqueClassList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code Classes} contains only unique Classes.
     */
    private boolean classesAreUnique(List<Class> classes) {
        for (int i = 0; i < classes.size() - 1; i++) {
            for (int j = i + 1; j < classes.size(); j++) {
                if (classes.get(i).isSameClass(classes.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
