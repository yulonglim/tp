package seedu.teachbook.model.gradeObject;

public class Grade {
    private final String grade;
    public Grade(String grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return grade;
    }
}
