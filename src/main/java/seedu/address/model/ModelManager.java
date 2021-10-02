package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.index.Index;
import seedu.address.model.classobject.Class;
import seedu.address.model.person.Student;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final TeachBook teachBook;
    private final UserPrefs userPrefs;
    private FilteredList<Student> filteredStudents;
    // private final FilteredList<Class> filteredClasses;
    private Index currentlySelectedClassIndex; // Use one-based index here!

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyTeachBook teachBook, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(teachBook, userPrefs);

        logger.fine("Initializing with address book: " + teachBook + " and user prefs " + userPrefs);

        this.teachBook = new TeachBook(teachBook);
        this.userPrefs = new UserPrefs(userPrefs);
        this.currentlySelectedClassIndex = Index.fromOneBased(1);
        // TODO: write the logic when there is no class, I'm assuming at least one class here

        // constructs a filteredList with sourcing from the uniquePersonList of the currently selected class
        filteredStudents = new FilteredList<>(this.teachBook.getStudentListOfClass(currentlySelectedClassIndex));
        // filteredClasses = new FilteredList<>(this.teachBook.getClassList());
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
        return userPrefs.getAddressBookFilePath();
    }

    @Override
    public void setTeachBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setAddressBookFilePath(addressBookFilePath);
    }

    //=========== AddressBook ================================================================================

    @Override
    public void setTeachBook(ReadOnlyTeachBook addressBook) {
        this.teachBook.resetData(addressBook);
    }

    @Override
    public ReadOnlyTeachBook getTeachBook() {
        return teachBook;
    }

    @Override
    public boolean hasStudent(Student student) {
        requireNonNull(student);
        return teachBook.hasStudent(student);
    }

    @Override
    public boolean hasClass(Class classObj) {
        requireNonNull(classObj);
        return teachBook.hasClass(classObj);
    }

    @Override
    public void addClass(Class toAdd) {
        teachBook.addClass(toAdd);
        //updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void deleteStudent(Student target) {
        teachBook.removeStudent(target);
    }

    @Override
    public void addStudent(Student student) {
        teachBook.addStudent(student);
        updateFilteredStudentList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setStudent(Student target, Student editedStudent) {
        requireAllNonNull(target, editedStudent);

        teachBook.setStudent(target, editedStudent);
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Student> getFilteredStudentList() {
        return filteredStudents;
    }

//    @Override
//    public ObservableList<Class> getFilteredClassList() {
//        return filteredClasses;
//    }

    @Override
    public void updateFilteredStudentList(Predicate<Student> predicate) {
        requireNonNull(predicate);
        filteredStudents.setPredicate(predicate);
    }

    // call this method by passing in an index (use -1 when list all!)
    // when "select class"/"list all"/... (when there is a need to change the "source")
    @Override
    public void updateCurrentlySelectedClass(Index newClassIndex) {
        currentlySelectedClassIndex = newClassIndex;
        updateSourceOfFilteredStudentList();
    }

    private void updateSourceOfFilteredStudentList() {
        if (currentlySelectedClassIndex.getOneBased() == -1) {
            filteredStudents = new FilteredList<>(this.teachBook.getStudentList()); // this is to "list all"
        } else {
            filteredStudents = new FilteredList<>(this.teachBook.getStudentListOfClass(currentlySelectedClassIndex));
        }
        updateFilteredStudentList(PREDICATE_SHOW_ALL_PERSONS);
    }

//    @Override
//    public void updateFilteredClassList(Predicate<Class> predicate) {
//        requireNonNull(predicate);
//        filteredClasses.setPredicate(predicate);
//    }

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
