package seedu.teachbook.model;

import javafx.collections.ObservableList;
import seedu.teachbook.model.classobject.Class;
import seedu.teachbook.model.student.Student;

/**
 * Unmodifiable view of TeachBook
 */
public interface ReadOnlyTeachBook {

    /**
     * Returns an unmodifiable view of the student list.
     * This list will not contain any duplicate students.
     */
    ObservableList<Student> getStudentList();

    /**
     * Returns an unmodifiable view of the class list.
     * This list will not contain any duplicate classes.
     */
    ObservableList<Class> getClassList();
}
