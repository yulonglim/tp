package seedu.teachbook.logic.commands;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import seedu.teachbook.commons.core.Messages;
import seedu.teachbook.commons.core.index.Index;
import seedu.teachbook.logic.commands.exceptions.CommandException;
import seedu.teachbook.model.Model;
import seedu.teachbook.model.attendance.Attendance;
import seedu.teachbook.model.student.Student;

/**
 * Unmarks a student identified using it's displayed index from the teachbook.
 */
public class UnmarkCommand extends Command {

    public static final String COMMAND_WORD = "unmark";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Unmarks the student, identified by the index number used in the displayed student list, as present.\n"
            + "Parameters: INDEX1 [INDEX2]... (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1, " + COMMAND_WORD + " 2, 4, 5";

    public static final String MESSAGE_UNMARK_STUDENT_SUCCESS = "Unmarked Student(s): %1$s";

    private final ArrayList<Index> targetIndices;

    public UnmarkCommand(ArrayList<Index> targetIndices) {
        this.targetIndices = targetIndices;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Student> lastShownList = model.getFilteredStudentList();

        // Ensures all indices are valid before executing the command
        for (Index targetIndex : targetIndices) {
            if (targetIndex.getZeroBased() >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
            }
        }

        // Sort first to ensure index order to be unmarked
        targetIndices.sort(Comparator.comparingInt(Index::getZeroBased));

        List<Student> studentsToUnmark = new ArrayList<>();
        for (Index targetIndex : targetIndices) {
            Student studentToUnmark = lastShownList.get(targetIndex.getZeroBased());
            studentsToUnmark.add(studentToUnmark);
            Student editedStudent = new Student(studentToUnmark.getName(), studentToUnmark.getPhone(),
                    studentToUnmark.getStudentClass(), studentToUnmark.getEmail(),
                    studentToUnmark.getAddress(), studentToUnmark.getRemark(), studentToUnmark.getTags(),
                    new Attendance(false, LocalDate.now()), studentToUnmark.getGrade());
            model.setStudent(studentToUnmark, editedStudent);
        }

        model.commitAddressBook();
        return new CommandResult(String.format(MESSAGE_UNMARK_STUDENT_SUCCESS, studentsToUnmark), false,
                false, true, false);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UnmarkCommand // instanceof handles nulls
                && targetIndices.equals(((UnmarkCommand) other).targetIndices)); // state check
    }
}
