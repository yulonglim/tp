package seedu.teachbook.model.gradeobject;

public class Grade {

    public static final String MESSAGE_CONSTRAINTS = "Grade cannot be an empty string!";
    public final String value;

    public Grade(String grade) {
        this.value = grade;
    }

//    public static boolean isValidGrade(String grade) {
//        return grade.equals("");
//    }

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
