package seedu.teachbook.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.teachbook.commons.core.index.GeneralIndex;
import seedu.teachbook.model.classobject.Class;
import seedu.teachbook.model.classobject.ClassNameDescriptor;
import seedu.teachbook.model.classobject.UniqueClassList;
import seedu.teachbook.model.classobject.exceptions.ClassNameWithNameException;
import seedu.teachbook.model.student.Student;
import seedu.teachbook.model.student.UniqueStudentList;

/**
 * Wraps all data at the teachbook level
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
     * Replaces the contents of the student list with {@code persons}.
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
        ObservableList<Class> toCopy = FXCollections.observableArrayList();
        for (Class c:
                newData.getClassList()) {
            Class toAdd = new Class(c.getClassName());
            for (Student s:
                 c.getStudentsOfThisClass()) {
                Student studentToAdd = new Student(s.getName(),s.getPhone(),s.getEmail(),s.getAddress(),s.getTags());
                studentToAdd.setStudentClass(toAdd);
                toAdd.addStudent(studentToAdd);

            }
            toCopy.add(toAdd);
        }

        setClasses(toCopy);
//        setStudents(newData.getStudentList());
    }

    //// student-level operations

    /**
     * Returns true if a student with the same identity as {@code student} exists in the teachbook book.
     */
    public boolean hasStudent(Student student) {
        requireNonNull(student);
        return students.contains(student);
    }

    /**
     * Adds a student to the teachbook book.
     * The student must not already exist in the teachbook book.
     */
    public void addStudent(Student p) {
        students.add(p);
    }

    /**
     * Replaces the given student {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the teachbook book.
     * The student identity of {@code editedPerson} must not be the same as
     * another existing student in the teachbook book.
     */
    public void setStudent(Student target, Student editedStudent) {
        requireNonNull(editedStudent);
        students.setStudent(target, editedStudent);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the teachbook book.
     */
    public void removeStudent(Student key) {
        // students.remove(key); // used only for "all student list"
        System.out.println("here:     " + key);
        System.out.println("here:     " + key.getStudentClass());
        Class c = key.getStudentClass();
        c.removeStudent(key);
    }

    //// util methods
    @Override
    public String toString() {
        return students.asUnmodifiableObservableList().size() + " persons";
        // TODO: refine later
    }

    @Override
    public ObservableList<Student> getStudentList() {
        UniqueStudentList studentsToAdd = new UniqueStudentList();
        for (Class studentClass: classes) {
            for (Student student: studentClass.getStudentsOfThisClass()) {
                studentsToAdd.add(student);
            }
        }
        return studentsToAdd.asUnmodifiableObservableList();
    }

    public int getNumOfClasses() {
        return classes.size();
    }

    public ObservableList<Student> getStudentListOfClass(GeneralIndex classIndex) {
        return classes.getClassAtIndex(classIndex).getStudentsOfThisClass().asUnmodifiableObservableList();
    }

    public GeneralIndex getIndexOfClass(ClassNameDescriptor className) throws ClassNameWithNameException {
        return classes.locateClass(className);
    }

    public Class getClassAtIndex(GeneralIndex classIndex) {
        return classes.getClassAtIndex(classIndex);
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

    public void setClass(Class target, Class editedClass) { // for editClass command
        requireNonNull(editedClass);
        classes.setClass(target, editedClass);
    }

    public void addClass(Class toAdd) {
        classes.add(toAdd);
    }

    public void removeClass(Class target) {
        classes.remove(target);
    }

    public void setClasses(List<Class> classes) {
        this.classes.setClasses(classes);
    }

    @Override
    public ObservableList<Class> getClassList() {
        return classes.asUnmodifiableObservableList();
    }

}
