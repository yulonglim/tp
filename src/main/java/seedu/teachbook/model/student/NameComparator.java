package seedu.teachbook.model.student;

import java.util.Comparator;

public class NameComparator implements Comparator<Student> {
    @Override
    public int compare(Student s1, Student s2) {
        return CharSequence.compare(s1.getName().toString().toLowerCase(), s2.getName().toString().toLowerCase());
    }
}
