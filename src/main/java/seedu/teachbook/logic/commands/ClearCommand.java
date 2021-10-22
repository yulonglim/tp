package seedu.teachbook.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.teachbook.model.Model;
import seedu.teachbook.model.TeachBook;

/**
 * Clears the teachbook.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "teachbook has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setTeachBook(new TeachBook());
        // Make Teachbook filtered list empty again
        return new CommandResult(MESSAGE_SUCCESS, false, false, true, true);
    }
}
