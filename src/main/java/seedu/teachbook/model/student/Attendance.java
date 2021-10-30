package seedu.teachbook.model.student;

import static seedu.teachbook.commons.util.AppUtil.checkArgument;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;
import java.util.Objects;

/**
 * Represents a Student's attendance in the teachbook.
 */
public class Attendance {

    public final boolean isPresent;
    public final LocalDateTime lastModified;

    /**
     * Constructs an {@code Attendance}.
     */
    public Attendance(boolean isPresent, LocalDateTime lastModified) {
        this.isPresent = isPresent;
        this.lastModified = lastModified;
    }

    public static boolean isValidAttendance(String test) {
        String[] components = test.split("\\s+");
        if (components.length != 2
                || (!components[0].equalsIgnoreCase("present")
                && !components[0].equalsIgnoreCase("absent"))) {
            return false;
        }
        try {
            LocalDateTime.parse(components[1]);
        } catch (DateTimeParseException exception) {
            return false;
        }
        return true;
    }

    public boolean isPresent() {
        return isPresent;
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

    public String asFormattedString() {
        return isPresent
                ? "Present  "
                + lastModified.format(DateTimeFormatter.ofPattern("dd MMM yyyy hh:mm a", Locale.ENGLISH))
                : "Absent";
    }

    public static Attendance fromString(String attendanceString) {
        checkArgument(isValidAttendance(attendanceString));
        String[] components = attendanceString.split("\\s+");
        boolean isPresent = components[0].equalsIgnoreCase("present");
        LocalDateTime lastModified = LocalDateTime.parse(components[1]);
        return new Attendance(isPresent, lastModified);
    }

    @Override
    public String toString() {
        return (isPresent ? "Present " : "Absent ") + lastModified.toString();
    }
}
