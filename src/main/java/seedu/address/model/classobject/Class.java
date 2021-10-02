package seedu.address.model.classobject;

import java.util.List;

import seedu.address.model.person.Name;
import seedu.address.model.person.Student;
import seedu.address.model.person.UniqueStudentList;

public class Class {

    private final ClassName className;
    private UniqueStudentList studentsOfThisClass;

    public Class(ClassName className) {
        this.className = className;
        this.studentsOfThisClass = new UniqueStudentList();
    }

    public UniqueStudentList getStudentsOfThisClass() {
        return studentsOfThisClass;
    }

    public void setStudentsOfThisClass(List<Student> students) {
        this.studentsOfThisClass.setStudents(students);
    }

    public ClassName getClassName() {
        return className;
    }

    public void addStudent(Student student) {
        this.studentsOfThisClass.add(student);
    }

    public void removeStudent(Student student) {
        this.studentsOfThisClass.remove(student);
    }

    public boolean isSameClass(Class otherClass) {
        if (otherClass == this) {
            return true;
        }

        return otherClass != null
                && otherClass.getClassName().equals(getClassName());
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
        builder.append(getClassName())
                .append(";");
        return builder.toString();
    }

}
