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

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Grade)) {
            return false;
        } else {
            return ((Grade) other).value.equals(this.value);
        }
    }
}
