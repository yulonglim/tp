package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javafx.collections.ObservableList;
import seedu.address.commons.core.index.Index;
import seedu.address.model.classobject.Class;
import seedu.address.model.classobject.ClassName;
import seedu.address.model.classobject.UniqueClassList;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Student;
import seedu.address.model.person.UniqueStudentList;
import seedu.address.model.tag.Tag;

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

        /* sample data */
        // TODO: delete these later
        HashSet<Tag> tags = new HashSet<>();
        tags.add(new Tag("leader"));
        Student student1 = new Student(new Name("Tom"), new Phone("12345678"),
                new Email("1@email.com"), new Address("address 1"), tags);
        Student student2 = new Student(new Name("Kitty"), new Phone("87654321"),
                new Email("2@email.com"), new Address("address 2"), new HashSet<>());
        Student student3 = new Student(new Name("Bob"), new Phone("15684523"),
                new Email("3@email.com"), new Address("address 3"), new HashSet<>());
        Student student4 = new Student(new Name("Jane"), new Phone("56874123"),
                new Email("4@email.com"), new Address("address 4"), new HashSet<>());
        Student student5 = new Student(new Name("Linda"), new Phone("85694714"),
                new Email("5@email.com"), new Address("address 5"), new HashSet<>());
        Student student6 = new Student(new Name("Cute"), new Phone("58412987"),
                new Email("6@email.com"), new Address("address 6"), new HashSet<>());
        Class class1 = new Class(new ClassName("remove this line later and fix no class situation"));
        Class class2 = new Class(new ClassName("A"));
        Class class3 = new Class(new ClassName("B"));
        Class class4 = new Class(new ClassName("C"));
        Class class5 = new Class(new ClassName("D"));
        class1.addStudent(student1);
        class2.addStudent(student2);
        class2.addStudent(student3);
        class3.addStudent(student4);
        class3.addStudent(student5);
        class3.addStudent(student6);
        classes.add(class1);
        classes.add(class2);
        classes.add(class3);
        classes.add(class4);
        classes.add(class5);
        /* sample data */
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
