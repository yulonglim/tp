package seedu.teachbook.model.gradeobject;

import seedu.teachbook.model.student.Student;

import java.util.Comparator;
import java.util.List;

import static seedu.teachbook.model.gradeobject.GradingSystem.NOT_GRADED;

public class GradeComparator implements Comparator<Student> {
    List<Grade> gradeList;
    public GradeComparator(List<Grade> gradeList) {
        this.gradeList = gradeList;
    }
    @Override
    public int compare(Student s1, Student s2) {
        if (s1.getGrade().value.equals(NOT_GRADED)) {
            return s2.getGrade().value.equals(NOT_GRADED) ? 1 : 0;
        }
        if (s2.getGrade().value.equals(NOT_GRADED)) {
            return 0;
        }
        return gradeList.indexOf(s1.getGrade()) > gradeList.indexOf(s2.getGrade()) ? 1 : 0;
    }
}
