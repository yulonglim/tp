package seedu.teachbook.model.student;

import static seedu.teachbook.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.teachbook.model.classobject.Class;
import seedu.teachbook.model.gradeobject.Grade;
import seedu.teachbook.model.tag.Tag;

/**
 * Represents a Student in the teachbook.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Student {

    private final Name name;
    private final Phone phone;
    private Class studentClass;
    private final Email email;
    private final Address address;
    private final Remark remark;
    private final Set<Tag> tags = new HashSet<>();
    private final Attendance attendance;
    private final Grade grade;

    /**
     * Every field must be present and not null.
     */
    public Student(Name name, Phone phone, Email email, Address address, Remark remark, Set<Tag> tags,
                   Attendance attendance, Grade grade) {
        this(name, phone, null, email, address, remark, tags, attendance, grade);
    }

    public Student(Name name, Phone phone, Class studentClass, Email email, Address address, Remark remark,
                   Set<Tag> tags, Attendance attendance, Grade grade) {
        requireAllNonNull(name, phone, email, address, remark, tags, attendance, grade);
        this.name = name;
        this.phone = phone;
        this.studentClass = studentClass;
        this.email = email;
        this.address = address;
        this.remark = remark;
        this.tags.addAll(tags);
        this.attendance = attendance;
        this.grade = grade;
    }

    public Student(Student toCopy) {
        this.name = toCopy.getName();
        this.phone = toCopy.getPhone();
        this.studentClass = toCopy.getStudentClass();
        this.email = toCopy.getEmail();
        this.address = toCopy.getAddress();
        this.remark = toCopy.getRemark();
        this.tags.addAll(toCopy.getTags());
        this.attendance = toCopy.getAttendance();
        this.grade = toCopy.getGrade();
        this.studentClass = toCopy.getStudentClass();
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Class getStudentClass() {
        return studentClass;
    }

    public Email getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    public Remark getRemark() {
        return remark;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    public Attendance getAttendance() {
        return attendance;
    }

    public String getAttendanceString() {
        return attendance.asFormattedString();
    }

    public Grade getGrade() {
        return grade;
    }

    public void setStudentClass(Class studentClass) {
        this.studentClass = studentClass;
    }

    public boolean isPresent() {
        return attendance.isPresent();
    }

    /**
     * Returns true if both students have the same name and are from the same class.
     * This defines a weaker notion of equality between two students.
     */
    public boolean isSameStudent(Student otherStudent) {
        if (otherStudent == this) {
            return true;
        }

        // TODO: should a student be uniquely identified by his/her class and name?
        return otherStudent != null
                && otherStudent.getName().equals(getName())
                && otherStudent.getStudentClass().equals(getStudentClass());
    }

    /**
     * Returns true if both students have the same identity and data fields.
     * This defines a stronger notion of equality between two students.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Student)) {
            return false;
        }

        Student otherStudent = (Student) other;
        return otherStudent.getName().equals(getName())
                && otherStudent.getPhone().equals(getPhone())
                && otherStudent.getStudentClass().getClassName().equals(getStudentClass().getClassName())
                && otherStudent.getEmail().equals(getEmail())
                && otherStudent.getAddress().equals(getAddress())
                && otherStudent.getRemark().equals(getRemark())
                && otherStudent.getTags().equals(getTags())
                && otherStudent.getAttendance().equals(getAttendance())
                && otherStudent.getGrade().equals(getGrade());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, studentClass.getClassName(), email, address, remark, tags, attendance, grade);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Class: ")
                .append(getStudentClass());
        if (!phone.value.equals("")) {
            builder.append("; Phone: ").append(getPhone());
        }
        if (!email.value.equals("")) {
            builder.append("; Email: ").append(getEmail());
        }
        if (!address.value.equals("")) {
            builder.append("; Address: ").append(getAddress());
        }
        if (!remark.value.equals("")) {
            builder.append("; Remark: ").append(getRemark());
        }
        if (!grade.value.equals("")) {
            builder.append("; Grade: ").append(getGrade());
        }
        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }
}
