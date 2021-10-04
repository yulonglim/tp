package seedu.teachbook.model;

import javafx.collections.ObservableList;
import seedu.teachbook.model.classobject.Class;
import seedu.teachbook.model.person.Student;

/**
 * Unmodifiable view of an teachbook book
 */
public interface ReadOnlyTeachBook {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Student> getStudentList();

    ObservableList<Class> getClassList();
}
