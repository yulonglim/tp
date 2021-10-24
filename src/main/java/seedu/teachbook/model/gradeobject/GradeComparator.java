package seedu.teachbook.model.gradeobject;

import seedu.teachbook.model.student.Student;

import java.util.Comparator;
import java.util.List;

import static seedu.teachbook.model.gradeobject.GradingSystem.NOT_GRADED;

public class GradeComparator implements Comparator<Student> {
    List<Grade> gradeList;
    public GradeComparator(List<Grade> gradeList) {
        this.gradeList = gradeList;
        this.gradeList.add(new Grade(""));
    }
    @Override
    public int compare(Student s1, Student s2) {
        return Integer.compare(gradeList.indexOf(s1.getGrade()), gradeList.indexOf(s2.getGrade()));
    }
}
