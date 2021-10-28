package seedu.teachbook.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.teachbook.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.teachbook.logic.parser.CliSyntax.PREFIX_REMARK;

import java.util.List;

import seedu.teachbook.commons.core.Messages;
import seedu.teachbook.commons.core.index.Index;
import seedu.teachbook.logic.commands.exceptions.CommandException;
import seedu.teachbook.model.Model;
import seedu.teachbook.model.student.Remark;
import seedu.teachbook.model.student.Student;

/**
 * Changes the remark of an existing student in the teachbook.
 */
public class RemarkCommand extends Command {

    public static final String COMMAND_WORD = "remark";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Edits the remark of the student identified "
            + "by the index number used in the displayed student list. "
            + "Existing remark will be overwritten by the input.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_REMARK + "[REMARK]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_REMARK + "Allergic to seafood.";

    public static final String MESSAGE_ADD_REMARK_SUCCESS = "Added remark to Student: %1$s";
    public static final String MESSAGE_DELETE_REMARK_SUCCESS = "Removed remark from Student: %1$s";

    private final Index index;
    private final Remark remark;

    /**
     * Creates an RemarkCommand to edit the remark of the specified {@code Student}.
     *
     * @param index of the student in the filtered student list to edit the remark
     * @param remark of the student to be updated to
     */
    public RemarkCommand(Index index, Remark remark) {
        requireAllNonNull(index, remark);
        this.index = index;
        this.remark = remark;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Student> lastShownList = model.getFilteredStudentList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
        }

        Student studentToEdit = lastShownList.get(index.getZeroBased());
        Student editedStudent = new Student(studentToEdit.getName(), studentToEdit.getPhone(),
                studentToEdit.getStudentClass(), studentToEdit.getEmail(), studentToEdit.getAddress(), remark,
                studentToEdit.getTags(), studentToEdit.getAttendance(), studentToEdit.getGrade());

        model.setStudent(studentToEdit, editedStudent);
        model.commitTeachBook();
        return new CommandResult(generateSuccessMessage(editedStudent));
    }

    /**
     * Generates a command execution success message based on whether the remark is added to or removed from
     * {@code studentToEdit}.
     */
    private String generateSuccessMessage(Student studentToEdit) {
        String message = !remark.value.isEmpty() ? MESSAGE_ADD_REMARK_SUCCESS : MESSAGE_DELETE_REMARK_SUCCESS;
        return String.format(message, studentToEdit);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RemarkCommand // instanceof handles nulls
                && index.equals(((RemarkCommand) other).index)
                && remark.equals(((RemarkCommand) other).remark));
    }

}
