package seedu.teachbook.model.gradeobject;

import java.util.ArrayList;
import java.util.List;

public class GradingSystem {

    public static final String NOT_GRADED = "";

    private List<Grade> gradeList;

    public GradingSystem(List<Grade> gradeList) {
        this.gradeList = gradeList;
    }

    public GradingSystem() {
        this.gradeList = new ArrayList<>();
    }

    public List<Grade> getGradeList() {
        return gradeList;
    }

    public void setGradeList(List<Grade> gradeList) {
        this.gradeList = gradeList;
    }

    public boolean isValidGrade(Grade grade) {
        return gradeList.contains(grade) || grade.value.equals("");
    }

    public boolean isInUse() {
        return gradeList.size() != 0;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("[ ");
        for (Grade grade : gradeList) {
            result.append(grade.toString()).append(", ");
        }
        if (gradeList.size() > 0) {
            result.setLength(result.length() - 2);
        }
        result.append(" ]");
        return result.toString();
    }

    // TODO: override equals()

}
