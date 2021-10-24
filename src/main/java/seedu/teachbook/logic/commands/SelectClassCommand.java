package seedu.teachbook.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.teachbook.commons.core.Messages;
import seedu.teachbook.commons.core.index.GeneralIndex;
import seedu.teachbook.logic.commands.exceptions.CommandException;
import seedu.teachbook.model.Model;
import seedu.teachbook.model.classobject.ClassNameDescriptor;
import seedu.teachbook.model.classobject.exceptions.NoClassWithNameException;

/**
 * Switches to another class identified using its name.
 */
public class SelectClassCommand extends Command {

    public static final String COMMAND_WORD = "select";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Switches to the class with "
            + "the specified class name (case-sensitive).\n"
            + "Parameters: CLASSNAME\n"
            + "Example: " + COMMAND_WORD + " Ace";

    public static final String MESSAGE_SELECT_CLASS_SUCCESS = "Currently displaying all the students from Class: %1$s";

    private final ClassNameDescriptor newClassName;

    public SelectClassCommand(ClassNameDescriptor newClassName) {
        this.newClassName = newClassName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        GeneralIndex newClassIndex;

        try {
            newClassIndex = model.getIndexOfClass(newClassName);
        } catch (NoClassWithNameException exception) {
            throw new CommandException(Messages.MESSAGE_CLASS_DOES_NOT_EXIST);
        }

        model.updateCurrentlySelectedClass(newClassIndex);
        return new CommandResult(String.format(MESSAGE_SELECT_CLASS_SUCCESS, newClassName),
                false, false, true, true);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SelectClassCommand // instanceof handles nulls
                && newClassName.equals(((SelectClassCommand) other).newClassName)); // state check
    }
}
