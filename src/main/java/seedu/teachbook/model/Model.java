package seedu.teachbook.model;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.teachbook.commons.core.GuiSettings;
import seedu.teachbook.commons.core.index.GeneralIndex;
import seedu.teachbook.model.classobject.Class;
import seedu.teachbook.model.classobject.ClassNameDescriptor;
import seedu.teachbook.model.classobject.exceptions.NoClassWithNameException;
import seedu.teachbook.model.gradeobject.Grade;
import seedu.teachbook.model.gradeobject.GradingSystem;
import seedu.teachbook.model.student.Student;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Student> PREDICATE_SHOW_ALL_STUDENTS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' teachbook file path.
     */
    Path getTeachBookFilePath();

    /**
     * Sets the user prefs' teachbook file path.
     */
    void setTeachBookFilePath(Path teachBookFilePath);

    /**
     * Replaces teachbook data with the data in {@code teachBook}.
     */
    void setTeachBook(ReadOnlyTeachBook teachBook);

    /** Returns the teachbook */
    ReadOnlyTeachBook getTeachBook();

    /** Returns the index of the currently selected class on ui */
    GeneralIndex getCurrentlySelectedClassIndex();

    /**
     * Returns true if a student with the same identity as {@code student} exists in the teachbook.
     */
    boolean hasStudent(Student student);

    /**
     * Returns true if a student with the same identity as {@code student} exists in the teachbook.
     */
    boolean hasClass(Class aClass);

    /**
     * Deletes the given class.
     * The class must exist in the teachbook.
     */
    void deleteClass(Class target);

    /**
     * Adds the given class.
     * {@code aClass} must not already exist in the teachbook.
     */
    void addClass(Class aClass);

    /**
     * Deletes the given student.
     * The student must exist in the teachbook.
     */
    void deleteStudent(Student target);

    /**
     * Adds the given student.
     * {@code student} must not already exist in the teachbook.
     */
    void addStudent(Student student);

    /**
     * Replaces the given student {@code target} with {@code editedPerson}.
     * {@code target} must exist in the teachbook.
     * The student identity of {@code editedPerson} must not be the same as another
     * existing student in the teachbook.
     */
    void setStudent(Student target, Student editedStudent);

    void setClassForStudent(Student student);

    /** Returns an unmodifiable view of the filtered student list */
    ObservableList<Student> getFilteredStudentList();

    /** Returns an unmodifiable view of the unique class list */
    ObservableList<Class> getUniqueClassList();

    /**
     * Updates the filter of the filtered student list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredStudentList(Predicate<Student> predicate);

    GeneralIndex getIndexOfClass(ClassNameDescriptor className) throws NoClassWithNameException;

    GradingSystem getGradingSystem();

    void setGradingSystem(GradingSystem gradingSystem);

    boolean hasExistingGradingSystem();

    boolean isValidGrade(Grade grade);

    void updateCurrentlySelectedClass(GeneralIndex newClassIndex);

    void reorderStudents(Comparator<? super Student> comparator);

    /**
     * Returns true if the model has previous address book states to restore.
     */
    boolean canUndoAddressBook();

    /**
     * Returns true if the model has undone address book states to restore.
     */
    boolean canRedoAddressBook();

    /**
     * Restores the model's address book to its previous state.
     */
    void undoTeachBook();

    /**
     * Restores the model's address book to its previously undone state.
     */
    void redoTeachBook();

    /**
     * Saves the current address book state for undo/redo.
     */
    void commitTeachBook();

    void resetGradingSystem();

    void setClassname(Class newClass);
}
