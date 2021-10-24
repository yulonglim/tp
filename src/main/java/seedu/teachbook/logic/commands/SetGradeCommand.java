package seedu.teachbook.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.teachbook.logic.commands.exceptions.CommandException;
import seedu.teachbook.model.Model;
import seedu.teachbook.model.gradeobject.Grade;
import seedu.teachbook.model.gradeobject.GradingSystem;

public class SetGradeCommand extends Command {

    public static final String COMMAND_WORD = "setGrade";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sets a grading system\n"
            + "Parameters: Grade1>Grade2>Grade3... "
            + "Example: " + COMMAND_WORD + " A>B>C>D>E";
    public static final String MESSAGE_SUCCESS = "New grading system set: %1$s";
    public static final String MESSAGE_GRADING_SYSTEM_EXISTS =
            "A grading system is currently in use. Delete the grading system before setting again";

    public final List<Grade> gradeList;

    /**
     * Creates an SetGradeCommand to set a new grading system
     */
    public SetGradeCommand(List<Grade> grades) {
        requireNonNull(grades);
        this.gradeList = grades;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasExistingGradingSystem()) {
            throw new CommandException(MESSAGE_GRADING_SYSTEM_EXISTS);
        }

        GradingSystem gradingSystem = new GradingSystem(gradeList);
        model.setGradingSystem(gradingSystem);
        model.commitAddressBook();
        return new CommandResult(String.format(MESSAGE_SUCCESS, gradingSystem));
    }

    // TODO: change later
//    @Override
//    public boolean equals(Object other) {
//        return other == this // short circuit if same object
//                || (other instanceof seedu.teachbook.logic.commands.SetGradeCommand // instanceof handles nulls
//                && Arrays.equals(grades, ((SetGradeCommand) other).grades));
//    }

}
