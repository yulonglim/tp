package seedu.teachbook.model.attendance;

import java.time.LocalDate;
import java.util.Objects;

import seedu.teachbook.model.student.Phone;

/**
 * Represents a Student's attendance in the teachbook.
 */
public class Attendance {

    public final boolean isPresent;
    public final LocalDate lastModified;

    /**
     * Constructs an {@code Attendance}.
     */
    public Attendance(boolean isPresent, LocalDate lastModified) {
        this.isPresent = isPresent;
        this.lastModified = lastModified;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Attendance // instanceof handles nulls
                && isPresent == ((Attendance) other).isPresent // state check
                && lastModified.equals(((Attendance) other).lastModified));
    }

    @Override
    public int hashCode() {
        return Objects.hash(isPresent, lastModified);
    }

    @Override
    public String toString() {
        return (isPresent ? "Present " : "Absent ") + lastModified.toString();
    }
}
