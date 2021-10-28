package seedu.teachbook.logic.commands;

import seedu.teachbook.logic.commands.exceptions.CommandException;
import seedu.teachbook.model.Model;

public class SortCommand extends Command{

    public static final String COMMAND_WORD = "sort";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": sorts students according to name/grade.\n"
            + "Example: " + COMMAND_WORD + "name";

    private boolean isName;

    public SortCommand(boolean isName) {
        this.isName = isName;
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        if (isName) {
            return new SortNameCommand().execute(model);
        }
        return new SortGradeCommand().execute(model);
    }
}
