package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.commons.core.index.Index;
import seedu.address.model.classobject.Class;
import seedu.address.model.classobject.ClassName;
import seedu.address.model.classobject.UniqueClassList;
import seedu.address.model.person.Student;
import seedu.address.model.person.UniqueStudentList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class TeachBook implements ReadOnlyTeachBook {

    private final UniqueStudentList students;
    private final UniqueClassList classes;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        students = new UniqueStudentList();
        classes = new UniqueClassList();
        classes.add(new Class(new ClassName("remove this line later and fix no class situation"))); // TODO: fix
    }

    public TeachBook() {}

    /**
     * Creates an AddressBook using the Persons in the {@code toBeCopied}
     */
    public TeachBook(ReadOnlyTeachBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setStudents(List<Student> students) {
        this.students.setStudents(students);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyTeachBook newData) {
        requireNonNull(newData);
        setClasses(newData.getClassList());
        setStudents(newData.getStudentList());
    }

    //// person-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    public boolean hasStudent(Student student) {
        requireNonNull(student);
        return students.contains(student);
    }

    /**
     * Adds a person to the address book.
     * The person must not already exist in the address book.
     */
    public void addStudent(Student p) {
        students.add(p);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    public void setStudent(Student target, Student editedStudent) {
        requireNonNull(editedStudent);
        students.setPerson(target, editedStudent);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removeStudent(Student key) {
        students.remove(key);
    }

    //// util methods
    @Override
    public String toString() {
        return students.asUnmodifiableObservableList().size() + " persons";
        // TODO: refine later
    }

    @Override
    public ObservableList<Student> getStudentList() {
        return students.asUnmodifiableObservableList();
    }

    public ObservableList<Student> getStudentListOfClass(Index classIndex) {
        return classes.getClassAtIndex(classIndex).getStudentsOfThisClass().asUnmodifiableObservableList();
    }

    public Index getIndexOfClass(ClassName className) {
        return classes.locateClass(className);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TeachBook // instanceof handles nulls
                && students.equals(((TeachBook) other).students) && classes.equals(((TeachBook) other).classes));
    }

    @Override
    public int hashCode() {
        return students.hashCode() + classes.hashCode();
    }

    public boolean hasClass(Class classObj) {
        requireNonNull(classObj);
        return classes.contains(classObj);
    }

    public void setClass(Class target, Class editedClass) {
        requireNonNull(editedClass);
        classes.setClass(target, editedClass);
    }

    public void addClass(Class toAdd) {
        classes.add(toAdd);
    }

    public void setClasses(List<Class> classes) {
        this.classes.setClasses(classes);
    }

    @Override
    public ObservableList<Class> getClassList() {
        return classes.asUnmodifiableObservableList();
    }

}
