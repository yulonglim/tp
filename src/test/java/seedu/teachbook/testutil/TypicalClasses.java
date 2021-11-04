package seedu.teachbook.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.teachbook.model.classobject.Class;
import seedu.teachbook.model.student.Student;

/**
 * A utility class containing a list of {@code Class} objects to be used in tests.
 */

public class TypicalClasses {
    public static Class A = new ClassBuilder().withName("A").build();
    public static Class B = new ClassBuilder().withName("B").build();


    private TypicalClasses() {}

    public static List<Class> getTypicalClasses() {
        A = new ClassBuilder().withName("A").build();
        B = new ClassBuilder().withName("B").build();
        for (Student student : TypicalStudents.getTypicalStudents()) {
            student.setStudentClass(A);
            A.addStudent(student);
        }
        return new ArrayList<>(Arrays.asList(A , B));
    }

}
