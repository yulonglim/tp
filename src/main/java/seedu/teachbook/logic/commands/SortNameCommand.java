package seedu.teachbook.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.teachbook.logic.commands.exceptions.CommandException;
import seedu.teachbook.model.Model;
import seedu.teachbook.model.student.NameComparator;

public class SortNameCommand extends Command {
    public static final String COMMAND_WORD = "sortName";

    public static final String MESSAGE_SUCCESS = "Sorted according to name";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Sorts students in the currently selected class according to name.";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        model.reorderStudents(new NameComparator());
        model.commitTeachBook();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
