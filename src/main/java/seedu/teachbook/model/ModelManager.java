package seedu.teachbook.model;

import static java.util.Objects.requireNonNull;
import static seedu.teachbook.commons.core.index.DefaultIndices.INDEX_DEFAULT_INITIAL_CLASS;
import static seedu.teachbook.commons.core.index.DefaultIndices.INDEX_LIST_ALL;
import static seedu.teachbook.commons.core.index.DefaultIndices.INDEX_NO_CLASS;
import static seedu.teachbook.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.teachbook.commons.core.GuiSettings;
import seedu.teachbook.commons.core.LogsCenter;
import seedu.teachbook.commons.core.index.GeneralIndex;
import seedu.teachbook.model.classobject.Class;
import seedu.teachbook.model.classobject.ClassNameDescriptor;
import seedu.teachbook.model.classobject.exceptions.NoClassWithNameException;
import seedu.teachbook.model.gradeobject.Grade;
import seedu.teachbook.model.student.Student;

/**
 * Represents the in-memory model of the teachbook data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final TeachBook teachBook;
    private final UserPrefs userPrefs;
    private FilteredList<Student> filteredStudents;
    private GeneralIndex currentlySelectedClassIndex;
    private ArrayList<Grade> gradeList = new ArrayList<>();

    /**
     * Initializes a ModelManager with the given teachBook and userPrefs.
     */
    public ModelManager(ReadOnlyTeachBook teachBook, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(teachBook, userPrefs);

        logger.fine("Initializing with teachbook: " + teachBook + " and user prefs " + userPrefs);

        this.teachBook = new TeachBook(teachBook);
        this.userPrefs = new UserPrefs(userPrefs);

        this.currentlySelectedClassIndex = INDEX_NO_CLASS;
        this.filteredStudents = new FilteredList<>(FXCollections.observableArrayList());
        if (this.teachBook.getNumOfClasses() >= 1) {
            updateCurrentlySelectedClass(INDEX_DEFAULT_INITIAL_CLASS);
        }
    }

    public ModelManager() {
        this(new TeachBook(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getTeachBookFilePath() {
        return userPrefs.getTeachBookFilePath();
    }

    @Override
    public void setTeachBookFilePath(Path teachBookFilePath) {
        requireNonNull(teachBookFilePath);
        userPrefs.setTeachBookFilePath(teachBookFilePath);
    }

    //=========== TeachBook ================================================================================

    @Override
    public void setTeachBook(ReadOnlyTeachBook teachBook) {
        this.teachBook.resetData(teachBook);
        this.filteredStudents = new FilteredList<>(FXCollections.observableArrayList());
    }

    @Override
    public ReadOnlyTeachBook getTeachBook() {
        return teachBook;
    }

    @Override
    public GeneralIndex getCurrentlySelectedClassIndex() {
        return currentlySelectedClassIndex;
    }

    @Override
    public boolean hasStudent(Student student) {
        requireNonNull(student);
        return teachBook.hasStudent(currentlySelectedClassIndex, student);
    }

    @Override
    public boolean hasClass(Class aClass) {
        requireNonNull(aClass);
        return teachBook.hasClass(aClass);
    }

    @Override
    public void addClass(Class aClass) {
        teachBook.addClass(aClass);

        updateCurrentlySelectedClass(GeneralIndex.fromOneBased(teachBook.getClassList().size()));
    }

    @Override
    public void deleteClass(Class target) {
        teachBook.removeClass(target);

        if (teachBook.getNumOfClasses() == 0) {
            updateCurrentlySelectedClass(INDEX_NO_CLASS);
        } else if (currentlySelectedClassIndex.getOneBased() > teachBook.getNumOfClasses()) {
            // set currently selected class to the last class in the list
            updateCurrentlySelectedClass(GeneralIndex.fromOneBased(teachBook.getNumOfClasses()));
        } else {
            // same index but different class
            updateCurrentlySelectedClass(currentlySelectedClassIndex);
        }
    }

    @Override
    public void deleteStudent(Student target) {
        teachBook.removeStudent(currentlySelectedClassIndex, target);
    }

    @Override
    public void addStudent(Student student) {
        assert(!currentlySelectedClassIndex.equals(INDEX_NO_CLASS));
        assert(!currentlySelectedClassIndex.equals(INDEX_LIST_ALL));

        teachBook.addStudent(currentlySelectedClassIndex, student);

        updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);
    }

    @Override
    public void setStudent(Student target, Student editedStudent) {
        teachBook.setStudent(currentlySelectedClassIndex, target, editedStudent);
    }

    @Override
    public void setClassForStudent(Student student) {
        requireNonNull(student);
        assert(!currentlySelectedClassIndex.equals(INDEX_NO_CLASS));
        assert(!currentlySelectedClassIndex.equals(INDEX_LIST_ALL));

        teachBook.setClassForStudent(currentlySelectedClassIndex, student);
    }

    //=========== Filtered Student List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Student> getFilteredStudentList() {
        return filteredStudents;
    }

    @Override
    public ObservableList<Class> getUniqueClassList() {
        return teachBook.getClassList();
    }

    @Override
    public void updateFilteredStudentList(Predicate<Student> predicate) {
        requireNonNull(predicate);
        filteredStudents.setPredicate(predicate);
    }

    @Override
    public GeneralIndex getIndexOfClass(ClassNameDescriptor className) throws NoClassWithNameException {
        return teachBook.getIndexOfClass(className);
    }

    @Override
    public void updateCurrentlySelectedClass(GeneralIndex newClassIndex) {
        requireNonNull(newClassIndex);
        currentlySelectedClassIndex = newClassIndex;
        updateSourceOfFilteredStudentList();
    }

    private void updateSourceOfFilteredStudentList() {
        if (currentlySelectedClassIndex.equals(INDEX_LIST_ALL)) {
            filteredStudents = new FilteredList<>(teachBook.getStudentList());
        } else if (currentlySelectedClassIndex.equals(INDEX_NO_CLASS)) {
            filteredStudents = new FilteredList<>(FXCollections.observableArrayList());
        } else {
            filteredStudents = new FilteredList<>(teachBook.getStudentListOfClass(currentlySelectedClassIndex));
        }
        updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);
    }

    public ArrayList<Grade> getGradeList() {
        return gradeList;
    }

    public void setGradeList(ArrayList<Grade> grades) {
        gradeList = grades;
    }

    public boolean isValidGrade(Grade grade) {
        return this.gradeList.contains(grade) || grade.value.equals("Not graded");
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return teachBook.equals(other.teachBook)
                && userPrefs.equals(other.userPrefs)
                && filteredStudents.equals(other.filteredStudents);
    }

}
