package seedu.teachbook.logic.commands;

import seedu.teachbook.logic.commands.exceptions.CommandException;
import seedu.teachbook.model.Model;
import seedu.teachbook.model.gradeobject.GradeComparator;

public class SortGradeCommand extends Command {
    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_SUCCESS = "Sorted according to grade, HIGH -> LOW";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sorts the teachbook according to grade.";
    public static final String MESSAGE_GRADING_SYSTEM_NOT_SET = "Set a grading system before sorting";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        if (!model.hasExistingGradingSystem()) {
            throw new CommandException(MESSAGE_GRADING_SYSTEM_NOT_SET);
        }
        model.reorderStudents(new GradeComparator(model.getGradingSystem().getGradeList()));
        model.commitTeachBook();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
