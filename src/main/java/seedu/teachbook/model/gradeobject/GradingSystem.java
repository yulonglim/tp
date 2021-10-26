package seedu.teachbook.model.gradeobject;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GradingSystem {

    public static final Grade NOT_GRADED = new Grade("");

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

    public boolean hasGrade(Grade grade) {
        return gradeList.contains(grade);
    }

    public void addGrade(Grade grade) {
        assert !hasGrade(grade);
        gradeList.add(grade);
    }

    public boolean isValidGrade(Grade grade) {
        return gradeList.contains(grade) || grade.equals(NOT_GRADED);
    }

    public boolean isInUse() {
        return gradeList.size() != 0;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("[ ");
        result.append(gradeList.stream().map(Grade::toString).collect(Collectors.joining(" > ")));
        result.append(" ]");
        return result.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof GradingSystem // instanceof handles nulls
                && gradeList.equals(((GradingSystem) other).gradeList)); // state check
    }

}
