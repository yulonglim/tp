package seedu.teachbook.logic.commands;

import seedu.teachbook.logic.commands.exceptions.CommandException;
import seedu.teachbook.model.Model;

public class ResetGradeCommand extends Command{

    public static final String COMMAND_WORD = "resetGrade";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Resets the grading system. ";

    public static final String MESSAGE_SUCCESS = "Grading system successfully reset";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        model.resetGradingSystem();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
