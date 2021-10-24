package seedu.teachbook.model.classobject;

import java.util.Comparator;

import javafx.collections.ObservableList;
import seedu.teachbook.model.student.Student;
import seedu.teachbook.model.student.UniqueStudentList;

public class Class {

    private final ClassName className;
    private UniqueStudentList studentsOfThisClass;

    public Class(ClassName className) {
        this.className = className;
        this.studentsOfThisClass = new UniqueStudentList();
    }

    public void reorderStudents(Comparator<? super Student> comparator) {
        studentsOfThisClass.sort(comparator);
    }

    public void resetGrade() {
        studentsOfThisClass.resetGrade();
    }

    public ObservableList<Student> getStudentsOfThisClass() {
        return studentsOfThisClass.asUnmodifiableObservableList();
    }

    public ClassName getClassName() {
        return className;
    }

    public void setStudent(Student target, Student editedStudent) {
        studentsOfThisClass.setStudent(target, editedStudent);
    }

    public void addStudent(Student student) {
        this.studentsOfThisClass.add(student);
    }

    public void removeStudent(Student student) {
        this.studentsOfThisClass.remove(student);
    }

    public boolean containsStudent(Student student) {
        return studentsOfThisClass.contains(student);
    }

    public boolean isSameClass(Class otherClass) {
        if (otherClass == this) {
            return true;
        }

        return otherClass != null
                && otherClass.getClassName().equals(getClassName());
    }

    public int getClassSize() {
        return this.studentsOfThisClass.size();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Class)) {
            return false;
        }

        Class otherClass = (Class) other;
        return otherClass.getClassName().equals(getClassName());
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getClassName());
        return builder.toString();
    }

}
