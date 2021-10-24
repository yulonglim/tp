package seedu.teachbook.model.gradeobject;

import static seedu.teachbook.model.gradeobject.GradingSystem.NOT_GRADED;

import java.util.Comparator;
import java.util.List;

import seedu.teachbook.model.student.Student;


public class GradeComparator implements Comparator<Student> {

    private final List<Grade> gradeList;

    public GradeComparator(List<Grade> gradeList) {
        this.gradeList = gradeList;
        this.gradeList.add(new Grade(NOT_GRADED));
    }

    @Override
    public int compare(Student s1, Student s2) {
        return Integer.compare(gradeList.indexOf(s1.getGrade()), gradeList.indexOf(s2.getGrade()));
    }

}
