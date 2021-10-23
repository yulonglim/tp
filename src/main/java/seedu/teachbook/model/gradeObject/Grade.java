package seedu.teachbook.model.gradeObject;

public class Grade {
    public final String value;
    public Grade(String grade) {
        this.value = grade;
    }

    @Override
    public String toString() {
        return value;
    }
}
