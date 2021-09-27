package seedu.address.model.Class;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.Class.exceptions.DuplicateClassException;
import seedu.address.model.Class.exceptions.ClassNotFoundException;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

/**
 * A list of Classes that enforces uniqueness between its elements and does not allow nulls.
 * A Class is considered unique by comparing using {@code Class#isSameClass(Class)}. As such, adding and updating of
 * Classes uses Class#isSameClass(Class) for equality so as to ensure that the Class being added or updated is
 * unique in terms of identity in the UniqueClassList. However, the removal of a Class uses Class#equals(Object) so
 * as to ensure that the Class with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Class#isSameClass(Class)
 */
public class UniqueClassList implements Iterable<Class> {

    private final ObservableList<Class> internalList = FXCollections.observableArrayList();
    private final ObservableList<Class> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent Class as the given argument.
     */
    public boolean contains(Class toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameClass);
    }

    /**
     * Adds a Class to the list.
     * The Class must not already exist in the list.
     */
    public void add(Class toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateClassException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the Class {@code target} in the list with {@code editedClass}.
     * {@code target} must exist in the list.
     * The Class identity of {@code editedClass} must not be the same as another existing Class in the list.
     */
    public void setClass(Class target, Class editedClass) {
        requireAllNonNull(target, editedClass);
        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new ClassNotFoundException();
        }
        if (!target.isSameClass(editedClass) && contains(editedClass)) {
            throw new DuplicateClassException();
        }
        internalList.set(index, editedClass);
    }

    /**
     * Removes the equivalent Class from the list.
     * The Class must exist in the list.
     */
    public void remove(Class toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new ClassNotFoundException();
        }
    }

    public void setClasses(UniqueClassList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code Classs}.
     * {@code Classs} must not contain duplicate Classs.
     */
    public void setClasses(List<Class> Classes) {
        requireAllNonNull(Classes);
        if (!ClassesAreUnique(Classes)) {
            throw new DuplicateClassException();
        }
        internalList.setAll(Classes);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Class> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
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
    private boolean ClassesAreUnique(List<Class> Classes) {
        for (int i = 0; i < Classes.size() - 1; i++) {
            for (int j = i + 1; j < Classes.size(); j++) {
                if (Classes.get(i).isSameClass(Classes.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
