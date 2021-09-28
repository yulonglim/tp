package seedu.address.model.classobject;

import java.util.List;

import seedu.address.model.person.Name;
import seedu.address.model.person.Person;

public class Class {

    private final Name className;
    private List<Person> classList;

    public Class(Name className) {
        this.className = className;
    }

    public List<Person> getClassList() {
        return classList;
    }

    public void setClassList(List<Person> classList) {
        this.classList = classList;
    }

    public Name getClassName() {
        return className;
    }

    public void addStudent(Person student) {
        this.classList.add(student);
    }

    public void removeStudent(Person student) {
        this.classList.remove(student);
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
