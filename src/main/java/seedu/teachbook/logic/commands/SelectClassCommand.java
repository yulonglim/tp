package seedu.teachbook.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.teachbook.commons.core.Messages;
import seedu.teachbook.commons.core.index.GeneralIndex;
import seedu.teachbook.commons.core.index.Index;
import seedu.teachbook.logic.commands.exceptions.CommandException;
import seedu.teachbook.model.Model;
import seedu.teachbook.model.classobject.Class;
import seedu.teachbook.model.classobject.ClassName;
import seedu.teachbook.model.classobject.exceptions.ClassNameNotFoundException;

/**
 * Finds and lists all persons in teachbook book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class SelectClassCommand extends Command {

    public static final String COMMAND_WORD = "select";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Switches to the class with "
            + "the specified class name (case-sensitive).\n"
            + "Parameters: CLASSNAME\n"
            + "Example: " + COMMAND_WORD + " Ace";

    public static final String MESSAGE_SELECT_CLASS_SUCCESS =
            "Currently displaying all the students from Class: %1$s";

    private final ClassName newClassName;

    public SelectClassCommand(ClassName newClassName) {
        this.newClassName = newClassName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Class> classes = model.getUniqueClassList();
        GeneralIndex newClassIndex;

        try {
            newClassIndex = model.getIndexOfClass(newClassName);
        } catch (ClassNameNotFoundException exception) {
            throw new CommandException(Messages.MESSAGE_INVALID_CLASS_NAME);
        }

        model.updateCurrentlySelectedClass(newClassIndex);
        return new CommandResult(String.format(MESSAGE_SELECT_CLASS_SUCCESS, newClassName),
                false, false, true);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SelectClassCommand // instanceof handles nulls
                && newClassName.equals(((SelectClassCommand) other).newClassName)); // state check
    }
}
