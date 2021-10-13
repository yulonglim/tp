package seedu.teachbook.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.teachbook.commons.core.index.DefaultIndices.INDEX_LIST_ALL;
import static seedu.teachbook.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import seedu.teachbook.model.Model;

/**
 * Lists all students in the currently selected class to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Lists all students of currently selected class or "
            + "lists all students from all classes by passing in an optional parameter.\n"
            + "Optional parameter: all\n"
            + "Example: " + COMMAND_WORD + ", " + COMMAND_WORD + " all";

    public static final String MESSAGE_SUCCESS = "Listed all students";

    private final boolean isAll;

    public ListCommand(boolean isAll) {
        this.isAll = isAll;
    }

    public ListCommand() {
        this(false);
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        if (isAll) {
            model.updateCurrentlySelectedClass(INDEX_LIST_ALL);
            return new CommandResult(MESSAGE_SUCCESS, false, false, true, true);
        }

        model.updateFilteredStudentList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
